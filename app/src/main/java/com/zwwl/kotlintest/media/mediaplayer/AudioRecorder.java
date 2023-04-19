package com.zwwl.kotlintest.media.mediaplayer;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecorder {

    private AudioRecord audioRecord;
    private boolean isRecording = false;

    // 设置音频录制参数
    private static final int SAMPLE_RATE = 44100;
    private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT);

    // 开始录制
    public void startRecording(String filePath) {
        if (isRecording) return;
        isRecording = true;
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, BUFFER_SIZE);
        audioRecord.startRecording();
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeAudioDataToFile(filePath);
            }
        }).start();
    }

    // 停止录制
    public void stopRecording() {
        if (!isRecording) return;
        isRecording = false;
        audioRecord.stop();
        audioRecord.release();
        audioRecord = null;
    }

    // 将音频数据写入文件
    private void writeAudioDataToFile(String filePath) {
        byte[] buffer = new byte[BUFFER_SIZE];
        String path = filePath + "/file.pcm"; // 替换为实际的文件路径
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (isRecording) {
            int read = audioRecord.read(buffer, 0, BUFFER_SIZE);
            if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                try {
                    os.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
