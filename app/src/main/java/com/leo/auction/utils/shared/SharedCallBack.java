package com.leo.auction.utils.shared;

import com.aten.compiler.utils.IsInstallUtils;
import com.aten.compiler.utils.RxTool;

import com.aten.compiler.utils.ToastUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.dgonline_android.utils.shared
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/9/3
 * 描   述：
 * ================================================
 */
public abstract class SharedCallBack implements UMShareListener {
    @Override
    public void onResult(SHARE_MEDIA share_media) {
        ToastUtils.showShort("分享成功");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        onErrorBack();
        if (!IsInstallUtils.getInstance().isQQClientAvailable(RxTool.getContext())){
            ToastUtils.showShort("请安装QQ");
            return;
        }
        if (!IsInstallUtils.getInstance().isWeixinAvilible(RxTool.getContext())){
            ToastUtils.showShort("请安装微信");
            return;
        }
        ToastUtils.showShort("分享失败");
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        ToastUtils.showShort("分享取消");
    }

    public abstract void onErrorBack();
}
