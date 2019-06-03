package com.shenkangyun.disabledproject.BeanFolder;

/**
 * Created by Administrator on 2018/3/27.
 */

public class CommResultBean {

    /**
     * status : 0
     * data : {"comments":{"id":"4028805262667d970162669fdf2f0016","showID":"4028801e6265e76c016265e92eba0001","content":"terst122121","checked":0,"receiverID":null,"createUser":"1","createTime":null,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}}
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
         * comments : {"id":"4028805262667d970162669fdf2f0016","showID":"4028801e6265e76c016265e92eba0001","content":"terst122121","checked":0,"receiverID":null,"createUser":"1","createTime":null,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}
         */

        private CommentsBean comments;

        public CommentsBean getComments() {
            return comments;
        }

        public void setComments(CommentsBean comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * id : 4028805262667d970162669fdf2f0016
             * showID : 4028801e6265e76c016265e92eba0001
             * content : terst122121
             * checked : 0
             * receiverID : null
             * createUser : 1
             * createTime : null
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private String id;
            private String showID;
            private String content;
            private int checked;
            private String receiverID;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getChecked() {
                return checked;
            }

            public void setChecked(int checked) {
                this.checked = checked;
            }

            public String getReceiverID() {
                return receiverID;
            }

            public void setReceiverID(String receiverID) {
                this.receiverID = receiverID;
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
