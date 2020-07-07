package com.leo.auction.ui.main.home.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/19
 * 描    述： 大分类
 * 修    改：
 * ===============================================
 */
public class SortLeftModel {


    /**
     * data : [{"children":[{"icon":"https://file.taojianlou.com/ut/goods/F8385023BEEF4C109CF59ED5E2D8AE65.jpg","id":2,"name":"和田玉"},{"icon":"https://file.taojianlou.com/ut/goods/E57E387BC36D425795E187ACF92766A3.jpg","id":3,"name":"翡翠"},{"icon":"https://file.taojianlou.com/ut/goods/01EBC03D108F4E7C898BE6FFE0704523.jpg","id":4,"name":"松石"},{"icon":"https://file.taojianlou.com/ut/goods/49549983E42A4DBBADC0FA2C8F436048.jpg","id":5,"name":"琥珀 | 蜜蜡"},{"icon":"https://file.taojianlou.com/ut/goods/56F5281EA1C142F688D301D4A7C0E05F.jpg","id":6,"name":"南红"}],"id":1,"name":"玉翠珠宝"},{"children":[{"icon":"https://file.taojianlou.com/ut/goods/C243A2E67CD2439395380DB2A474B9C5.jpg","id":16,"name":"木雕 | 摆件"},{"icon":"https://file.taojianlou.com/ut/goods/71D829C952C74943AC3D39020D17E00F.jpg","id":15,"name":"菩提珠串"},{"icon":"https://file.taojianlou.com/ut/goods/4CFD3724EA9F4728992D81A0F67AEEB5.jpg","id":14,"name":"木质珠串"},{"icon":"https://file.taojianlou.com/ut/goods/B6C615E94FFE47619B9EF7409DC2A8AA.jpg","id":17,"name":"崖柏"},{"icon":"https://file.taojianlou.com/ut/goods/839438E40878432B959141F4659A847C.jpg","id":19,"name":"紫檀 | 黄花梨"},{"icon":"https://file.taojianlou.com/ut/goods/3D1A8B9DC7CE407C90787D92C5C6A1D9.jpg","id":21,"name":"沉香 | 檀香"},{"icon":"https://file.taojianlou.com/ut/goods/6994503B332E477C9345DC0239E9FBBE.jpg","id":22,"name":"家具 | 家居"},{"icon":"https://file.taojianlou.com/ut/goods/4F99685F580E4BB684D85011EEC2B8DC.jpg","id":24,"name":"宗教文化"},{"icon":"https://file.taojianlou.com/ut/goods/DD0F098AF2A446E79996AFE7DDD57BEB.jpg","id":25,"name":"石雕"}],"id":13,"name":"工艺作品"},{"children":[{"icon":"https://file.taojianlou.com/ut/goods/ED135AD41C4343D98605A6DDFDB9ECB4.jpg","id":34,"name":"香道 | 烟具"},{"icon":"https://file.taojianlou.com/ut/goods/FF724819EF3A42D2A6EB7B4FBB91503F.jpg","id":33,"name":"核雕 | 核桃"},{"icon":"https://file.taojianlou.com/ut/goods/E8C65CA0377D447FA42241ED5EDE7665.jpg","id":37,"name":"钱币"},{"icon":"https://file.taojianlou.com/ut/goods/9703524B7CC14C06996B09EC6F1B3393.jpg","id":38,"name":"化石"},{"icon":"https://file.taojianlou.com/ut/goods/F6DBCD96249F4EE2A8662580351765F9.jpg","id":41,"name":"奇石 | 摆件"},{"icon":"https://file.taojianlou.com/ut/goods/B18577D636DF4214BAA1880893AA4277.jpg","id":75,"name":"其他文玩"}],"id":31,"name":"文玩杂项"},{"children":[{"icon":"https://file.taojianlou.com/ut/goods/024A2052F8A74E3DA5891F7B164D446C.jpg","id":43,"name":"紫砂"},{"icon":"https://file.taojianlou.com/ut/goods/CD0F1C20658847C99F9A16BE07A943B8.jpg","id":45,"name":"瓷器 | 瓷片"},{"icon":"https://file.taojianlou.com/ut/goods/F245B7B4D6FB42769A9B456183E859F4.jpg","id":44,"name":"陶器"},{"icon":"https://file.taojianlou.com/ut/goods/35EC157CDE9048AAA2291A942F0A3B7B.jpg","id":76,"name":"建盏"},{"icon":"https://file.taojianlou.com/ut/goods/047F575B02FF4EBBAAB4A44C12B1A3E8.jpg","id":77,"name":"其他"}],"id":42,"name":"紫砂陶瓷"},{"children":[{"icon":"https://file.taojianlou.com/ut/goods/A7E3C7552CBE43988A33CDFD613EC41D.jpg","id":32,"name":"文房器"},{"icon":"https://file.taojianlou.com/ut/goods/B60920C5014B469D8EC6850BF6ACD20F.jpg","id":48,"name":"国画"},{"icon":"https://file.taojianlou.com/ut/goods/D37D4FD9F92341A9AC8030EEAE3000AB.jpg","id":51,"name":"印石章料"},{"icon":"https://file.taojianlou.com/ut/goods/281E30BE1347444691A655BEFDB59A96.jpg","id":49,"name":"西画"},{"icon":"https://file.taojianlou.com/ut/goods/686BD842A7CC44ED90EAA5D9D5D7A282.jpg","id":52,"name":"书法"}],"id":47,"name":"书画篆刻"},{"children":[{"icon":"https://file.taojianlou.com/ut/goods/E4C2932AB05644D5AFC547FF819F0125.jpg","id":62,"name":"茶器"},{"icon":"https://file.taojianlou.com/ut/goods/4CF1C005E5034F4BA87C758339BD3867.jpg","id":64,"name":"盆景 | 盆栽"},{"icon":"https://file.taojianlou.com/ut/goods/89F3D124D29D4CC4A4CE488AD643C63E.jpg","id":65,"name":"花卉"}],"id":54,"name":"茶艺盆景"}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591168623405}
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
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1591168623405
         */

