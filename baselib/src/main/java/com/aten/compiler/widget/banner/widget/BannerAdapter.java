package com.aten.compiler.widget.banner.widget;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.aten.compiler.widget.banner.listener.BannerModelCallBack;
import com.aten.compiler.widget.banner.listener.ImageLoaderManager;
import com.aten.compiler.widget.glide.GlideUtils;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;


/**
 * by y on 2016/10/24.
 */

class BannerAdapter extends PagerAdapter {
    private List<? extends BannerModelCallBack> imageList = null;

    private boolean isGuide = false;
    private int error_image;
    private int place_image;
    private ImageLoaderManager imageLoaderManage = null;
    private OnBannerImageClickListener imageClickListener = null;
    private RequestOptions requestOptions = null;

    BannerAdapter() {
    }

    @Override
    public int getCount() {
        return isGuide ? imageList.size() : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View img;
        if (imageLoaderManage == null) {
            img = new ImageView(container.getContext());
            GlideUtils.loadImg(imageList.get(getPosition(position)).getBannerUrl(),(ImageView) img,place_image,error_image);
        } else {
            img = imageLoaderManage.display(container, imageList.get(getPosition(position)));
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageClickListener != null) {
                    imageClickListener.onBannerClick(v, getPosition(position), imageList.get(getPosition(position)));
                }
            }
        });
        container.addView(img);
        return img;
    }

    void setGuide(boolean guide) {
        this.isGuide = guide;
    }

    void addAll(List<? extends BannerModelCallBack> list) {
        imageList = list;
    }

    private int getPosition(int position) {
        return position % imageList.size();
    }

    interface OnBannerImageClickListener {
        void onBannerClick(View view, int position, Object model);
    }

    void setErrorImage(int error_image) {
        this.error_image = error_image;
    }

    void setPlaceImage(int place_image) {
        this.place_image = place_image;
    }

    void setImageClickListener(OnBannerImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    void setImageLoaderManage(ImageLoaderManager imageLoaderManage) {
        this.imageLoaderManage = imageLoaderManage;
    }
}
