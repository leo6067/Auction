package com.leo.auction.net;

import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.LogUtils;


import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebAppActivity;
import com.leo.auction.utils.Globals;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.LitePal;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.net
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/12/14
 * 描    述：请求基础类（okhttputils版本）post Gson字符串到服务器端
 * ================================================
 */
public class HttpRequest {
    public static void httpPostString( String url, JSONObject jsonObject, HttpRequest.HttpCallback httpCallback) {
        jsonObject.put("client", "64");
        LogUtils.e("dataParams:"+jsonObject.toJSONString());
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jsonObject.toJSONString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        httpCallback.httpError(call, e);
                        Globals.log("log XHttpUtils 后台错误url=" + url);
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Globals.log("log XHttpUtils  url" + url + "  result=   " + result);
                        ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                        if ("5004".equals(jsonObject.getResult().getCode())) {//异端登录
//                            //刷新首页
//                            LoginActivity.newIntance(RxTool.getContext(),1);
//                            ToastUtils.showShort(jsonObject.getResult().getMessage());
//                            return;
//                        }
//
//                        if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                            //刷新首页
//                            LoginActivity.newIntance(RxTool.getContext(),0);
//                            return;
//                        }
                        try {
                            httpCallback.httpResponse(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Globals.log("log XHttpUtils 代码报错url=" + url);
                        }
                    }
                });
    }



    public static void httpPostString( String url, JSONObject jsonObject, CustomerJsonCallBack httpCallback) {

        jsonObject.put("client", "64");
        LogUtils.e("dataParams:"+jsonObject.toJSONString());
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jsonObject.toJSONString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        httpCallback.onError(  call,   e,   id);
                        Globals.log("log XHttpUtils 后台错误url=" + url);
                    }

                    @Override
                    public void onResponse(String result, int id) {
                        Globals.log("log XHttpUtils  url" + url + "  result=   " + result);
                        ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                        if ( "5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                            //刷新首页
//                            LoginActivity.newIntance(RxTool.getContext(),1);
//                            ToastUtils.showShort(jsonObject.getResult().getMessage());
//                            return;
//                        }
//
//                        if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                            //刷新首页
//                            LoginActivity.newIntance(RxTool.getContext(),0);
//                            return;
//                        }
                        try {
                            httpCallback.onRequestSuccess(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Globals.log("log XHttpUtils 代码报错url=" + url);
                        }
                    }
                });

    }





    //容器参数
    public static void httpGetString(final String url, final HashMap<String, String> data, final HttpCallback httpCallback) {
        data.put("client", "64");

        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }

        Globals.log("log XHttpUtils  data " + url + data.toString());
        OkHttpUtils.get().url(url).params(data).build().execute(new CustomerJsonCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url= " + url);
            }

            @Override
            public void onRequestError(Object returnData, String msg) {

            }

            @Override
            public void onRequestSuccess(Object returnData) {

            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils "+url + "" + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
                    //刷新首页

                    ToastUtils.showShort(jsonObject.getResult().getMessage());

                    BaseSharePerence.getInstance().setUserJson("");
                    BaseSharePerence.getInstance().setLoginStatus(false);

                }

                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
                    //刷新首页

                    BaseSharePerence.getInstance().setUserJson("");
                    BaseSharePerence.getInstance().setLoginStatus(false);

                }
                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });

    }


    //容器参数
    public static void httpGetStringWeb(final String url, final HashMap<String, String> data, final HttpCallback httpCallback) {
        data.put("client", "4");

        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }

        Globals.log("log XHttpUtils  data " + url + data.toString());
        OkHttpUtils.get().url(url).params(data).build().execute(new CustomerJsonCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url= " + url);
            }

            @Override
            public void onRequestError(Object returnData, String msg) {

            }

            @Override
            public void onRequestSuccess(Object returnData) {

            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils "+url + "" + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),1);
//                    ToastUtils.showShort(jsonObject.getResult().getMessage());
//                    return;
//                }
//
//                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),0);
//                    return;
//                }
                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });


    }

    public static void httpPostStringWeb(final String url, final HashMap<String, String> data, final HttpCallback httpCallback) {
        data.put("client", "4");
        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }
        Globals.log("log XHttpUtils  data " + url + data.toString());
        OkHttpUtils.postString()
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(url)
                .content(JSON.toJSONString(data))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url=" + url);
            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils  url" + url + "  result=   " + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),1);
