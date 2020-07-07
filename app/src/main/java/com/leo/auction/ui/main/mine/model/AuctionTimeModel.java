package com.leo.auction.ui.main.mine.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/4
 * 描    述： 发拍，截拍时间
 * 修    改：
 * ===============================================
 */
public class AuctionTimeModel {


    /**
     * data : {"tomorrow":{"delayText":"5分钟延时竞价","delayTime":300,"interceptId":2,"timeNodes":[{"showText":"00:00","timeNode":0,"timeNodeId":2},{"showText":"06:00","timeNode":21600,"timeNodeId":4},{"showText":"10:00","timeNode":36000,"timeNodeId":5},{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}],"type":2,"typeName":"06月03日(明天)"},"quick":{"delayText":"1分钟延时竞价","delayTime":60,"interceptId":1,"timeNodes":[{"showText":"1分钟","timeNode":60,"timeNodeId":1},{"showText":"5分钟","timeNode":300,"timeNodeId":3}],"type":1,"typeName":"快速截拍"},"after_tomorrow":{"delayText":"5分钟延时竞价","delayTime":300,"interceptId":2,"timeNodes":[{"showText":"00:00","timeNode":0,"timeNodeId":2},{"showText":"06:00","timeNode":21600,"timeNodeId":4},{"showText":"10:00","timeNode":36000,"timeNodeId":5},{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}],"type":2,"typeName":"06月04日(后天)"},"today":{"delayText":"5分钟延时竞价","delayTime":300,"interceptId":2,"timeNodes":[{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}],"type":2,"typeName":"06月02日(后天)"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1591067862177}
     */

    private DataBean data;
    private ResultBean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * tomorrow : {"delayText":"5分钟延时竞价","delayTime":300,"interceptId":2,"timeNodes":[{"showText":"00:00","timeNode":0,"timeNodeId":2},{"showText":"06:00","timeNode":21600,"timeNodeId":4},{"showText":"10:00","timeNode":36000,"timeNodeId":5},{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}],"type":2,"typeName":"06月03日(明天)"}
         * quick : {"delayText":"1分钟延时竞价","delayTime":60,"interceptId":1,"timeNodes":[{"showText":"1分钟","timeNode":60,"timeNodeId":1},{"showText":"5分钟","timeNode":300,"timeNodeId":3}],"type":1,"typeName":"快速截拍"}
         * after_tomorrow : {"delayText":"5分钟延时竞价","delayTime":300,"interceptId":2,"timeNodes":[{"showText":"00:00","timeNode":0,"timeNodeId":2},{"showText":"06:00","timeNode":21600,"timeNodeId":4},{"showText":"10:00","timeNode":36000,"timeNodeId":5},{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}],"type":2,"typeName":"06月04日(后天)"}
         * today : {"delayText":"5分钟延时竞价","delayTime":300,"interceptId":2,"timeNodes":[{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}],"type":2,"typeName":"06月02日(后天)"}
         */

        private TomorrowBean tomorrow;
        private QuickBean quick;
        private AfterTomorrowBean after_tomorrow;
        private TodayBean today;

        public TomorrowBean getTomorrow() {
            return tomorrow;
        }

        public void setTomorrow(TomorrowBean tomorrow) {
            this.tomorrow = tomorrow;
        }

        public QuickBean getQuick() {
            return quick;
        }

        public void setQuick(QuickBean quick) {
            this.quick = quick;
        }

        public AfterTomorrowBean getAfter_tomorrow() {
            return after_tomorrow;
        }

