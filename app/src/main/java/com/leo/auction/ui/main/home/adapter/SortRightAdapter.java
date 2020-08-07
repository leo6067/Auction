package com.leo.auction.ui.main.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.utils.GlideUtils;
import com.zhy.http.okhttp.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.app.ui.main.home.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/10
 * 描    述： 具体分类
 * 修    改：
 * ===============================================
 */
public class SortRightAdapter extends BaseMultiItemQuickAdapter<SortLeftModel.DataBean.ChildrenBean, BaseViewHolder> {

    private Context mContext;

    private RightItemClick mRightItemClick;



    public void setRightInter(RightItemClick rightInter){
        mRightItemClick = rightInter;
    }

    public SortRightAdapter(Context context, @Nullable List<SortLeftModel.DataBean.ChildrenBean> data) {
        super(data);
        // 绑定 layout 对应的 type
        addItemType(Constants.Var.LAYOUT_TYPE_HEAD, R.layout.item_sort_left);
        addItemType(Constants.Var.LAYOUT_TYPE, R.layout.item_image_text);
        mContext = context;
    }






    @Override
    protected void convert(@NonNull BaseViewHolder helper, final SortLeftModel.DataBean.ChildrenBean item) {
//        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
//
//        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_sort_left,null);
//        TextView textView = inflate.findViewById(R.id.sort_name);
//        textView.setText(item.getName());
//
//        ImageTextAdapter imageTextAdapter = new ImageTextAdapter(item.getChildren());
//        recyclerView.setAdapter(imageTextAdapter);
//
//
//        imageTextAdapter.addHeaderView(inflate);


        switch (helper.getItemViewType()) {
            case Constants.Var.LAYOUT_TYPE_HEAD:
                helper.setText(R.id.sort_name, item.getName());
                TextView textView = helper.getView(R.id.textView);
                textView.setVisibility(View.GONE);
                break;
            case Constants.Var.LAYOUT_TYPE:
                ImageView imageView = helper.getView(R.id.imageView);
                TextView nameText = helper.getView(R.id.textView);
                if (item.getPosition()==-5){
                    imageView.setVisibility(View.INVISIBLE);
                    nameText.setText("");
                }else {
                    nameText.setText(item.getName());
                    imageView.setVisibility(View.VISIBLE);
                    GlideUtils.loadImg(mContext,item.getIcon(),imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRightItemClick.ItemClick(item);
                        }
                    });
                }

                break;
            default:
                break;
        }

    }

    public interface RightItemClick{
        void ItemClick(SortLeftModel.DataBean.ChildrenBean item);
    }

}
