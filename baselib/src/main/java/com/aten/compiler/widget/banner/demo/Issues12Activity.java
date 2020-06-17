//package com.aten.compiler.widget.banner.demo;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.FrameLayout;
//
//import com.bannerlayout.animation.MeizuBannerTransformer;
//import com.bannerlayout.widget.BannerLayout;
//import com.bannerlayout.widget.BannerViewPager;
//import com.bannersimple.R;
//
//import static com.bannersimple.bean.SimpleData.initModel;
//
//
///**
// * by y on 25/07/2017.
// * <p>魅族效果demo
// * Issues sample : https://github.com/7449/BannerLayout/issues/12
// */
//
//public class Issues12Activity extends AppCompatActivity {
//
//    private static final String TAG = Issues12Activity.class.getSimpleName();
//
//    private BannerLayout issues_13_banner;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_issues_12);
//        issues_13_banner = (BannerLayout) findViewById(R.id.issues_12_banner);
//        issues_13_banner.setClipChildren(false);
//        issues_13_banner
//                .initPageNumView()
//                .initTips()
//                .setTipsDotsSelector(R.drawable.banner)
//                .setPageNumViewMargin(12, 12, 12, 12)
//                .setBannerTransformer(new MeizuBannerTransformer())
//                .initListResources(initModel())
//                .switchBanner(true);
//        final BannerViewPager viewPager = issues_13_banner.getViewPager();
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)viewPager.getLayoutParams();
//        if (layoutParams!=null){
//            layoutParams.leftMargin = 50;
//            layoutParams.rightMargin = 50;
//        }
//
//        viewPager.setOffscreenPageLimit(3);
//        Handler handler = new Handler();
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                viewPager.beginFakeDrag();
//                viewPager.fakeDragBy(1.0f);
//                viewPager.endFakeDrag();
//            }
//        };
//        handler.postDelayed(r, 10);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (issues_13_banner != null) {
//            issues_13_banner.clearBanner();
//        }
//    }
//
//}
