package com.shenkangyun.disabledproject.BeanFolder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */

public class CommunityBean {

    /**
     * status : 0
     * data : {"pageCount":100,"totalCount":11,"pageNo":0,"list":[{"id":"1","name":"动态标题11111111111111","content":"动态内容11111111111111111111","coverPhoto":"{\"json\":[{\"sType\":1,\"attachmentID\":34,\"attachmentUrl\":\"attachment/crfImage/20180303/0824474028805261fdd89e0161fdd89e5b0000.png\"}]}","createUser":"李四","createTime":1510113415000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null,"status":0,"zanCount":3,"commentCount":1,"zanList":[{"id":"4028801e611cf60d01611cf60d160000","showID":"1","createUser":"张三","delFlag":0},{"id":"40288052620e5cc301620e5cc36c0000","showID":"1","createUser":"李四","delFlag":0},{"id":"40288052620e5cc301620e7113690001","showID":"1","createUser":"李四","delFlag":0}],"commentList":[{"id":"2","showID":"1","content":"评论2","createTime":null}]}]}
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
         * pageCount : 100
         * totalCount : 11
         * pageNo : 0
         * list : [{"id":"1","name":"动态标题11111111111111","content":"动态内容11111111111111111111","coverPhoto":"{\"json\":[{\"sType\":1,\"attachmentID\":34,\"attachmentUrl\":\"attachment/crfImage/20180303/0824474028805261fdd89e0161fdd89e5b0000.png\"}]}","createUser":"李四","createTime":1510113415000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null,"status":0,"zanCount":3,"commentCount":1,"zanList":[{"id":"4028801e611cf60d01611cf60d160000","showID":"1","createUser":"张三","delFlag":0},{"id":"40288052620e5cc301620e5cc36c0000","showID":"1","createUser":"李四","delFlag":0},{"id":"40288052620e5cc301620e7113690001","showID":"1","createUser":"李四","delFlag":0}],"commentList":[{"id":"2","showID":"1","content":"评论2","createTime":null}]}]
         */

        private int pageCount;
        private int totalCount;
        private int pageNo;
        private List<ListBean> list;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 动态标题11111111111111
             * content : 动态内容11111111111111111111
             * coverPhoto : {"json":[{"sType":1,"attachmentID":34,"attachmentUrl":"attachment/crfImage/20180303/0824474028805261fdd89e0161fdd89e5b0000.png"}]}
             * createUser : 李四
             * createTime : 1510113415000
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             * status : 0
             * zanCount : 3
             * commentCount : 1
             * zanList : [{"id":"4028801e611cf60d01611cf60d160000","showID":"1","createUser":"张三","delFlag":0},{"id":"40288052620e5cc301620e5cc36c0000","showID":"1","createUser":"李四","delFlag":0},{"id":"40288052620e5cc301620e7113690001","showID":"1","createUser":"李四","delFlag":0}]
             * commentList : [{"id":"2","showID":"1","content":"评论2","createTime":null}]
             */

            private String id;
            private String name;
            private String content;
            private String coverPhoto;
            private String createUser;
            private long createTime;
            private Object updateUser;
            private Object updateTime;
            private int delFlag;
            private Object delTime;
            private Object remark;
            private int status;
            private int zanCount;
            private int commentCount;
            private List<ZanListBean> zanList;
            private String zanStatus;

            public String getZanStatus() {
                return zanStatus;
            }

            public void setZanStatus(String zanStatus) {
                this.zanStatus = zanStatus;
            }

            private List<CommentListBean> commentList;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCoverPhoto() {
                return coverPhoto;
            }

            public void setCoverPhoto(String coverPhoto) {
                this.coverPhoto = coverPhoto;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public Object getDelTime() {
                return delTime;
            }

            public void setDelTime(Object delTime) {
                this.delTime = delTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getZanCount() {
                return zanCount;
            }

            public void setZanCount(int zanCount) {
                this.zanCount = zanCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public List<ZanListBean> getZanList() {
                return zanList;
            }

            public void setZanList(List<ZanListBean> zanList) {
                this.zanList = zanList;
            }

            public List<CommentListBean> getCommentList() {
                return commentList;
            }

            public void setCommentList(List<CommentListBean> commentList) {
                this.commentList = commentList;
            }

            public static class ZanListBean {
                /**
                 * id : 4028801e611cf60d01611cf60d160000
                 * showID : 1
                 * createUser : 张三
                 * delFlag : 0
                 */

                private String id;
                private String showID;
                private String createUser;
                private int delFlag;

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

                public int getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(int delFlag) {
                    this.delFlag = delFlag;
                }
            }

            public static class CommentListBean {
                /**
                 * id : 2
                 * showID : 1
                 * content : 评论2
                 * createTime : null
                 */

                private String id;
                private String showID;
                private String content;
                private String createUser;
                private String createTime;

                public String getCreateUser() {
                    return createUser;
                }

                public void setCreateUser(String createUser) {
                    this.createUser = createUser;
                }

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

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }
            }
        }
    }
}
