package com.shoes.position.ui.mine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity implements OnClickListener {

    @BindView(R.id.head_img)
    SelectableRoundedImageView headImg;
    @BindView(R.id.mine_head_img)
    LinearLayout mineHeadImg;
    @BindView(R.id.nike_name)
    TextView nikeName;
    @BindView(R.id.mine_nike_name)
    LinearLayout mineNikeName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.mine_phone_number)
    LinearLayout minePhoneNumber;
    DialogPlus dialog = null;

    protected OnCheckCameraPermission mOnCheckCameraPermission;
    private OnCheckStoragePermission mOnCheckStoragePermission;
    private File sdcardTempFile;
    protected final int PERMISSIONS_WRITE_STORAGE = 1;
    public final int PERMISSIONS_CAMERA = 2;

    private boolean isToSettings = false;
    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_personal_info, "个人信息");
    }

    @Override
    protected void setLogic() {

    }

    @OnClick({R.id.mine_head_img,R.id.mine_nike_name,R.id.mine_phone_number})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_head_img:
                showSelect();
                break;
            case R.id.mine_nike_name:

                break;
            case R.id.mine_phone_number:
                break;
        }
    }

    public void showSelect(){
        dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.dialog_select_img))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setContentWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setOnClickListener(this)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        dialog.show();
    }

    @Override
    public void onClick(DialogPlus dialog, View view) {
        switch (view.getId()){
            case R.id.photograph:
                checkCameraPression(new OnCheckCameraPermission() {
                    @Override
                    public void onCheckCameraPression(boolean haspermission) {
                        if(haspermission){
                            takePhoto();
                        }else {
                            //拒绝权限，弹出提示框。
                            showExceptionDialog();
                        }
                    }
                });
                break;
            case R.id.select_img:
                checkStoragePression(new OnCheckStoragePermission() {
                    @Override
                    public void onCheckStoragePression(boolean haspermission) {
                        if(haspermission){
                            selectFromGallery();
                        }else {
                            //拒绝权限，弹出提示框。
                            showExceptionDialog();
                        }
                    }
                });
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
        }
    }

    /**
     * 发生没有权限等异常时，显示一个提示dialog.
     */
    private void showExceptionDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("提示")
                .setMessage("该相册需要赋予访问存储的权限，请到“设置”>“应用”>“权限”中配置权限。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                startAppSettings();
                isToSettings = true;
            }
        }).show();
    }

    /**
     * 启动应用的设置
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 权限申请
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean permit = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        switch (requestCode) {
            case PERMISSIONS_CAMERA:
                if (mOnCheckCameraPermission != null) {
                    mOnCheckCameraPermission.onCheckCameraPression(permit);
                }
                break;
            case PERMISSIONS_WRITE_STORAGE:
                if (mOnCheckStoragePermission != null) {
                    mOnCheckStoragePermission.onCheckStoragePression(permit);
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/myPhoto" + SystemClock.currentThreadTimeMillis() + ".jpg";
        //         String path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DeviceUtils.getPackageName(GarageApp.getAppContext()) + "/img_dow_"+SystemClock.currentThreadTimeMillis() + ".jpg";
        sdcardTempFile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, "com.shoes.position", sdcardTempFile));
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(sdcardTempFile));
        }
        startActivityForResult(intent, 101);
    }

    /**
     * 从相册选取照片
     */
    private void selectFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 102);
    }

    /**
     * android6.0动态权限申请：SD卡读写权限
     **/
    public void checkStoragePression(OnCheckStoragePermission callback) {
        mOnCheckStoragePermission = callback;
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCamerapression = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkCamerapression != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, PERMISSIONS_WRITE_STORAGE);
                return;
            }
            mOnCheckStoragePermission.onCheckStoragePression(true);
            return;
        }
        mOnCheckStoragePermission.onCheckStoragePression(true);
    }

    /**
     * android6.0动态权限申请：相机使用权限
     **/
    public void checkCameraPression(OnCheckCameraPermission callback) {
        mOnCheckCameraPermission = callback;
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCamerapression = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (checkCamerapression != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_CAMERA);
                return;
            }
            mOnCheckCameraPermission.onCheckCameraPression(true);
            return;
        }
        mOnCheckCameraPermission.onCheckCameraPression(true);
    }

    /**
     * 读写SD卡权限申请后回调
     **/
    public interface OnCheckStoragePermission {
        /**
         * @param haspermission true 允许  false 拒绝
         **/
        void onCheckStoragePression(boolean haspermission);
    }

    /**
     * 拍照权限申请后回调
     **/
    public interface OnCheckCameraPermission {
        /**
         * @param haspermission true 允许  false 拒绝
         **/
        void onCheckCameraPression(boolean haspermission);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            /**
             * 根据路径获取数据
             * */
            case 101:
                if (resultCode == RESULT_OK && sdcardTempFile.exists()) {
                    try {
                        //如果需要上传照片到服务器，上传方法写这里既可
                        FileInputStream inputStream = new FileInputStream(sdcardTempFile);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        headImg.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 102:
                ContentResolver contentResolver = getContentResolver();
                if (data != null) {
                    //如果需要把选取的图片进行上传服务器，请把下面代码注释解开，因为这样 才能获取到文件URI转的文件路径~方便上传的时候使用
                    //Uri uri = data.getData();
                    //使用工具类获取绝对路径
                    //String path = getFilePathFromContentUri(uri, contentResolver);
                    //File sdcardTempFile = new File(path);
                    //if (resultCode == RESULT_OK && sdcardTempFile.exists()) {
                    if (resultCode == RESULT_OK) {
                        //显示在我们UI上
                        Bitmap bitmap = null;
                        try {
                            //如果需要上传照片到服务器，上传方法写这里既可
                            bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(data.getData()));
                            headImg.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
