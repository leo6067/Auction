package com.leo.auction.common.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import com.bumptech.glide.request.Request;

import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.leo.auction.R;


import java.util.List;


/**
 * Created by qwe on 2017/12/18.
 */

public class RollPagerAdapter extends StaticPagerAdapter {
    private List<String> mUrlList;
    public RollPagerAdapter(List<String> mUrlList){
        this.mUrlList = mUrlList;
    }
    @Override
    public View getView(ViewGroup viewGroup, int position) {
       final ImageView view = new ImageView(viewGroup.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Glide.with(viewGroup.getContext().getApplicationContext())
                .load(mUrlList.get(position))
                .error(R.drawable.homebanner)
                .into(new Target<Drawable>() {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        view.setBackground(resource);


                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }
        });
        return view;
    }
    @Override
    public int getCount() {
        return mUrlList.size();
    }
}
