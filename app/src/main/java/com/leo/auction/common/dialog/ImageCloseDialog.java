package com.leo.auction.common.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leo.auction.R;
import com.leo.auction.base.BaseDialog;

import java.util.HashMap;

/**
 * Created by Leo on 2017/11/5.
 */

public class ImageCloseDialog extends BaseDialog {


    private ImageView mImg;
    private ImageView mClose;
    Context mContext;


    ImageCloseDialogListener mCloseDialogListener;
    View view;
    private String mString;

    public interface ImageCloseDialogListener {
        void imageCloseClick(String string);
    }


    public void setCloseDialogListener(ImageCloseDialogListener dialogListener) {
        mCloseDialogListener = dialogListener;
    }

    public ImageCloseDialog(Context context, HashMap<String, Object> hashMap) {
        super(context);
        mContext = context;
        initView(context);
        initData(hashMap);
    }

    @Override
    public void initView(Context context) {
        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.dialog_image_close, null);
            setContentView(view);
        }
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImg = (ImageView) view.findViewById(R.id.img_id);
        mClose = (ImageView) view.findViewById(R.id.close_id);


    }

    @Override
    public void initData(HashMap<String, Object> hashMap) {

        try {
            int imgID = (int) hashMap.get("img");
            int imgClose = (int) hashMap.get("imgClose");
            final Class toActivity = (Class) hashMap.get("class");
            mString = (String) hashMap.get("weChat");


            if (toActivity !=null)
            {
                mClose.setVisibility(View.GONE);
            }else {
                mClose.setVisibility(View.VISIBLE);
            }




            mImg.setBackgroundResource(imgID);
            mClose.setBackgroundResource(imgClose);


            mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (toActivity != null) {
                        mContext.startActivity(new Intent(mContext, toActivity));
                        dismiss();
                    }

                    if (mString != null) {
                        mCloseDialogListener.imageCloseClick(mString);
                    }
                }
            });


            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
