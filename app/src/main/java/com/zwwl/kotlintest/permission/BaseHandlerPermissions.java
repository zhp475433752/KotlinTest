package com.zwwl.kotlintest.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;


/**
 * Created by baiwenzhi on 2018/8/16.
 * 基础的权限处理，定义权限申请都会用到的基础方法
 * 当样式不能给满足是可以{@link #createSettingDialog}和{@link #createTipDialog(String, String[])}
 */

public abstract class BaseHandlerPermissions implements IHandlerPermissions {
    public static final int PERMISSION_REQUEST_CODE = 1024;
    // 只能进行一次权限请求(两次的话会将第一次的权限挤出，同时第二次的返回处理请求数量为0)
    public boolean mIsNeedRequest = true;

    // 执行下一步操作
    protected INextHandler mNextHandler;
    protected Activity mActivity;
    // 权限列表
    protected ArrayList<String> mPermissionList = new ArrayList<>();
    // 权限设置对话框
//    private CustomBaseDialog mSettingDialog;
//     权限提示对话框
//    private CustomExtraDialog<CustomExtraDialog> mPermissionTipDialog;

    public void setNextHandler(INextHandler mNextHandler) {
        this.mNextHandler = mNextHandler;
    }

    public BaseHandlerPermissions(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 显示权限设置提醒对话框
     */
    @Override
    public void showSettingPermission() {
//        if (mSettingDialog == null) {
//            mSettingDialog = createSettingDialog();
//        }
//        if (mActivity != null && !mActivity.isFinishing()) {
//            if (!mSettingDialog.isShowing()) {
//                mSettingDialog.setLocation(Gravity.BOTTOM);
//                mSettingDialog.show();
//            }
//        }
    }

    /***
     * 创建设置对话框
     * @return
     */
    protected CustomBaseDialog createSettingDialog() {
//        CustomBaseDialog settingDialog = CustomDialog.createBase(mActivity);
//        settingDialog
//                .setContent(mActivity.getString(R.string.custom_dialog_require_sd_permission))
//                .setNegativeText(mActivity.getString(R.string.custom_dialog_no_setting))
//                .setPositiveText(mActivity.getString(R.string.custom_dialog_go_to_setting))
//                .shouldCancelOnBackKeyDown(false)
//                .shouldCancelOnTouchOutside(false)
//                .setOnDialogClickListener(new CustomBaseDialog.OnDialogClickListener() {
//                    @Override
//                    public void onPositiveClick() {
//                        getToSettingIntent(mActivity);
//                        mIsNeedRequest = true;
//                    }
//
//                    @Override
//                    public void onNegativeClick() {
//                        mIsNeedRequest = true;
//                        if (mNextHandler != null) {
//                            mNextHandler.refuseHandlerNext();
//                        }
//                    }
//                });
//        return settingDialog;
        return null;
    }


    /**
     * 检查需要的权限
     *
     * @param permissionSettingNames 权限组
     * @return
     */
    @Override
    public boolean checkNeedPermission(String... permissionSettingNames) {
        boolean hasNeedPermission = true;
        if (permissionSettingNames != null && permissionSettingNames.length > 0) {
            int length = permissionSettingNames.length;
            mPermissionList.clear();
            for (int i = 0; i < length; i++) {
                String permissionSettingName = permissionSettingNames[i];
                if (PackageManager.PERMISSION_GRANTED
                        != ContextCompat.checkSelfPermission(mActivity, permissionSettingName)) {
                    // 进入到这里代表没有权限.
                    mPermissionList.add(permissionSettingName);
                    hasNeedPermission = false;
                }
            }
        }
        return hasNeedPermission;
    }

    /**
     * 显示权限提示对话框
     *
     * @param tip             权限说明
     * @param permissionNames 权限名称
     */
    @Override
    public void showPermissionTip(String tip, String... permissionNames) {

        // activity 为null activity 已经finish 或不是活跃的结束
        if (mActivity == null || mActivity.isFinishing()) {
            return;
        }

//        if (!mPermissionList.isEmpty()) {
//            if (!mIsNeedRequest) {
//                return;
//            }
//            mIsNeedRequest = false;
//            if (mPermissionTipDialog == null) {
//                mPermissionTipDialog = createTipDialog(tip, permissionNames);
//            }
//            if (mActivity != null && !mActivity.isFinishing()
//                    && !mPermissionTipDialog.isShowing()) {
//                mPermissionTipDialog.setLocation(Gravity.BOTTOM);
//                mPermissionTipDialog.show();
//            }
//
//        }

    }

    /**
     * 创建提示对话框
     *
     * @param tip
     * @param permissionNames
     * @return
     */
//    private CustomExtraDialog<CustomExtraDialog> createTipDialog(String tip, String[] permissionNames) {
//        if (mActivity == null) {
//            return null;
//        }
//        if (TextUtils.isEmpty(tip)) {
//            tip = mActivity.getString(R.string.custom_dialog_permission_tip);
//        }
//        // 当参数为空时给默认检查存储
//        if (permissionNames == null || (permissionNames != null && permissionNames.length == 0)) {
//            permissionNames[0] = mActivity.getString(R.string.custom_dialog_save_permission);
//        }
//        SpannableString spannableString = new SpannableString(tip);
//        int length = permissionNames.length;
//        for (int i = 0; i < length; i++) {
//            String permissionName = permissionNames[i];
//            if (!TextUtils.isEmpty(permissionName)) {
//                int indexStart = tip.indexOf(permissionName);
//                if (indexStart >= 0) {
//                    int nameLength = permissionName.length();
//                    spannableString.setSpan(new ForegroundColorSpan(
//                                    ContextCompat.getColor(App.getInstance().app, R.color.color_main_theme)),
//                            indexStart, indexStart + nameLength,
//                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            }
//        }
//        CustomExtraDialog<CustomExtraDialog> permissionTipDialog = CustomDialog.createExtra(mActivity);
//        permissionTipDialog.setLayoutId(R.layout.dialog_confirm_permission)
//                .setText(R.id.dialog_permission_tv_content, spannableString)
//                .shouldCancelOnBackKeyDown(false)
//                .shouldCancelOnTouchOutside(false)
//                .setOnClickListener(R.id.dialog_permission_tv_sure, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mIsNeedRequest = true;
//                        String[] permissionArray = new String[mPermissionList.size()];
//                        ActivityCompat.requestPermissions(mActivity,
//                                mPermissionList.toArray(permissionArray),
//                                PERMISSION_REQUEST_CODE);
//                    }
//                });
//        return permissionTipDialog;
//    }


    /**
     * 跳转至权限设置界面
     */
    public void getToSettingIntent(Context context) {
        if (context != null) {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(localIntent);

        }
    }

    /**
     * 释放
     */
    public void release() {
//        if (mSettingDialog != null) {
//            try {
////                if (mSettingDialog.isShowing()) {
////                    mSettingDialog.dismiss();
////                }
////                if (mPermissionTipDialog != null) {
////                    if (mPermissionTipDialog.isShowing()) {
////                        mPermissionTipDialog.dismiss();
////                    }
////                }
//                mSettingDialog = null;
//                mPermissionTipDialog = null;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

}