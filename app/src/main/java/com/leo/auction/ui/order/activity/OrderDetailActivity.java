package com.leo.auction.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aten.compiler.base.BaseActivity;
import com.leo.auction.R;

public class OrderDetailActivity extends BaseActivity {

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order_detail);
    }

    public static void newIntance(Fragment fragment, String id, int type, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), OrderDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void newIntance(Context context, String id, String type) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
