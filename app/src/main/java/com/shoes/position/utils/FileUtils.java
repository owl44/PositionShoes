package com.shoes.position.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Created by honjane on 2016/9/11.
 */

public class FileUtils {

    private static String APP_DIR_NAME = "honjane";
    private static String FILE_DIR_NAME = "files";
    private static String mRootDir;
    private static String mAppRootDir;
    private static String mFileDir;

    public static void init() {
        mRootDir = getRootPath();
        if (mRootDir != null && !"".equals(mRootDir)) {
            mAppRootDir = mRootDir + "/" + APP_DIR_NAME;
            mFileDir = mAppRootDir + "/" + FILE_DIR_NAME;
            File appDir = new File(mAppRootDir);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            File fileDir = new File(mAppRootDir + "/" + FILE_DIR_NAME);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

        } else {
            mRootDir = "";
            mAppRootDir = "";
            mFileDir = "";
        }
    }

    public static String getFileDir(){
        return mFileDir;
    }


    public static String getRootPath() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getAbsolutePath(); // filePath:  /sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data"; // filePath:  /data/data/
        }
    }

    /**
     * 打开文件
     * 兼容7.0
     *
     * @param context     activity
     * @param file        File
     * @param contentType 文件类型如：文本（text/html）
     *                    当手机中没有一个app可以打开file时会抛ActivityNotFoundException
     */
    public static void startActionFile(Context context, File file, String contentType) throws ActivityNotFoundException {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//增加读写权限
        intent.setDataAndType(getUriForFile(context, file), contentType);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     * @param requestCode result requestCode
     */
    public static void startActionCapture(Activity activity, File file, int requestCode) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(activity, file));
        activity.startActivityForResult(intent, requestCode);
    }
    
   
    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.dt.cd.oaapplication.fileprovider", file);

        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
}


//https://www.jianshu.com/p/d85a662f2229
//
//private void uploadHeadImage() {
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow, null);
//        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
//        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
//        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
//final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
//        popupWindow.setOutsideTouchable(true);
//        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_navigation_google, null);
//        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
////popupWindow在弹窗的时候背景半透明
//final WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getActivity().getWindow().setAttributes(params);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//@Override
//public void onDismiss() {
//        params.alpha = 1.0f;
//        getActivity().getWindow().setAttributes(params);
//        }
//        });
//
//        btnCarema.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        //权限判断
//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        != PackageManager.PERMISSION_GRANTED) {
//        //申请WRITE_EXTERNAL_STORAGE权限
//        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//        WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//        } else {
//        //跳转到调用系统相机
//        gotoCamera();
//        }
//        popupWindow.dismiss();
//        }
//        });
//        btnPhoto.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        //权限判断
//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
//        != PackageManager.PERMISSION_GRANTED) {
//        //申请READ_EXTERNAL_STORAGE权限
//        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//        READ_EXTERNAL_STORAGE_REQUEST_CODE);
//        } else {
//        //跳转到相册
//        gotoPhoto();
//        }
//        popupWindow.dismiss();
//        }
//        });
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        popupWindow.dismiss();
//        }
//        });
//        }
///**
// * 跳转到相册
// */
//private void gotoPhoto() {
//        Log.d("evan", "*****************打开图库********************");
//        //跳转到调用系统图库
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
//        }
//
//@Override
//public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Log.d("evan", requestCode+"--"+permissions+"--"+grantResults);
//        gotoPhoto();
//        }
//
///**
// * 跳转到照相机
// */
//private void gotoCamera() {
//        Log.d("evan", "*****************打开相机********************");
//        //创建拍照存储的图片文件
//        tempFile = new File(FileUtils.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
//
//        //跳转到调用系统相机
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
//        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        Uri contentUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", tempFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//        } else {
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
//        }
//        startActivityForResult(intent, REQUEST_CAPTURE);
//        }
//
//@Override
//public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//        case REQUEST_CAPTURE: //调用系统相机返回
//        if (resultCode == RESULT_OK) {
//        gotoClipActivity(Uri.fromFile(tempFile));
//        }
//        break;
//        case REQUEST_PICK:  //调用系统相册返回
//        if (resultCode == RESULT_OK) {
//        Uri uri = data.getData();
//        gotoClipActivity(uri);
//        }
//        break;
//        case REQUEST_CROP_PHOTO:  //剪切图片返回
//        if (resultCode == RESULT_OK) {
//final Uri uri = data.getData();
//        if (uri == null) {
//        return;
//        }
//        String cropImagePath = FileUtils.getRealFilePathFromUri(getActivity().getApplicationContext(), uri);
//        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//        if (type == 1) {
//        imageView.setImageBitmap(bitMap);
//        } else {
////                        headImage2.setImageBitmap(bitMap);
//        }
//        //此处后面可以将bitMap转为二进制上传后台网络
//        //......
//        File file = null;
//        try {
//        file = new File(new URI(uri.toString()));
//        } catch (URISyntaxException e) {
//        e.printStackTrace();
//        }
//        OkHttpUtils.post()
//        .url(BaseURL+"/dtoa/AppN/User/uplaodHeadLogo")
//        .addParams("token", (String) SharedPreferencesHelper.getInstance().getData("token", ""))
//        .addFile("file",System.currentTimeMillis() + ".jpg",file)
//        .build()
//        .execute(new StringCallback() {
//@Override
//public void onError(Request request, Exception e) {
//        Log.e("oooooooo",e.toString());
//        }
//
//@Override
//public void onResponse(String response) {
//        Log.e("oooooooo",response);
//        }
//        });
//
//        }
//        break;
//        }
//        }
///**
// * 打开截图界面
// */
//public void gotoClipActivity(Uri uri) {
//        if (uri == null) {
//        return;
//        }
//        Intent intent = new Intent();
//        intent.setClass(getActivity(), ClipImageActivity.class);
//        intent.putExtra("type", type);
//        intent.setData(uri);
//        startActivityForResult(intent, REQUEST_CROP_PHOTO);
//        }