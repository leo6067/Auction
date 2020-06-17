package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.leo.auction.R;

import java.util.HashMap;

/**
 * Created by Leo on 2017/12/1.
 */

public class InviteDialog extends Dialog {


    private Context mContext;
    private LayoutInflater mInflater;
    private TextView mTitelTv;
    private TextView mHintTv;


    private TextView mInviteName;
    private EditText mEditText;
    private String mPhoneStr;
    private TextView mInviteCommit;

    private boolean hasPhone = false;


    private InviteDialogInter mDialogInter;


    public interface InviteDialogInter
    {
        void success();
    }





    public void setInviteInter(InviteDialogInter inviteInter)
    {

        mDialogInter = inviteInter;

    }




    public InviteDialog(Context context, HashMap<String, String> parameterList) {
        super(context, R.style.dialog_style);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initView();
        initData(parameterList);

    }


    private void initView() {
        View view = mInflater.inflate(R.layout.dialog_invite, null);
        setContentView(view);


        mTitelTv = (TextView) view.findViewById(R.id.invite_title);

        mHintTv = (TextView) view.findViewById(R.id.invite_hint);
        mInviteName = (TextView) view.findViewById(R.id.invite_invite);
        mInviteCommit = (TextView) view.findViewById(R.id.invite_ok); //提交


        mEditText = (EditText) view.findViewById(R.id.invite_edit);


    }


    private void initData(HashMap<String, String> parameterList) {


//        mTitelTv.setText(parameterList.get("dialogTitle"));

      final  String shareCode = parameterList.get("shareCode");


        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                mPhoneStr = editable.toString();

                if (mPhoneStr.length() == 11) {

//                    bindRequest(mPhoneStr);
                }
            }
        });


        mInviteCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                    Globals.log("log bindRequest onClick"+hasPhone );
                if (hasPhone) {
//                    commitRequest(shareCode);
                }

            }
        });

    }


//    void bindRequest(String phone) {
//        HashMap<String, Object> data = new HashMap();
//        data.put("phone", phone);
//        HashMap<String, String> requestMap = Token.getRequestMap(data);
////        Globals.log("log bindRequest 0", data.toString());
//        OkHttpUtils.post().url(Constants.getRealNameByPhone_url).params(requestMap).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
////                Globals.log("log bindRequest", response);
//                try {
//                    JSONObject json = JSONObject.parseObject(response);
//                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
//
//                    JSONObject object = JSONObject.parseObject(resultData);
//                    if ("success".equals(object.getString("status"))) {
//                        mInviteName.setText(object.getString("realName"));
//                        mInviteName.setTextColor(mContext.getResources().getColor(R.color.dodgerblue));
//                        hasPhone = true;
//
//                    } else {
//                        mInviteName.setText(object.getString("message"));
//                        mInviteName.setTextColor(mContext.getResources().getColor(R.color.red));
//                        ToastUtils.showShort(mContext, object.getString("message"));
//                        hasPhone = false;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                }
//            }
//        });
//    }
//
//
//    void commitRequest(String shareCode) {
//        HashMap<String, Object> data = new HashMap();
//        data.put("shareCode", shareCode);
//        data.put("phone", mPhoneStr);
//        HashMap<String, String> requestMap = Token.getRequestMap(data);
//        OkHttpUtils.post().url(Constants.mengxinEvolve_url).params(requestMap).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                try {
//                    JSONObject json = JSONObject.parseObject(response);
//                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));//响应体需要解码,本地json数据
//
//                    JSONObject object = JSONObject.parseObject(resultData);
//                    if ("success".equals(object.getString("status"))) {
//                        dismiss();
//
//                        mDialogInter.success();
//                        ToastUtils.showShort(mContext, object.getString("message"));
//                    } else {
//                        dismiss();
//                        ToastUtils.showShort(mContext, object.getString("message"));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                }
//            }
//        });
//    }


}
