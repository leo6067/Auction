package com.leo.auction.model.home;

/**
 * Created by Leo on 2018/7/2.
 */

public class VersionJson {


    /**
     * state : 0
     * message :
     * data : {"id":0,"forceUpgradeType":0,"upgradeType":0,"appPath":"https://www.baidu.com/","describe":"测试公告"}
     */

    private int state;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VersionJson{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 0
         * forceUpgradeType : 0
         * upgradeType : 0
         * appPath : https://www.baidu.com/
         * describe : 测试公告
         */

        private int id;
        private int forceUpgradeType;
        private int upgradeType;
        private String appPath;
        private String describe;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getForceUpgradeType() {
            return forceUpgradeType;
        }

        public void setForceUpgradeType(int forceUpgradeType) {
            this.forceUpgradeType = forceUpgradeType;
        }

        public int getUpgradeType() {
            return upgradeType;
        }

        public void setUpgradeType(int upgradeType) {
            this.upgradeType = upgradeType;
        }

        public String getAppPath() {
            return appPath;
        }

        public void setAppPath(String appPath) {
            this.appPath = appPath;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", forceUpgradeType=" + forceUpgradeType +
                    ", upgradeType=" + upgradeType +
                    ", appPath='" + appPath + '\'' +
                    ", describe='" + describe + '\'' +
                    '}';
        }
    }
}
