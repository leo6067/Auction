package com.leo.auction.utils.shared_dailog;

import android.widget.LinearLayout;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.utils.shared_dailog
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/19
 * 描    述：
 * 修    改：
 * ===============================================
 */
public interface ShareDialogInter {

    void dissmiss();

    void onCopyLink();

    void onSharedWX();//链接

    void onSharedWXCircle();//链接

    void onSharedWXCircle_qrcode(LinearLayout llContain);//带二维码图片 九张图

    void onSharedWXCircleNine();//不带二维码图片 九张图


    void onSharedWXCircleCode(LinearLayout llContain);//带二维码图片 一张二维码图

    void onSharedQQCode(LinearLayout llContain);//带二维码图片 一张二维码图

    void onSharedWXCode(LinearLayout llContain);//带二维码图片 一张二维码图

    void onDowload(LinearLayout llContain);

    void onXYShared();

    void onWWDZShared();

    void onQQShared(); //链接


    void onShowShareDialog();
}
