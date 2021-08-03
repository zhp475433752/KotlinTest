package com.zwwl.kotlintest.permission;

import androidx.annotation.NonNull;

/**
 * Created by baiwenzhi on 2018/8/16.
 */

public interface IHandlerPermissions {


    void requestPermissions();

    /**
     * 显示设置权限对话框
     */
    void showSettingPermission();

    /**
     * 显示权限使用说明
     *
     * @param tip             权限说明
     * @param permissionNames 权限名称
     */
    void showPermissionTip(String tip, String... permissionNames);

    /**
     * 检查权限
     *
     * @param permissionSettingNames 权限组
     */
    boolean checkNeedPermission(String... permissionSettingNames);

    /**
     * 处理权限返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    void requestPermissionBack(int requestCode,
                               @NonNull String[] permissions, @NonNull int[] grantResults);
}
