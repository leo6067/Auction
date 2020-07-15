package com.leo.auction.ui.main.mine.banlance.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.banlance.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/11
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class WithdrawNumModel {


    /**
     * data : {"residueNum":1,"maxMoney":20000}
     * result : {"message":"今日已提现","success":false,"timestamp":1592405543105}
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
         * residueNum : 1
         * maxMoney : 20000
         */

        private int residueNum;
        private String maxMoney;

        public int getResidueNum() {
            return residueNum;
        }

        public void setResidueNum(int residueNum) {
            this.residueNum = residueNum;
        }

        public String getMaxMoney() {
            return maxMoney;
        }

        public void setMaxMoney(String maxMoney) {
            this.maxMoney = maxMoney;
        }
    }

    public static class ResultBean {
        /**
         * message : 今日已提现
         * success : false
         * timestamp : 1592405543105
         */

        private String message;
        private boolean success;
        private long timestamp;

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

    public static void httpWithdrawNum(){



    }
}
