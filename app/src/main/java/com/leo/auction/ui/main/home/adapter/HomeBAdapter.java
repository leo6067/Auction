package com.leo.auction.ui.main.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.autoTextView.AutofitTextView;
import com.aten.compiler.widget.transformation.RoundedCornersTransformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.Globals;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * ================================================
 * 项目名称：Android-frame
 * 包   名：com.demo.android_frame.ui.home.adapter
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/10/17
 * 描   述：
 * ================================================
 */
public class HomeBAdapter extends CommonAdapter<HomeListModel.DataBean> {



    public HomeBAdapter(Context context, List<HomeListModel.DataBean> datas) {
        super(context, R.layout.item_home_list_all, datas);
    }

    @Override
    protected void convert(ViewHolder holder, HomeListModel.DataBean s, int position) {


        Globals.log("xxxxxxx  DataBean");

    }
}
