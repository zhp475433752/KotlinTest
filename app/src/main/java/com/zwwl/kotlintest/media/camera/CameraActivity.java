package com.zwwl.kotlintest.media.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zwwl.kotlintest.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 摄像机
 * @author hpzhang
 * 2021年08月05日19:53:20
 *
 * 技术点参考：
 * https://www.pianshen.com/article/37511023073/
 * http://blog.chinaunix.net/uid-26941022-id-3996501.html
 * http://blog.chinaunix.net/uid-9863638-id-1996383.html
 */
public class CameraActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, View.OnClickListener, Camera.PictureCallback {
    private static final String TAG = "CameraActivity";
    private TextureView textureView;
    private Camera mCamera;
    private FloatingActionButton takePicture;
    private int mCurrentCameraType = Camera.CameraInfo.CAMERA_FACING_BACK;
//    private int mCurrentCameraType = Camera.CameraInfo.CAMERA_FACING_FRONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);
        textureView = findViewById(R.id.texture_view);
        takePicture = findViewById(R.id.take_picture);
        textureView.setSurfaceTextureListener(this);
        takePicture.setOnClickListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        int numberOfCameras = Camera.getNumberOfCameras();
        mCamera = Camera.open(mCurrentCameraType);
        //后置摄像头需要恢复画面预览角度，前置摄像头预览会自动校正
        int angle = CameraUtils.getAngle(getApplicationContext(), mCurrentCameraType);
        Log.d(TAG, "旋转角度 = " + angle);
//
        mCamera.setDisplayOrientation(angle);//
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setRotation(angle);
        mCamera.setParameters(parameters);

        try {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {

            }
        });
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


    @Override
    public void onClick(View v) {
        if (v == takePicture) {
            mCamera.takePicture(null, null, this);
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        mCamera.cancelAutoFocus();
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
        new FileSaver(data).save();

    }

    private class FileSaver implements Runnable {
        private byte[] buffer;

        public FileSaver(byte[] buffer) {
            this.buffer = buffer;
        }

        public void save() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+File.separator+"Camera", "000-test-"+System.currentTimeMillis()+".jpg");
                file.createNewFile();

                FileOutputStream os = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(os);

                Bitmap bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

                bos.flush();
                bos.close();
                os.close();

                Looper.prepare();
                Toast.makeText(CameraActivity.this, "照片已保存", Toast.LENGTH_SHORT).show();
                Looper.loop();

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("照片保存异常：", e.getMessage());
            }
        }
    }

}