        private String code;
        private String message;
        private boolean success;
        private long timestamp;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

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

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class DataBean {
        /**
         * children : [{"icon":"https://file.taojianlou.com/ut/goods/F8385023BEEF4C109CF59ED5E2D8AE65.jpg","id":2,"name":"和田玉"},{"icon":"https://file.taojianlou.com/ut/goods/E57E387BC36D425795E187ACF92766A3.jpg","id":3,"name":"翡翠"},{"icon":"https://file.taojianlou.com/ut/goods/01EBC03D108F4E7C898BE6FFE0704523.jpg","id":4,"name":"松石"},{"icon":"https://file.taojianlou.com/ut/goods/49549983E42A4DBBADC0FA2C8F436048.jpg","id":5,"name":"琥珀 | 蜜蜡"},{"icon":"https://file.taojianlou.com/ut/goods/56F5281EA1C142F688D301D4A7C0E05F.jpg","id":6,"name":"南红"}]
         * id : 1
         * name : 玉翠珠宝
         */

        private String id;
        private String name;
        private List<ChildrenBean> children;

        private boolean selected;



        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

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

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements MultiItemEntity {
            /**
             * icon : https://file.taojianlou.com/ut/goods/F8385023BEEF4C109CF59ED5E2D8AE65.jpg
             * id : 2
             * name : 和田玉
             */

            private String icon;
            private String id;
            private String name;


            private int mItemType;

            private int position ;

            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public void setItemType(int itemType) {
                mItemType = itemType;
            }


            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }


            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

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

            @Override
            public int getItemType() {
                return mItemType;
            }

            @Override
            public String toString() {
                return "ChildrenBean{" +
                        "icon='" + icon + '\'' +
                        ", id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", mItemType=" + mItemType +
                        ", position=" + position +
                        ", select=" + select +
                        '}';
            }
        }
    }




    public static void httpSort(HttpRequest.HttpCallback httpCallback){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "1");
        HttpRequest.httpGetString(Constants.Api.SORT_SORT_URL,hashMap,httpCallback);
    }


}
