package com.zwwl.kotlintest.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;


/**
 * author : zhanghuipeng
 * date : 2021-04-01 13:48:15
 * description : 录音权限
 */
public class RecordAudioPermissions extends BaseHandlerPermissions {


    public RecordAudioPermissions(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void requestPermissions() {
        if (checkSavePermission()) {
            if (mNextHandler != null) {
                mNextHandler.agreeHandlerNext();
            }
        }
    }

    @Override
    public void requestPermissionBack(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            int length = grantResults.length;
            for (int i = 0; i < length; i++) {
                // 没有弹出系统框地方
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (permissions.length > i) {
                        boolean hasRefuse = !ActivityCompat
                                .shouldShowRequestPermissionRationale(mActivity, permissions[i]);
                        if (hasRefuse) {
                            showSettingPermission();
                            return;
                        }
                    }
                    showSettingPermission();
                } else {
                    if (mNextHandler != null) {
                        mNextHandler.agreeHandlerNext();
                    }
                }
            }
        }
    }

    public boolean checkSavePermission() {
        boolean hasPermission = checkNeedPermission(Manifest.permission.RECORD_AUDIO);
        if (!hasPermission) {
            String tip = "mActivity.getString(R.string.str_record_audio_permission_tip)";
            String permissionSave = "mActivity.getString(R.string.str_record_audio_permission)";
            showPermissionTip(tip, permissionSave);
            return false;
        } else {
            return true;
        }
//        return false;
    }
}
