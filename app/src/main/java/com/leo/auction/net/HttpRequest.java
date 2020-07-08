package com.leo.auction.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.LogUtils;


import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.utils.Globals;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

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
        jsonObject.put("client", "4");
//        LogUtils.e("dataParams:"+jsonObject.toJSONString());
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

        jsonObject.put("client", "4");
//        LogUtils.e("dataParams:"+jsonObject.toJSONString());
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
                Globals.log("log XHttpUtils ", url + "" + result);
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
        jsonObject.put("client", "4");

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
        data.put("client", "4");
        if (url.length() == 0) {
            ToastUtils.showShort("请求路径有误！");
            return;
        }
        Globals.log("log XHttpUtils  data " + url + data.toString());

        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(data));
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
        data.put("client", "4");
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


}
