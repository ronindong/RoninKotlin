package com.ronin.learn.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 2017/6/22.
 */

public class PermissionUtil {

    public static int imageChooserActivityRequestCode = 100;
    public static int taskWallActivityRequestCode = 101;
    private PermissionCallback callback;
    public static int code;
    private static final String TAG = "PermissionUtil";
    private static final String NO_LONGER_ASK = "no_longer_ask";
    private Activity activity;
    private Fragment fragment;


    public static <T extends Object> PermissionUtil getInstance(T t) {
        PermissionUtil permission = SinglonHolder.INSTANCE;
        if (t == null) {
            throw new NullPointerException("NullPointerException");
        } else if (t instanceof Activity) {
            permission.activity = (Activity) t;
        } else if (t instanceof Fragment) {
            permission.fragment = (Fragment) t;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException");
        }

        return permission;
    }

    private PermissionUtil() {
    }

    @SuppressLint("StaticFieldLeak")
    private static class SinglonHolder {
        private static final PermissionUtil INSTANCE = new PermissionUtil();
    }

    /**
     * @param isNeedShowRequestPermissionRationale 当用户第一次拒绝申请权限后再次申请权限是否弹出解释弹框
     * @param permissions                          申请的权限数组
     * @param requestCode                          请求码
     * @param permissionCallback                   检查权限回调
     */
    public void requestPermissions(boolean isNeedShowRequestPermissionRationale, String[] permissions, int requestCode, PermissionCallback permissionCallback) {
        callback = permissionCallback;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissions != null && permissions.length > 0) {
                String[] denyPermissions = getDenyPermissions(permissions);
                if (denyPermissions.length > 0) {
                    if (isNeedShowRequestPermissionRationale) {
                        if (shouldShowRequestPermissionRationale(denyPermissions)) {
                            showRequestPermissionRationale(denyPermissions, callback, requestCode);
                        } else {
                            handlerPermission(requestCode, denyPermissions);
                        }
                    } else {
                        handlerPermission(requestCode, denyPermissions);
                    }
                } else {
                    callback.permittedPermissions();
                }
            } else {
                Log.d(TAG, "requestPermissions:permissions is null");
            }
        } else {
            callback.permittedPermissions();
        }
    }

    private void handlerPermission(int requestCode, String[] denyPermissions) {
        if (fragment != null) {
            fragment.requestPermissions(denyPermissions, requestCode);
        } else {
            ActivityCompat.requestPermissions(activity, denyPermissions, requestCode);
        }
    }

    /**
     * 请求权限结果处理
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param callback
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, PermissionCallback callback) {
        if (requestCode == code) {
            String[] denyPermissions = getNotVerifyPermissions(grantResults, permissions);
            if (denyPermissions.length > 0) {
                callback.rejectPermission(denyPermissions);
                intentSetting(denyPermissions);
            } else {
                callback.permittedPermissions();
            }
        }
    }

    /**
     * 根据申请权限结果，得到未授权的权限数组
     *
     * @param grantResults 存储权限结果数组
     * @param permissions  存储权限数组
     * @return 未授权的权限数组
     */
    public String[] getNotVerifyPermissions(int[] grantResults, String[] permissions) {
        List<String> denyPermissions = new ArrayList<>();
        if (grantResults.length > 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    denyPermissions.add(permissions[i]);
                }
            }
        }
        return denyPermissions.toArray(new String[denyPermissions.size()]);
    }

    /**
     * 检查权限得到未授权的权限数组
     *
     * @param permissions 申请授权的权限数组
     * @return 未授权的权限数组
     */
    private String[] getDenyPermissions(String[] permissions) {
        List<String> denyPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(permission);
            }
        }
        return denyPermissions.toArray(new String[denyPermissions.size()]);
    }

    /**
     * 判断是否有权限需要显示解释弹框
     *
     * @param permissions
     * @return
     */
    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        if (fragment != null) {
            for (String permission : permissions) {
                if (fragment.shouldShowRequestPermissionRationale(permission)) {
                    return true;
                }
            }
        } else {
            for (String permission : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 显示解释弹框
     *
     * @param callback
     * @param requestCode
     */
    private void showRequestPermissionRationale(final String[] permissions, final PermissionCallback callback, final int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("需要开启权限才能使用此功能")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //继续请求权限
                        handlerPermission(requestCode, permissions);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.rejectPermission(permissions);
                    }
                })
                .create()
                .show();
    }

    /**
     * 申请权限的结果回调接口
     */
    public interface PermissionCallback {
        void permittedPermissions(); //允许申请的权限

        void rejectPermission(String[] rejectPermissions);//申请的权限中有未授权的权限，参数为未授权的权限集合
    }

    /**
     * 当用户点击不再询问之后，再次请求权限提示用户进入设置页手动授权
     *
     * @param permissions
     */
    public void intentSetting(String[] permissions) {
        if (!shouldShowRequestPermissionRationale(permissions)) {
            if (getFlag()) {
                showGoToSettingDialog(permissions);
            }
            saveFlag(true);
        }
    }

    /**
     * 跳到系统的设置页面
     */
    private void gotoSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 用户点击不再询问后，再次申请权限弹框
     *
     * @param permissions 请求的权限
     */
    private void showGoToSettingDialog(final String[] permissions) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("需要在设置页面手动设置权限才可使用此功能")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.rejectPermission(permissions);
                    }
                })
                .create()
                .show();
    }

    private void saveFlag(boolean flag) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(
                "userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NO_LONGER_ASK, flag);
        editor.commit();
    }

    private boolean getFlag() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(
                "userInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(NO_LONGER_ASK, false);
    }

    public void showRejectMsg() {
        if (fragment != null) {
            Toast.makeText(fragment.getActivity(), "授权后,才能使用此功能", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "授权后,才能使用此功能", Toast.LENGTH_SHORT).show();
        }
    }
}
