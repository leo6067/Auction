package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.leo.auction.R;

/**
 * Created by Leo on 2017/8/10.
 *
 *
 *
 * 包含多种支付方式以及支付的密码的监听
 */

public class PayDialog extends Dialog implements PassWordDialog.OnButtonClickListener, ListViewDialog.onChangeListener {

    private final LayoutInflater mInflater;
    private TextView mMoneyTv;
    private TextView mBankTv;


    private String money;
    private String bank;
    private TextView mBankOk;
    private View mView;

    private Context mContext;
    private String mSharecode;
//    private BankCardInfo.CardListBean mCardInfo;
//    private MemberInfo mMemberInfo;
    private PassWordDialog mPassWordDialog;
//    private List<BankCardInfo.CardListBean> mCardInfoList;
    private ListViewDialog mPayWayDialog;


    public PayDialog(Context context, String money) {

        super(context, R.style.dialog_style);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        initView();
        this.money = money;

        initData();

        initListener();
    }

    private void initView() {

        mView = mInflater.inflate(R.layout.dialog_pay, null);
        setContentView(mView);

        mMoneyTv = (TextView) mView.findViewById(R.id.pay_dialog_money);
        mBankTv = (TextView) mView.findViewById(R.id.pay_dialog_bank);
        mBankOk = (TextView) mView.findViewById(R.id.bank_ok);

    }

    private void initData() {
//        mMemberInfo = BaseSharePerence.getInstance(mContext).getMemberInfo();
//        mSharecode = mMemberInfo.getSharecode();
        mMoneyTv.setText("¥ "+money);


//        getCardList();

        mPayWayDialog = new ListViewDialog(mContext);
        mPayWayDialog.setOnchangeListener(this);

    }


    private void initListener() {
        mView.findViewById(R.id.pay_dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        //选择支付银行
        mView.findViewById(R.id.pay_way).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPayWayDialog.show();
            }
        });


        //银行卡支付
        mBankOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                creationOrder(money);
                payPassWord();
                dismiss();
            }
        });
    }


    private void payPassWord() {
//        if (mMemberInfo.getPayPassword() == null) {
//            ToastUtils.showShort(mContext, "请前往账户设置设置支付密码！");
//            return;
//        }


        mPassWordDialog = new PassWordDialog(mContext, "输入支付密码", 0);
        mPassWordDialog.setOnButtonClickListener(this);

        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);

        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mPassWordDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth() - 100); //设置宽度
        mPassWordDialog.getWindow().setAttributes(lp);
        mPassWordDialog.show();
    }


    @Override
    public void onOk(String pwd, int positon) {

//        if (mMemberInfo.getPayPassword().equals(MD5Utils.stringToMD5(pwd))) {
//
//            creationOrder(money);
//        } else {
//            ToastUtils.showShort(mContext, "密码错误！");
//        }


    }

    @Override
    public void onCancel() {
        mPassWordDialog.dismiss();
    }

    @Override
    public void onChange(int position) {

    }


//    private String  bankType ;
//    /**
//     * 获取银行卡列表
//     */
//    private void getCardList() {
//
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("shareCode", mSharecode);
//        HashMap<String, String> requestMap = Token.getRequestMap(data);
//
//        OkHttpUtils.post()
//                .url(Constants.cardList_url)
//                .params(requestMap)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        try {
//                            JSONObject json = JSONObject.parseObject(response);
//                            String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
//                            BankCardInfo bankCardInfo = JSONObject.parseObject(resultData, BankCardInfo.class);
//
//                            mCardInfoList = bankCardInfo.getCardList();
//                            mCardInfo = mCardInfoList.get(0);
//
//                            String bankCard = mCardInfo.getBankCard();
//
//                            String substring = bankCard.substring(bankCard.length() - 4);
//
//                            if (mCardInfo.getCardType() == 1) {
//                                bankType ="信用卡";
//
//                            } else {
//                                bankType ="储蓄卡";
//                            }
//                            mBankTv.setText(mCardInfo.getBankName() + bankType + "(" + substring + ")");
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//    }
//
//
//    @Override
//    public void onChange(int position) {
//        mCardInfo = mCardInfoList.get(position);
//        String bankCard = mCardInfo.getBankCard();
//
//        String substring = bankCard.substring(bankCard.length() - 4);
//
//        if (mCardInfo.getCardType() == 1) {
//            bankType ="信用卡";
//
//        } else {
//            bankType ="储蓄卡";
//        }
//
//        mBankTv.setText(mCardInfo.getBankName() + bankType + "(" + substring + ")");
//    }
}
