package com.leo.auction.ui.order.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.DesUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.LubanUtils;
import com.aten.compiler.utils.OssUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.customerDialog.BottomListDialog;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.ratingbar.BaseRatingBar;
import com.aten.compiler.widget.ratingbar.ScaleRatingBar;
import com.aten.compiler.widget.title.TitleBar;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.CommonlyUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.model.CommonModel;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.OssTokenModel;
import com.leo.auction.ui.main.home.activity.ImageShowActivity;
import com.leo.auction.ui.order.adapter.EvaluationLableAdapter;
import com.leo.auction.ui.order.adapter.PostOssEvaluationImglistAdapter;
import com.leo.auction.ui.order.model.CommitEvaluationModel;
import com.leo.auction.ui.order.model.OrderCommentInfoModel;
import com.leo.auction.utils.ossUpload.DecryOssDataModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;


/**
 * 立即评价
 */
public class OrderEvaluationActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.item_head)
    RadiusImageView mItemHead;
    @BindView(R.id.item_title)
    TextView mItemTitle;
    @BindView(R.id.item_time)
    TextView mItemTime;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.item_rate)
    ScaleRatingBar mItemRate;
    @BindView(R.id.item_label_recycler)
    CustomeRecyclerView mItemLabelRecycler;
    @BindView(R.id.item_content_et)
    EditText mItemContentEt;
    @BindView(R.id.item_img_recycler)
    CustomeRecyclerView mItemImgRecycler;
    @BindView(R.id.item_check)
    CheckBox mItemCheck;
    @BindView(R.id.stb_immediate_purchase)
    SuperButton mStbImmediatePurchase;
    private ArrayList<String> resultPaths = new ArrayList<>();//存储相册或者相机以后的图片路径
    private TreeMap<String, File> resultFileMap = new TreeMap<>();//存储压缩以后的图片路径
    private TreeMap<String, String> ossResultPaths = new TreeMap<>();//存储oss上传以后的图片路径
    private BottomListDialog publicationEvaluationDialog;


    private PostOssEvaluationImglistAdapter postImglistAdapter;//记录商品评论图片的适配器
    private DecryOssDataModel decryOssDataModel;//oss上传需要的一些参数

    private LubanUtils lubanUtils;
    private OssUtils ossUtils;

    private String orderCode;


    private String rateNum = "5";
    private EvaluationLableAdapter mEvaluationLableAdapter;

    ArrayList<String> imgList = new ArrayList<>();


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order_appraise);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    public void initView() {
        super.initView();

        mTitleBar.setTitle("发表评价");

        mItemRate.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar ratingBar, float rating) {
                rateNum = rating + "";
            }
        });


        //标签列表
//        ArrayList<CommonModel> commonModels = new ArrayList<>();
//        commonModels.add(new CommonModel("物超所值", 1, false));
//        commonModels.add(new CommonModel("物流给力", 2, false));
//        commonModels.add(new CommonModel("服务贴心", 4, false));
//        commonModels.add(new CommonModel("包装精美", 8, false));
//        commonModels.add(new CommonModel("捡到漏了", 16, false));
//        commonModels.add(new CommonModel("值得信赖", 32, false));



        ArrayList<CommonModel> commonModels =  CommonlyUsedData.getInstance().getCommonLabelList();

        mItemLabelRecycler.setHasFixedSize(true);
        mItemLabelRecycler.setLayoutManager(new GridLayoutManager(OrderEvaluationActivity.this, 3, GridLayoutManager.VERTICAL, false));
        mEvaluationLableAdapter = new EvaluationLableAdapter();
        mItemLabelRecycler.setAdapter(mEvaluationLableAdapter);
        mEvaluationLableAdapter.setNewData(commonModels);


        //图片列表
        mItemImgRecycler.setHasFixedSize(true);
        mItemImgRecycler.setLayoutManager(new LinearLayoutManager(OrderEvaluationActivity.this, LinearLayoutManager.HORIZONTAL, false));


        ArrayList<File> files = new ArrayList<>();
        File lastImg = new File("lastImg");
        files.add(lastImg);


        postImglistAdapter = new PostOssEvaluationImglistAdapter(files);
        mItemImgRecycler.setAdapter(postImglistAdapter);

        //选择图片按钮点击事件
        postImglistAdapter.setmOnLastImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicTypeDialog();
            }
        });
        //图片item点击事件
        postImglistAdapter.setmOnImgsItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(R.id.tag_1);
                ImageView[] imgViews = (ImageView[]) postImglistAdapter.getImgViews().toArray(new ImageView[postImglistAdapter.getImgViews().size()]);
                String[] imgListsData = new String[postImglistAdapter.getData().size() - 1];
                for (int i = 0; i < postImglistAdapter.getData().size() - 1; i++) {
                    imgListsData[i] = postImglistAdapter.getData().get(i).getAbsolutePath();
                }
                ImageShowActivity.startImageActivity(OrderEvaluationActivity.this, imgViews, imgListsData, position);
            }
        });
        //图片删除按钮点击事件
        postImglistAdapter.setmOnImgsItemDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = (int) v.getTag(R.id.tag_2);
