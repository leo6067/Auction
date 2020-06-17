package com.leo.auction.model.home;

import java.util.List;

/**
 * Created by Leo on 2018/7/9.
 */

public class CollectWebJson {


    /**
     * state : 0
     * message :
     * data : [[{"webId":1,"webName":"赶集网","cityid":592},{"webId":6,"webName":"58同城","cityid":592},{"webId":8,"webName":"小鱼网","cityid":592},{"webId":5,"webName":"联合网","cityid":592},{"webId":109,"webName":"城市房产网","cityid":592},{"webId":338,"webName":"好租123","cityid":592},{"webId":339,"webName":"快点8","cityid":592},{"webId":313,"webName":"列表网","cityid":592}]]
     */

    private int state;
    private String message;
    private List<List<DataBean>> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * webId : 1
         * webName : 赶集网
         * cityid : 592
         */

        private int webId;
        private String webName;
        private int cityid;

        public int getWebId() {
            return webId;
        }

        public void setWebId(int webId) {
            this.webId = webId;
        }

        public String getWebName() {
            return webName;
        }

        public void setWebName(String webName) {
            this.webName = webName;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }
    }
}
