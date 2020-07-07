package com.leo.auction.ui.main.mine.model;


import com.alibaba.fastjson.JSONObject;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.seller.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/29 0029
 * 描    述：
 * ================================================
 */
public class ReleaseSortModel {
    /**
     * data : [{"id":"1","name":"","attributes":[{"id":"2","title":"属性名称","tab":"描述","length":"18","option":"1","tags":[{"tagId":"3","name":"标签名称"}]}]}]
     * result : {"message":"请求成功","success":true}
     */

    private ResultBean result;
    private List<DataBean> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * message : 请求成功
         * success : true
         */

        private String message;
        private boolean success;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * name :
         * attributes : [{"id":"2","title":"属性名称","tab":"描述","length":"18","option":"1","tags":[{"tagId":"3","name":"标签名称"}]}]
         */

        private String id;
        private String name;
        private boolean select;
        private ArrayList<AttributesBean> attributes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public ArrayList<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(ArrayList<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public static class AttributesBean {
            /**
             * id : 2
             * title : 属性名称
             * tab : 描述
             * length : 18
             * option : 1
             * tags : [{"tagId":"3","name":"标签名称"}]
             */

            private String id;
            private String title;
            private String tab;
            private String length;
            private String option;
            private List<TagsBean> tags;
            private String value;



            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public static class TagsBean {
                /**
                 * tagId : 3
                 * name : 标签名称
                 */

                private String id;
                private String tagId;
                private String name;
                private boolean select;

                public TagsBean(String id, String tagId, String name, boolean select) {
                    this.id = id;
                    this.tagId = tagId;
                    this.name = name;
                    this.select = select;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTagId() {
                    return tagId;
                }

                public void setTagId(String tagId) {
                    this.tagId = tagId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isSelect() {
                    return select;
                }

                public void setSelect(boolean select) {
                    this.select = select;
                }
            }
        }
    }

    //当parentId的-1时,得到的是一级分类列表,当parentId不是-1时，会返回二级分类，
    //type:控制是否开启对应的属性和标签
    //使用例子： 进入发布界面的时候，你的参数传递parentId=-1即可 ，这时候会返回一级分类给你。
    //你点击一级分类的时候，还是传递parentId=#{该一级分类的id} 参数即可，这时候会返回二级分类，
    //这时候点击二级分类，二级分类id要放置categoryId上 type=1 开启属性和标签 这样就可以了。
    public static void sendReleaseSortRequest(final String TAG, String parentId,String categoryId,String type,HashMap<String,String> hashMap,
                                              HttpRequest.HttpCallback httpCallback) {
        JSONObject params=new JSONObject();
        params.put("parentId",parentId);//-1的时候 取的是一级分类
        params.put("categoryId",categoryId);//二级分类标识
        params.put("type",type);//0-不开启  1-开启 是否展示属性和标签
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_CATEGORY_PUB_LIST_URL, params.toJSONString(), callback);
        HttpRequest.httpPostString(Constants.Api.GOODS_DETAIL_ATTR_URL, params,httpCallback);
    }
}