//                ossUtils.deleteOssObject(item.getOssPaths().get(position), new OssUtils.OssDeleteListener() {
//                    @Override
//                    public void deleteSuccess() {
//                        postImglistAdapter.clearImgViews();
//                        postImglistAdapter.getData().remove(position);
//                        postImglistAdapter.notifyDataSetChanged();
//                    }
//                });

                postImglistAdapter.remove(position);
            }
        });

    }

    @Override
    public void initData() {
        orderCode = getIntent().getExtras().getString("orderCode");
        super.initData();
        lubanUtils = new LubanUtils();
        ossUtils = new OssUtils();
        getOrderEvaluation();
        geOssToken();
    }


    //获取详情 订单评论详情
    private void getOrderEvaluation() {
        showWaitDialog();
        OrderCommentInfoModel.httpOrderEvaluation(orderCode, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                OrderCommentInfoModel returnData = JSONObject.parseObject(resultData, OrderCommentInfoModel.class);
                if (returnData.getData() != null) {
                    ArrayList<OrderCommentInfoModel.DataBeanX> dataBeanXES = new ArrayList<>();
                    dataBeanXES.add(returnData.getData());

                }

                initUi(returnData.getData());
            }
        });
    }

    public void initUi(OrderCommentInfoModel.DataBeanX dataBeanX) {
        OrderCommentInfoModel.DataBeanX.OrderBean.ItemsBean itemsBean = dataBeanX.getOrder().getItems().get(0);
        GlideUtils.loadImg(itemsBean.getFirstPic(), mItemHead);
        mItemTitle.setText(itemsBean.getTitle());
        mItemTime.setText("成交时间 ：" + dataBeanX.getOrder().getCreateTime());
        mItemPrice.setText("成交金额 ：￥" + dataBeanX.getOrder().getPayment());
        mItemRate.setNumStars(5);
    }


    //获oss请求的必要参数
    private void geOssToken() {
        showWaitDialog();
        OssTokenModel.sendOssTokenRequest(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                OssTokenModel ossTokenModel = JSONObject.parseObject(resultData, OssTokenModel.class);
                if (ossTokenModel.getData() != null) {
                    String decryptData = "";
                    try {
                        decryptData = DesUtil.decrypt(ossTokenModel.getData().getEncryptedData(), Constants.Nouns.OSS_KEY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (!EmptyUtils.isEmpty(decryptData)) {
                        decryOssDataModel = JSON.parseObject(decryptData, DecryOssDataModel.class);
                    } else {
                        ToastUtils.showShort("oss数据有误");
                    }
                }
            }
        });
    }


    //显示选择获取的图片的方式
    private void showChoosePicTypeDialog() {
        publicationEvaluationDialog = new BottomListDialog(OrderEvaluationActivity.this, getResources().getString(R.string.pager_personal_pic_comefrom),
                CommonlyUsedData.getInstance().getPhotoChooseData(),
                -1, new BottomListDialog.IAdapter() {
            @Override
            public void onItemClick(String str, int positoion) {
                if (positoion == 0) {
                    EasyPhotos.createAlbum(OrderEvaluationActivity.this, false, GlideEngine.getInstance())
                            .setCount(7 - postImglistAdapter.getData().size())
                            .start(mSelectCallback);
                } else {
                    EasyPhotos.createCamera(OrderEvaluationActivity.this)
                            .start(mSelectCallback);
                }
            }
        });

        publicationEvaluationDialog.show();
    }

    @OnClick({R.id.stb_immediate_purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stb_immediate_purchase:
                commitEvaluation();
                break;
        }
    }

    //图片选中完得回调（拍照完得回调）
    private SelectCallback mSelectCallback = new SelectCallback() {
        @Override
        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
            showWaitDialog();
            if (paths.size() > 0) {
                for (int i = 0; i < paths.size(); i++) {
                    String resultPath = paths.get(i);
                    goLuban(resultPath, i, paths.size());
                }
            } else {
                hideWaitDialog();
            }
        }
    };

    //调用鲁班压缩
    private void goLuban(String resultPath, int i, int size) {
        lubanUtils.compressed(this, BaseGlobal.getImageCompressedTempDir(), resultPath, String.valueOf(i),
                new LubanUtils.onCompressedListener2() {
                    @Override
                    public void compressedSuccess(File file, String key) {
                        resultFileMap.put(key, file);
                        if (resultFileMap.size() == size) {
                            //图片压缩完成 将图片进行展示
                            for (String mapKey : resultFileMap.keySet()) {
                                postImglistAdapter.getData().add(postImglistAdapter.getData().size() - 1, resultFileMap.get(mapKey));
                            }

                            resultFileMap.clear();

                            int resultFileSize = postImglistAdapter.getData().size() > 6 ? 6 : postImglistAdapter.getData().size() - 1;
                            for (int i = 0; i < resultFileSize; i++) {
                                File upfile = postImglistAdapter.getData().get(i);
                                ossUpDate(upfile, decryOssDataModel.getAccessKeyId(), "",
                                        decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName(),
                                        decryOssDataModel.getSecret(), decryOssDataModel.getDomain(), String.valueOf(i), resultFileSize);
                            }
                        }
                    }

                    @Override
                    public void compressedError() {
                        hideWaitDialog();
                    }
                });
    }

    //上传到oss
    private void ossUpDate(final File file, String accessKeyId, String securityToken, String endPoint,
                           String bucketName, String accessKeySecret, final String domain, final String key, final int resultFileSize) {
        ossUtils.initOssOption(this, accessKeyId, accessKeySecret, securityToken, endPoint, bucketName);
        ossUtils.sendUpFileRequest(file, Constants.Api.OSS_FOLDER_IMG_GOODS, key, new OssUtils.OssCompleteListener() {
            @Override
            public void upLoadSuccess(final String picUrl, String tag) {
                ossResultPaths.put(tag, domain + "/" + picUrl);
                if (ossResultPaths.size() >= resultFileSize) {
                    hideWaitDialog();
                    for (String ossKey : ossResultPaths.keySet()) {
                        imgList.add(ossResultPaths.get(ossKey));
                    }

                    ossResultPaths.clear();
                    postImglistAdapter.clearImgViews();
                    postImglistAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    //提交评论
    private void commitEvaluation() {


        int lables = 0;
        CommitEvaluationModel commitEvaluationModel = new CommitEvaluationModel();
        List<CommonModel> lableAdapterData = mEvaluationLableAdapter.getData();
        for (CommonModel commonModel : lableAdapterData
        ) {
            if (commonModel.isCommonSelect()) {
                lables += commonModel.getCommonInt();
            }
        }


        String bzStr = mItemContentEt.getText().toString().trim();
        if (EmptyUtils.isEmpty(bzStr)) {
            bzStr = "默认好评";
        }

        CommitEvaluationModel.DataBean dataBean = new CommitEvaluationModel.DataBean(bzStr, mItemCheck.isChecked() ? "1" : "0", rateNum, imgList, String.valueOf(lables));
        commitEvaluationModel.setOrderCode(orderCode);


        orderComment(commitEvaluationModel);
    }


    //订单评论
    private void orderComment(CommitEvaluationModel commitEvaluationModel) {
        showWaitDialog();
        BaseModel.httpOrderEvaluate(commitEvaluationModel.getOrderCode(), commitEvaluationModel.getData(), new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BroadCastReceiveUtils.sendLocalBroadCast(OrderEvaluationActivity.this,
                        Constants.Action.SEND_REFRESH_ORDER_LIST + "8");
                OrderCompleteEvaluationActivity.newIntance(OrderEvaluationActivity.this,
                        orderCode);
                goFinish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (publicationEvaluationDialog != null && publicationEvaluationDialog.isShowing()) {
            publicationEvaluationDialog.dismiss();
            publicationEvaluationDialog = null;
        }
    }

}