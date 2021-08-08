package com.zwwl.kotlintest.media.camera;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by zhanghuipeng on 8/6/21.
 * 恢复画面计算公式：int angle = (orientation - rotation + 360) % 360
 */
public class CameraUtils {
    /**
     * 获取后置摄像头传感器的安装角度
     *
     * @return
     */
    private static int getCameraOrientationBack(int cameraId) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);
        return cameraInfo.orientation;
    }

    /**
     * 获取前置摄像头传感器的安装角度
     *
     * @param context
     * @return
     */
    private static int getCameraOrientationFront(Context context, int cameraId) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            // 相机特征
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(String.valueOf(cameraId));
            // 配置
            StreamConfigurationMap configurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            //旋转角度
            Integer integer = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            return integer;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取手机屏幕的角度
     *
     * @param context
     * @return
     */
    private static int getDisplayRotation(Context context) {
        int rotation = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        switch (windowManager.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                rotation = 0;
                break;
            case Surface.ROTATION_90:
                rotation = 90;
                break;
            case Surface.ROTATION_180:
                rotation = 180;
                break;
            case Surface.ROTATION_270:
                rotation = 270;
                break;
        }
        return rotation;
    }

    public static int getAngle(Context context, int cameraId) {
        int orientation = 0, rotation = 0;
        if (cameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            orientation = getCameraOrientationBack(cameraId);
        } else {
            orientation = getCameraOrientationFront(context, cameraId);
        }
        rotation = getDisplayRotation(context);
        return (orientation - rotation + 360) % 360;
    }
}
