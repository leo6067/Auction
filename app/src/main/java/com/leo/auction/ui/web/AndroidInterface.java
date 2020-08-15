package com.leo.auction.ui.web;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.just.agentweb.AgentWeb;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.ui.main.SharedActvity;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared_dailog.SharedModel;

/**
 * Created by cenxiaozhong on 2017/5/14.
 * source code  https://github.com/Justson/AgentWeb
 */

public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }


    @JavascriptInterface
    public void closeAction() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                ActivityManager.agentWebActivity.finish();
            }
        });
    }


    @JavascriptInterface
    public void invitationAction(String data) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                Globals.log("invitationAction" + data);
                JSShareJson jsShareJson = JSONObject.parseObject(data, JSShareJson.class);
                SharedModel sharedModel = new SharedModel(jsShareJson.getTitle(), jsShareJson.getDesc(), jsShareJson.getImg(), jsShareJson.getUrl(),"2","");
                SharedActvity.newIntance(context, sharedModel);
            }
        });
    }



    @JavascriptInterface
    public void LogPrinting(String data) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                Globals.log("invitationAction  LogPrinting" + data);
//                JSShareJson jsShareJson = JSONObject.parseObject(data, JSShareJson.class);
//                SharedModel sharedModel = new SharedModel(jsShareJson.getTitle(), jsShareJson.getDesc(), jsShareJson.getImg(), jsShareJson.getUrl());
//                SharedActvity.newIntance(context, sharedModel);
            }
        });
    }

    @JavascriptInterface
    public void appLoginSuccess() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
//                Globals.log("invitationAction  LogPrinting" + data);
//                JSShareJson jsShareJson = JSONObject.parseObject(data, JSShareJson.class);
//                SharedModel sharedModel = new SharedModel(jsShareJson.getTitle(), jsShareJson.getDesc(), jsShareJson.getImg(), jsShareJson.getUrl());
//                SharedActvity.newIntance(context, sharedModel);
            }
        });
    }




}
