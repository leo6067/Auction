package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.mvp.BaseModel;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateNickNameActivity extends BaseActivity {



    @BindView(R.id.et_content)
    EditText etContent;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_update_nick_name);
    }



    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.stb_sure_update})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.stb_sure_update:
                sureUpdate();
                break;
        }
    }

    //确认更新
    private void sureUpdate() {
        if (EmptyUtils.isEmpty(etContent.getText().toString().trim())){
            ToastUtils.showShort("请输入要修改的昵称！");
            return;
        }
//        BaseModel.sendSettingRequest(TAG, "", "", etContent.getText().toString().trim(),"", new CustomerJsonCallBack_v1<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                showShortToast(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                showShortToast("设置成功");
//                Intent intent=new Intent();
//                intent.putExtra("nickName",etContent.getText().toString().trim());
//                setResult(RESULT_OK,intent);
//                goFinish();
//            }
//        });
    }

    public static void newIntance(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, UpdateNickNameActivity.class);
        activity.startActivityForResult(intent,requestCode);
    }
}
