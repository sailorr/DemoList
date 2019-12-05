package me.sailor.libcommon.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

import me.sailor.libcommon.R;

/**
 * @author Administrator on2019/5/15 17:13
 * @desc
 */
public class PermissionManager {

    public PermissionManager() {
    }
     interface GetPermissionCallback {
        void getPermissionSuccess();
//        void getPermissionFail();
    }

    public static PermissionManager getInstance() {
        return PermissionManagerHolder.instance;
    }

    static class PermissionManagerHolder {
        static final PermissionManager instance = new PermissionManager();
    }

    public void requestPermission(final Context context, final String permission, final GetPermissionCallback callback) {
        AndPermission.with(context)
                .runtime()
                .permission(permission)
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, final RequestExecutor executor) {
                        //若没有该权限会走该回调，拥有该权限直接走onGranted
//                        new AlertDialog.Builder(context)
//                                .setTitle("权限申请提醒")
//                                .setMessage("需要使用"+Permission.transformText(context, data).toString()+"权限")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        executor.execute();
//                                        dialog.dismiss();
//                                    }
//                                })
//                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        executor.cancel();
//                                        dialog.dismiss();
//                                    }
//                                }).show();
                        executor.execute();

                    }
                })
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //用该权限
                        callback.getPermissionSuccess();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //没有该权限
//                        callback.getPermissionFail();
                        if (AndPermission.hasAlwaysDeniedPermission(context, data)) {
                            Toast.makeText(context, "您已经关闭权限，若需要请前往设置开启", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "您拒绝给与权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .start();
    }

    public void requestPermission(final Context context, final String[] permission, final GetPermissionCallback callback) {
        AndPermission.with(context)
                .runtime()
                .permission(permission)
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, final RequestExecutor executor) {
                        executor.execute();
                    }
                })
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //用该权限
                        callback.getPermissionSuccess();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //没有该权限
//                        callback.getPermissionFail();
                        if (AndPermission.hasAlwaysDeniedPermission(context, data)) {
                            Toast.makeText(context, "您已经关闭"+Permission.transformText(context,data)+"权限，若需要请前往设置开启", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "您拒绝给与权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .start();
    }
}
