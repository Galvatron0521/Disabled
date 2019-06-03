package com.shenkangyun.disabledproject.BeanFolder;

/**
 * Created by Administrator on 2018/3/26.
 */

public class ZanRespond {
    /**
     * status : 0
     * data : {"zans":{"id":"4028805262615341016261689844000e","showID":"4028801e6217bf53016217bf5da00001","createUser":"石屹哲","createTime":"2018-03-26 16:24:27","updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * zans : {"id":"4028805262615341016261689844000e","showID":"4028801e6217bf53016217bf5da00001","createUser":"石屹哲","createTime":"2018-03-26 16:24:27","updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}
         */

        private ZansBean zans;

        public ZansBean getZans() {
            return zans;
        }

        public void setZans(ZansBean zans) {
            this.zans = zans;
        }

        public static class ZansBean {
            /**
             * id : 4028805262615341016261689844000e
             * showID : 4028801e6217bf53016217bf5da00001
             * createUser : 石屹哲
             * createTime : 2018-03-26 16:24:27
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private String id;
            private String showID;
            private String createUser;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String delTime;
            private String remark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getShowID() {
                return showID;
            }

            public void setShowID(String showID) {
                this.showID = showID;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getDelTime() {
                return delTime;
            }

            public void setDelTime(String delTime) {
                this.delTime = delTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
