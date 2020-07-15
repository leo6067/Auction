package com.leo.auction.ui.order.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;


//获取评价详情
public class OrderCommentInfoModel {


    /**
     * data : {"data":{"content":"默认好评","label":63,"score":5,"labels":[1,2,3],"images":["图片1","图片2"]},"order":{"headImg":"https://file.taojianlou.com/ut/product/354D53758EBB444A853F966D38F8D872.jpg","items":[{"firstPic":"https://file.taojianlou.com/ut/product/1592963056555.png?image=800,800","instanceCode":"1301e0b2661260c176a97d2b849dd855","num":1,"price":"188888.00","title":"订单待付款来一个"}],"nickname":"超级无敌好\u2014-xiaolin","orderCode":"15929774160441964197372717268","payment":"188888.00","status":32,"createTime":123123123}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1593162957411}
     */

    private DataBeanX data;
    private ResultBean result;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBeanX {
        /**
         * data : {"content":"默认好评","label":63,"score":5,"labels":[1,2,3],"images":["图片1","图片2"]}
         * order : {"headImg":"https://file.taojianlou.com/ut/product/354D53758EBB444A853F966D38F8D872.jpg","items":[{"firstPic":"https://file.taojianlou.com/ut/product/1592963056555.png?image=800,800","instanceCode":"1301e0b2661260c176a97d2b849dd855","num":1,"price":"188888.00","title":"订单待付款来一个"}],"nickname":"超级无敌好\u2014-xiaolin","orderCode":"15929774160441964197372717268","payment":"188888.00","status":32,"createTime":123123123}
         */

        private DataBean data;
        private OrderBean order;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class DataBean {
            /**
             * content : 默认好评
             * label : 63
             * score : 5
             * labels : [1,2,3]
             * images : ["图片1","图片2"]
             */

            private String content;
            private int label;
            private int score;
            private List<Integer> labels;
            private List<String> images;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLabel() {
                return label;
            }

            public void setLabel(int label) {
                this.label = label;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public List<Integer> getLabels() {
                return labels;
            }

            public void setLabels(List<Integer> labels) {
                this.labels = labels;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class OrderBean {
            /**
             * headImg : https://file.taojianlou.com/ut/product/354D53758EBB444A853F966D38F8D872.jpg
             * items : [{"firstPic":"https://file.taojianlou.com/ut/product/1592963056555.png?image=800,800","instanceCode":"1301e0b2661260c176a97d2b849dd855","num":1,"price":"188888.00","title":"订单待付款来一个"}]
             * nickname : 超级无敌好—-xiaolin
             * orderCode : 15929774160441964197372717268
             * payment : 188888.00
             * status : 32
             * createTime : 123123123
             */

            private String headImg;
            private String nickname;
            private String orderCode;
            private String payment;
            private int status;
            private int createTime;
            private List<ItemsBean> items;

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCreateTime() {
                return createTime;
            }

            public void setCreateTime(int createTime) {
                this.createTime = createTime;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * firstPic : https://file.taojianlou.com/ut/product/1592963056555.png?image=800,800
                 * instanceCode : 1301e0b2661260c176a97d2b849dd855
                 * num : 1
                 * price : 188888.00
                 * title : 订单待付款来一个
                 */

                private String firstPic;
                private String instanceCode;
                private int num;
                private String price;
                private String title;

                public String getFirstPic() {
                    return firstPic;
                }

                public void setFirstPic(String firstPic) {
                    this.firstPic = firstPic;
                }

                public String getInstanceCode() {
                    return instanceCode;
                }

                public void setInstanceCode(String instanceCode) {
                    this.instanceCode = instanceCode;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1593162957411
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



    //获取评价详情 或者 订单详情
    public static void httpOrderEvaluation(String orderCode, HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderCode",orderCode);
        HttpRequest.httpGetString(Constants.Api.ORDER_INFO_URL,hashMap,httpCallback);

    }

}