        public void setAfter_tomorrow(AfterTomorrowBean after_tomorrow) {
            this.after_tomorrow = after_tomorrow;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public static class TomorrowBean {
            /**
             * delayText : 5分钟延时竞价
             * delayTime : 300
             * interceptId : 2
             * timeNodes : [{"showText":"00:00","timeNode":0,"timeNodeId":2},{"showText":"06:00","timeNode":21600,"timeNodeId":4},{"showText":"10:00","timeNode":36000,"timeNodeId":5},{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}]
             * type : 2
             * typeName : 06月03日(明天)
             */

            private String delayText;
            private int delayTime;
            private int interceptId;
            private int type;
            private String typeName;
            private List<TimeNodesBean> timeNodes;

            public String getDelayText() {
                return delayText;
            }

            public void setDelayText(String delayText) {
                this.delayText = delayText;
            }

            public int getDelayTime() {
                return delayTime;
            }

            public void setDelayTime(int delayTime) {
                this.delayTime = delayTime;
            }

            public int getInterceptId() {
                return interceptId;
            }

            public void setInterceptId(int interceptId) {
                this.interceptId = interceptId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<TimeNodesBean> getTimeNodes() {
                return timeNodes;
            }

            public void setTimeNodes(List<TimeNodesBean> timeNodes) {
                this.timeNodes = timeNodes;
            }

            public static class TimeNodesBean {
                /**
                 * showText : 00:00
                 * timeNode : 0
                 * timeNodeId : 2
                 */

                private String showText;
                private int timeNode;
                private int timeNodeId;

                public String getShowText() {
                    return showText;
                }

                public void setShowText(String showText) {
                    this.showText = showText;
                }

                public int getTimeNode() {
                    return timeNode;
                }

                public void setTimeNode(int timeNode) {
                    this.timeNode = timeNode;
                }

                public int getTimeNodeId() {
                    return timeNodeId;
                }

                public void setTimeNodeId(int timeNodeId) {
                    this.timeNodeId = timeNodeId;
                }
            }
        }

        public static class QuickBean {
            /**
             * delayText : 1分钟延时竞价
             * delayTime : 60
             * interceptId : 1
             * timeNodes : [{"showText":"1分钟","timeNode":60,"timeNodeId":1},{"showText":"5分钟","timeNode":300,"timeNodeId":3}]
             * type : 1
             * typeName : 快速截拍
             */

            private String delayText;
            private int delayTime;
            private int interceptId;
            private int type;
            private String typeName;
            private List<TimeNodesBeanX> timeNodes;

            public String getDelayText() {
                return delayText;
            }

            public void setDelayText(String delayText) {
                this.delayText = delayText;
            }

            public int getDelayTime() {
                return delayTime;
            }

            public void setDelayTime(int delayTime) {
                this.delayTime = delayTime;
            }

            public int getInterceptId() {
                return interceptId;
            }

            public void setInterceptId(int interceptId) {
                this.interceptId = interceptId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<TimeNodesBeanX> getTimeNodes() {
                return timeNodes;
            }

            public void setTimeNodes(List<TimeNodesBeanX> timeNodes) {
                this.timeNodes = timeNodes;
            }

            public static class TimeNodesBeanX {
                /**
                 * showText : 1分钟
                 * timeNode : 60
                 * timeNodeId : 1
                 */

                private String showText;
                private int timeNode;
                private int timeNodeId;

                public String getShowText() {
                    return showText;
                }

                public void setShowText(String showText) {
                    this.showText = showText;
                }

                public int getTimeNode() {
                    return timeNode;
                }

                public void setTimeNode(int timeNode) {
                    this.timeNode = timeNode;
                }

                public int getTimeNodeId() {
                    return timeNodeId;
                }

                public void setTimeNodeId(int timeNodeId) {
                    this.timeNodeId = timeNodeId;
                }
            }
        }

        public static class AfterTomorrowBean {
            /**
             * delayText : 5分钟延时竞价
             * delayTime : 300
             * interceptId : 2
             * timeNodes : [{"showText":"00:00","timeNode":0,"timeNodeId":2},{"showText":"06:00","timeNode":21600,"timeNodeId":4},{"showText":"10:00","timeNode":36000,"timeNodeId":5},{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}]
             * type : 2
             * typeName : 06月04日(后天)
             */

            private String delayText;
            private int delayTime;
            private int interceptId;
            private int type;
            private String typeName;
            private List<TimeNodesBeanXX> timeNodes;

            public String getDelayText() {
                return delayText;
            }

            public void setDelayText(String delayText) {
                this.delayText = delayText;
            }

            public int getDelayTime() {
                return delayTime;
            }

            public void setDelayTime(int delayTime) {
                this.delayTime = delayTime;
            }

            public int getInterceptId() {
                return interceptId;
            }

            public void setInterceptId(int interceptId) {
                this.interceptId = interceptId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<TimeNodesBeanXX> getTimeNodes() {
                return timeNodes;
            }

            public void setTimeNodes(List<TimeNodesBeanXX> timeNodes) {
                this.timeNodes = timeNodes;
            }

            public static class TimeNodesBeanXX {
                /**
                 * showText : 00:00
                 * timeNode : 0
                 * timeNodeId : 2
                 */

                private String showText;
                private int timeNode;
                private int timeNodeId;

                public String getShowText() {
                    return showText;
                }

                public void setShowText(String showText) {
                    this.showText = showText;
                }

                public int getTimeNode() {
                    return timeNode;
                }

                public void setTimeNode(int timeNode) {
                    this.timeNode = timeNode;
                }

                public int getTimeNodeId() {
                    return timeNodeId;
                }

                public void setTimeNodeId(int timeNodeId) {
                    this.timeNodeId = timeNodeId;
                }
            }
        }

        public static class TodayBean implements MultiItemEntity {
            /**
             * delayText : 5分钟延时竞价
             * delayTime : 300
             * interceptId : 2
             * timeNodes : [{"showText":"12:00","timeNode":43200,"timeNodeId":6},{"showText":"15:00","timeNode":54000,"timeNodeId":7},{"showText":"16:00","timeNode":57600,"timeNodeId":8},{"showText":"17:00","timeNode":61200,"timeNodeId":9},{"showText":"19:00","timeNode":68400,"timeNodeId":10},{"showText":"20:00","timeNode":72000,"timeNodeId":11},{"showText":"21:00","timeNode":75600,"timeNodeId":12},{"showText":"22:00","timeNode":79200,"timeNodeId":13},{"showText":"23:00","timeNode":82800,"timeNodeId":14}]
             * type : 2
             * typeName : 06月02日(后天)
             */

            private String delayText;
            private int delayTime;
            private int interceptId;
            private int type;
            private String typeName;
            private List<TimeNodesBeanXXX> timeNodes;

            public String getDelayText() {
                return delayText;
            }

            public void setDelayText(String delayText) {
                this.delayText = delayText;
            }

            public int getDelayTime() {
                return delayTime;
            }

            public void setDelayTime(int delayTime) {
                this.delayTime = delayTime;
            }

            public int getInterceptId() {
                return interceptId;
            }

            public void setInterceptId(int interceptId) {
                this.interceptId = interceptId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<TimeNodesBeanXXX> getTimeNodes() {
                return timeNodes;
            }

            public void setTimeNodes(List<TimeNodesBeanXXX> timeNodes) {
                this.timeNodes = timeNodes;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            public static class TimeNodesBeanXXX {
                /**
                 * showText : 12:00
                 * timeNode : 43200
                 * timeNodeId : 6
                 */

                private String showText;
                private int timeNode;
                private int timeNodeId;

                public String getShowText() {
                    return showText;
                }

                public void setShowText(String showText) {
                    this.showText = showText;
                }

                public int getTimeNode() {
                    return timeNode;
                }

                public void setTimeNode(int timeNode) {
                    this.timeNode = timeNode;
                }

                public int getTimeNodeId() {
                    return timeNodeId;
                }

                public void setTimeNodeId(int timeNodeId) {
                    this.timeNodeId = timeNodeId;
                }
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1591067862177
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

    public static void httpTimeModel(HttpRequest.HttpCallback httpCall){


        HashMap<String, String> hashMap = new HashMap<>();
        HttpRequest.httpGetString(Constants.Api.GOODS_TIME_URL,hashMap,httpCall);

    }

}
