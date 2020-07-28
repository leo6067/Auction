package com.aten.compiler.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aten.compiler.R;
import com.aten.compiler.utils.KeyboardUtils;
import com.aten.compiler.widget.loadingView.KProgressHUD;
import com.aten.compiler.widget.title.OnTitleBarListener;
import com.aten.compiler.widget.title.TitleBar;

import com.blankj.utilcode.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.base
 * Created by 彭俊鸿 on 2018/5/23.
 * e-mail : 1031028399@qq.com
 * fragment基类
 */

public abstract class BaseFragment extends Fragment {
    public final String TAG = this.getClass().getSimpleName();
    public boolean isVisibleToUser;//当前页面是否可见
    private boolean isViewInitialized;//是否初始化过view
    public boolean isDataInitialized;//是否初始化过数据
    private boolean isLazyLoadEnabled = false;//是否开启懒加载 默认关闭
    public KProgressHUD hud;
    private Unbinder unbinder;
    protected TitleBar mTitleBar;

    /*
     *是否开启懒加载 调用该方法开启
     */
    public void enableLazyLoad() {
        isLazyLoadEnabled = true;
    }

    /* enableDataInitialized 是否开启数据已初始化的功能 当viewpager中fragment未被回收掉时
     *是否重新加载数据isDataInitialized：true直接使用数据 false 重新加载数据
     *false：一般使用在不管什么情况 在切换fragment时都需要重新请求数据。
     * 默认为true
     */
    public void enableDataInitialized() {
        isDataInitialized = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTitle(view);
        if (!isLazyLoadEnabled) {
            initData();
            initEvent();
        } else {
            isViewInitialized = true;
//            if (savedInstanceState != null){
//                onRestoreInstanceState(savedInstanceState);
//            }
            if (isDataInitialized) {
                initData();
                initEvent();
            } else {
                checkIfLoadData();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    //设置页面布局
    public abstract int getLayoutId();

    //初始化页面控件
    public void initView(View view) {}

    //初始化头部title
    public void initTitle(View view) {
        mTitleBar = (TitleBar) view.findViewById(R.id.title_bar);
        if (mTitleBar == null) {
            return;
        }
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                leftTitleViewClick();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                rightTitleViewClick();
            }
        });
    }

    //设置标题
    public void setTitle(String text) {
        if (mTitleBar != null) {
            mTitleBar.setTitle(text);
        }
    }

    //title左侧点击事件
    public void leftTitleViewClick() {
        KeyboardUtils.hideSoftInput(mTitleBar);
        getActivity().onBackPressed();
    }

    //title左侧控件显示
    public void showLeftView() {
        if (mTitleBar != null) {
            mTitleBar.getLeftView().setVisibility(View.VISIBLE);
        } else {
            ToastUtils.showShort("控件还未初始化!");
        }
    }

    //title左侧控件隐藏
    public void hideLeftView() {
        if (mTitleBar != null) {
            mTitleBar.getLeftView().setVisibility(View.GONE);
        } else {
            ToastUtils.showShort("控件还未初始化!");
        }
    }

    //title右侧点击事件
    public void rightTitleViewClick() {
        ToastUtils.showShort("title右侧点击事件");
    }

    //title右侧控件显示
    public void showRightView() {
        if (mTitleBar != null) {
            mTitleBar.getRightView().setVisibility(View.VISIBLE);
        } else {
            ToastUtils.showShort("控件还未初始化!");
        }
    }

    //title右侧控件隐藏
    public void hideRightView() {
        if (mTitleBar != null) {
            mTitleBar.getRightView().setVisibility(View.GONE);
        } else {
            ToastUtils.showShort("控件还未初始化!");
        }
    }

    //设置title右侧控件文字
    public void setRightTitleView(String text) {
        if (mTitleBar != null) {
            mTitleBar.setRightTitle(text);
        }
    }

    //设置title右侧控件文字
    public void setRightIcon(Drawable drawable) {
        if (mTitleBar != null) {
            mTitleBar.setRightIcon(drawable);
        }
    }

    //初始化页面数据
    public void initData() {
    }

    //初始化页面交互事件
    public void initEvent() {
    }



    //显示数据加载框
    public void showWaitDialog() {
        showWaitDialog(null);
    }

    //显示数据加载框
    protected void showWaitDialog(String tip) {
        if (getContext()==null){
            return;
        }
        if (hud == null) {
            hud = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        }

        if (!TextUtils.isEmpty(tip)) {
            hud.setLabel(tip);
        }

        if (hud.isShowing()){
            return;
        }
        hud.show();
    }

    //判断数据加载框是否显示
    public boolean isWaitShow() {
        return hud.isShowing();
    }

    //隐藏数据加载框
    public void hideWaitDialog() {
        if (hud != null && hud.isShowing()) {
            hud.dismiss();
            hud=null;
        }
    }

    //检测是否需要加载数据
    private void checkIfLoadData() {
        if (isVisibleToUser && isViewInitialized && !isDataInitialized) {
            enableDataInitialized();
            initData();
            initEvent();
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataInitialized = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInitialized = false;
        OkHttpUtils.getInstance().cancelTag(TAG);//取消以Activity.this作为tag的请求
    }

    @Override
    public void onDestroy() {
        hideWaitDialog();
        if (unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }

        super.onDestroy();
        isDataInitialized = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        checkIfLoadData();
    }

    //关闭页面
    public void goFinish(){
        getActivity().finish();
        KeyboardUtils.hideSoftInput(getActivity());
    }
}