//                    ToastUtils.showShort(jsonObject.getResult().getMessage());
//                    return;
//                }
//
//                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),0);
//                    return;
//                }
                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });
    }







    public static void httpPostString(final String url, final HashMap<String, String> data, final HttpCallback httpCallback) {
        data.put("client", "64");
        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }
        Globals.log("log XHttpUtils  data " + url + data.toString());
        OkHttpUtils.postString()
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(url)
                .content(JSON.toJSONString(data))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url=" + url);
            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils  url" + url + "  result=   " + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),1);
//                    ToastUtils.showShort(jsonObject.getResult().getMessage());
//                    return;
//                }
//
//                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),0);
//                    return;
//                }
                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });
    }


    public static void httpPutString(final String url, final JSONObject jsonObject  , final HttpCallback httpCallback) {
        jsonObject.put("client", "64");

        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }

        Globals.log("log XHttpUtils jsonObject= 参数" + jsonObject.toJSONString());
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());
        OkHttpUtils.put()
                .url(url)
                .requestBody(body)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url=" + url);
            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils  url" + url + "  result=   " + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),1);
//                    ToastUtils.showShort(jsonObject.getResult().getMessage());
//                    return;
//                }
//
//
//                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),0);
//                    return;
//                }

                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });
    }


    public static void httpDeleteString(final String url, final HashMap<String, String> data, final HttpCallback httpCallback) {
        data.put("client", "64");
        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }
        Globals.log("log XHttpUtils  data " + url + data.toString());

//        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, map2Form(data));
        OkHttpUtils.delete()
                .url(url)
                .requestBody(body)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url=" + url);
            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils  url" + url + "  result=   " + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),1);
//                    ToastUtils.showShort(jsonObject.getResult().getMessage());
//                    return;
//                }
//
//                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),0);
//                    return;
//                }

                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });
    }





    public static void httpPostForm(final String url, final HashMap<String, String> data, final HttpCallback httpCallback) {
        data.put("client", "64");
        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }


        Globals.log("log XHttpUtils  data " + url + data.toString());
//
//        //post方式提交的数据
//        FormBody formBody = new FormBody.Builder()
//                .add("name", "android基础")
//                .add("price", "50")
//                .build();


//        OkHttpUtils.post()
//                .params(data)
//                .url(url)
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                httpCallback.httpError(call, e);
//                Globals.log("log XHttpUtils 后台错误url=" + url);
//            }
//
//            @Override
//            public void onResponse(String result, int id) {
//                Globals.log("log XHttpUtils  url" +url+" " + result);
//                try {
//                    httpCallback.httpResponse(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Globals.log("log XHttpUtils 代码报错url=" + url);
//                }
//            }
//        });


//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, map2Form(data));
//        OkHttpUtils.post()


        OkHttpUtils.post()
                .params(data)
                .url(url)
                .build().execute(new CustomerJsonCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpCallback.httpError(call, e);
                Globals.log("log XHttpUtils 后台错误url=" + url);
            }

            @Override
            public void onRequestError(Object returnData, String msg) {

            }

            @Override
            public void onRequestSuccess(Object returnData) {

            }

            @Override
            public void onResponse(String result, int id) {
                Globals.log("log XHttpUtils  url" + url + " " + result);
                ResultModel  jsonObject = JSONObject.parseObject(result,ResultModel.class);
//                if ("5004".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),1);
//                    ToastUtils.showShort(jsonObject.getResult().getMessage());
//                    return;
//                }
//
//                if ("5002".equals(jsonObject.getResult().getCode())) {//登录超时，重新登录
//                    //刷新首页
//                    LoginActivity.newIntance(RxTool.getContext(),0);
//                    return;
//                }

                try {
                    httpCallback.httpResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Globals.log("log XHttpUtils 代码报错url=" + url);
                }
            }
        });


    }


    public abstract static class HttpCallback {
        public abstract void httpError(Call call, Exception e);

        public abstract void httpResponse(String resultData);
    }




    public static String map2Form(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        if(map == null) {
            return stringBuilder.toString();
        } else {
            for (Map.Entry<String, String> entry:
                    map.entrySet() ) {
                stringBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
    }

}
