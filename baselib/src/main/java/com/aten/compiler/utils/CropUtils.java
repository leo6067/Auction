//package com.aten.compiler.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.FileProvider;
//import com.aten.compiler.R;
//import com.aten.compiler.base.BaseGlobal;
//import com.yalantis.ucrop.UCrop;
//import com.yanzhenjie.permission.Setting;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
///**
// * ================================================
// * 项目名称：PJHAndroidFrame
// * 包    名：com.frame.pjh_core.utils
// * 作    者：彭俊鸿
// * 邮    箱：1031028399@qq.com
// * 版    本：1.0
// * 创建日期：2018/11/5
// * 描    述：图片裁剪工具类
// * ================================================
// */
//public class CropUtils {
//    private UCrop uCrop;
//
//    /**
//     * 内部类实现单例模式
//     * 延迟加载，减少内存开销
//     */
//    private static class SingletonHolder {
//        private static CropUtils instance = new CropUtils();
//    }
//
//    /**
//     * 私有的构造函数
//     */
//    private CropUtils() {}
//
//    public static CropUtils getInstance() {
//        return CropUtils.SingletonHolder.instance;
//    }
//
//    //初始化裁剪的参数
//    public void initCropOption(Context context,Uri imageUri){
//        //裁剪之后的图片存储文件名
//        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
//        long time = System.currentTimeMillis();
//        String imageName = timeFormatter.format(new Date(time));
//
//        File file=new File(BaseGlobal.getImageCrop(),imageName + ".jpg");
//        if (!file.exists()) {
//            file.getParentFile().mkdirs();
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Uri destinationUri = Uri.fromFile(file);
//
//        uCrop = UCrop.of(imageUri, destinationUri);
//        uCrop=basisConfig(uCrop);
//        uCrop=advancedConfig(context,uCrop);
//    }
//
//    //ucrop的基本配置
//    private UCrop basisConfig(@NonNull UCrop uCrop) {
//        uCrop = uCrop.withAspectRatio(1, 1);
//        return uCrop;
//    }
//    //设置ucrop的一些参数
//    private UCrop advancedConfig(Context context,@NonNull UCrop uCrop) {
//        UCrop.Options options = new UCrop.Options();
//        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
//        options.setCompressionQuality(90);
//
//        options.setFreeStyleCropEnabled(true);
//        //设置隐藏底部容器，默认显示
//        options.setHideBottomControls(true);
//        //设置toolbar颜色
//        options.setToolbarColor(ActivityCompat.getColor(context, R.color.colorPrimary));
//        //设置状态栏颜色
//        options.setStatusBarColor(ActivityCompat.getColor(context, R.color.colorPrimaryDark));
//        //开始设置
//        //设置最大缩放比例
//        options.setMaxScaleMultiplier(5);
//        //设置图片在切换比例时的动画
//        options.setImageToCropBoundsAnimDuration(666);
//        //设置裁剪窗口是否为椭圆
//        //options.setCircleDimmedLayer(true);
//        //设置是否展示矩形裁剪框
//        // options.setShowCropFrame(false);
//        //设置裁剪框横竖线的宽度
//        //options.setCropGridStrokeWidth(20);
//        //设置裁剪框横竖线的颜色
//        //options.setCropGridColor(Color.GREEN);
//        //设置竖线的数量
//        //options.setCropGridColumnCount(2);
//        //设置横线的数量
//        //options.setCropGridRowCount(1);
//        //设置裁剪图片可操作的手势
//        //options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
//
//        /*
//        This sets max size for bitmap that will be decoded from source Uri.
//        More size - more memory allocation, default implementation uses screen diagonal.
//
//        options.setMaxBitmapSize(640);
//        * */
//       /*
//        Tune everything (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧
//        options.setDimmedLayerColor(Color.CYAN);
//        options.setToolbarCropDrawable(R.drawable.your_crop_icon);
//        options.setToolbarCancelDrawable(R.drawable.your_cancel_icon);
//        // Color palette
//        options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.your_color_res));
//        options.setToolbarWidgetColor(ContextCompat.getColor(this, R.color.your_color_res));
//        options.setRootViewBackgroundColor(ContextCompat.getColor(this, R.color.your_color_res));
//        // Aspect ratio options
//        options.setAspectRatioOptions(1,
//            new AspectRatio("WOW", 1, 2),
//            new AspectRatio("MUCH", 3, 4),
//            new AspectRatio("RATIO", CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO),
//            new AspectRatio("SO", 16, 9),
//            new AspectRatio("ASPECT", 1, 1));
//       */
//        return uCrop.withOptions(options);
//    }
//
//    //将string地址转换成uri地址
//    public Uri StrToUri(Context context,String imgPath){
//        //将原图转成Uri格式
//        Uri imageUri = null;
//        if (Build.VERSION.SDK_INT >= 24) {
//            imageUri = FileProvider.getUriForFile(context, Setting.fileProviderAuthority, new File(imgPath));//通过FileProvider创建一个content类型的Uri
//        } else {
//            imageUri = Uri.fromFile(new File(imgPath));
//        }
//        return imageUri;
//    }
//
//    //跳转裁剪页面
//    public void startCropActivity(Activity activity){
//        uCrop.start(activity);
//    }
//
//    //跳转裁剪页面
//    public void startCropFragment(Context context, Fragment fragment){
//        uCrop.start(context, fragment);
//    }
//
//}
