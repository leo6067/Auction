package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.net.HttpRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UpdateNickNameActivity extends BaseActivity {


    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.stb_sure_update)
    SuperButton mStbSureUpdate;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_update_nick_name);
    }


    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("修改昵称");
    }

    @OnClick({R.id.stb_sure_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stb_sure_update:
                sureUpdate();
                break;
        }
    }

    //确认更新
    private void sureUpdate() {
        if (EmptyUtils.isEmpty(etContent.getText().toString().trim())) {
            ToastUtils.showShort("请输入要修改的昵称！");
            return;
        }

        showWaitDialog();
        BaseModel.httpUserName(etContent.getText().toString().trim(), new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();

                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()){
                    ToastUtils.showShort("修改成功");
                    Intent intent = new Intent();
                    intent.putExtra("nickName", etContent.getText().toString().trim());
                    setResult(RESULT_OK, intent);
                    goFinish();
                }else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }

            }
        });

    }

    public static void newIntance(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, UpdateNickNameActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

}
