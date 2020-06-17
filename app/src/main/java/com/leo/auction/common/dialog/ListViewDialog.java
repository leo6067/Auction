package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.leo.auction.R;

/**
 * Created by Administrator on 2017/8/11.
 */

public class ListViewDialog extends Dialog {


    private final LayoutInflater mInflater;
    private Context mContext;
    private String mSharecode;
//    private CardAdapter mCardAdapter;

//    private ArrayList<BankCardInfo.CardListBean> mBankCardList = new ArrayList<>();
    private ListView mListView;
    private int clickNum = 0;


    public ListViewDialog(Context context) {
        super(context, R.style.dialog_style);
        mInflater = LayoutInflater.from(context);
        mContext = context;
        initView();
        initData();
    }


    private void initView() {
        View view = mInflater.inflate(R.layout.dialog_listview, null);
        setContentView(view);









        mListView = (ListView) view.findViewById(R.id.pay_way_list);

//        mCardAdapter = new CardAdapter();
//        mListView.setAdapter(mCardAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mOnChangeListener.onChange(i-1);
                clickNum = i-1;
//                mCardAdapter.notifyDataSetChanged();
            }
        });



    }




    private void initData() {

//        mSharecode = BaseSharePerence.getInstance(mContext).getMemberInfo().getSharecode();


//        getCardList();
    }







//    /**
//     * 获取银行卡列表
//     */
//    private void getCardList() {
//
//
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("shareCode", mSharecode);
//
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
//                            mBankCardList.clear();
//                            mBankCardList.addAll(bankCardInfo.getCardList());
//                            mCardAdapter.notifyDataSetChanged();
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//    }
//
//
//    private String bankType;
//
//
//    private class CardAdapter extends BaseAdapter
//    {
//
//        @Override
//        public int getCount() {
//            return mBankCardList.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup viewGroup) {
//            view = LayoutInflater.from(mContext).inflate(R.layout.pay_way_dialog_item,null);
//
//            BankCardInfo.CardListBean cardInfo = mBankCardList.get(position);
//
//            ImageView mBankCardImg = (ImageView) view.findViewById(R.id.pay_way_img);
//            TextView mBankCardName = (TextView) view.findViewById(R.id.pay_way_num);
//            CheckBox mBankCardType = (CheckBox) view.findViewById(R.id.pay_way_check);
//
//            Glide.with(mContext).load(cardInfo.getLogo()).into(mBankCardImg);
////            GlideUtils.displayImage(mContext, cardInfo.getLogo(), mBankCardImg);
//             mBankCardName.setText(cardInfo.getBankName());
//            if (cardInfo.getCardType() == 1) {
//                bankType ="信用卡";
//
//            } else {
//                bankType ="储蓄卡";
//            }
//
//
//            String bankCard = cardInfo.getBankCard();
//
//            String substring = bankCard.substring(bankCard.length() - 4);
//            mBankCardName.setText(cardInfo.getBankName() + bankType + "(" + substring + ")");
//
//            if (position == clickNum)
//            {
//                mBankCardType.setChecked(true);
//            }else{
//                mBankCardType.setChecked(false);
//            }
//
//
//            return view;
//        }
//    }




private onChangeListener mOnChangeListener;


    public interface onChangeListener
    {
        void onChange(int position);
    }



    public void setOnchangeListener(onChangeListener listener)
    {
        mOnChangeListener = listener;
    }





}
