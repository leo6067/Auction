package com.leo.auction.ui.main.news.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.news.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/30
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class NewsModel {


    /**
     * data : [{"noticeId":1,"title":"标题","content":"内容","createTime":"2020-06-15 10:24","pic":"图片","skipUrl":"跳转地址","status":"状态"}]
     * result : {"success":true,"message":"请求成功"}
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
         * success : true
         * message : 请求成功
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public static class DataBean {
        /**
         * noticeId : 1
         * title : 标题
         * content : 内容
         * createTime : 2020-06-15 10:24
         * pic : 图片
         * skipUrl : 跳转地址
         * status : 状态
         */

        private int noticeId;
        private String title;
        private String content;
        private String createTime;
        private String pic;
        private String skipUrl;
        private String status;

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSkipUrl() {
            return skipUrl;
        }

        public void setSkipUrl(String skipUrl) {
            this.skipUrl = skipUrl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "noticeId=" + noticeId +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", pic='" + pic + '\'' +
                    ", skipUrl='" + skipUrl + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
