package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ToolBean {

    /**
     * status : 0
     * data : {"pageCount":100,"totalCount":2,"pageNo":0,"leaseList":[{"id":1,"applicationtypeId":1,"title":"测试申请流程","orderNum":1,"content":"测试内容","createTime":null,"delFlag":0},{"id":2,"applicationtypeId":1,"title":"12312312312","orderNum":2,"content":"123123123","createTime":null,"delFlag":0}],"toolList":[{"id":"4028801e61f3faec0161f3fb0e4c0001","name":"辅具测试","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":30,\"attachmentUrl\":\"attachment/crfImage/20180305/1026034028801e61f3faec0161f3faec930000.png\"}]}","serviceObject":1,"content":"<strong>测试数据<\/strong>"},{"id":"4028801e61f4145a0161f41482040001","name":"测试","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":32,\"attachmentUrl\":\"attachment/crfImage/20180305/1054124028801e61f4145a0161f414b1820003.png\"},{\"sType\":1,\"attachmentID\":33,\"attachmentUrl\":\"attachment/crfImage/20180305/1054144028801e61f4145a0161f414baa10004.png\"}]}","serviceObject":2,"content":"<span style=\"color:#4C33E5;\">测试数据<\/span>"}]}
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
         * totalCount : 2
         * pageNo : 0
         * leaseList : [{"id":1,"applicationtypeId":1,"title":"测试申请流程","orderNum":1,"content":"测试内容","createTime":null,"delFlag":0},{"id":2,"applicationtypeId":1,"title":"12312312312","orderNum":2,"content":"123123123","createTime":null,"delFlag":0}]
         * toolList : [{"id":"4028801e61f3faec0161f3fb0e4c0001","name":"辅具测试","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":30,\"attachmentUrl\":\"attachment/crfImage/20180305/1026034028801e61f3faec0161f3faec930000.png\"}]}","serviceObject":1,"content":"<strong>测试数据<\/strong>"},{"id":"4028801e61f4145a0161f41482040001","name":"测试","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":32,\"attachmentUrl\":\"attachment/crfImage/20180305/1054124028801e61f4145a0161f414b1820003.png\"},{\"sType\":1,\"attachmentID\":33,\"attachmentUrl\":\"attachment/crfImage/20180305/1054144028801e61f4145a0161f414baa10004.png\"}]}","serviceObject":2,"content":"<span style=\"color:#4C33E5;\">测试数据<\/span>"}]
         */

        private int pageCount;
        private int totalCount;
        private int pageNo;
        private List<LeaseListBean> leaseList;
        private List<ToolListBean> toolList;

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

        public List<LeaseListBean> getLeaseList() {
            return leaseList;
        }

        public void setLeaseList(List<LeaseListBean> leaseList) {
            this.leaseList = leaseList;
        }

        public List<ToolListBean> getToolList() {
            return toolList;
        }

        public void setToolList(List<ToolListBean> toolList) {
            this.toolList = toolList;
        }

        public static class LeaseListBean {
            /**
             * id : 1
             * applicationtypeId : 1
             * title : 测试申请流程
             * orderNum : 1
             * content : 测试内容
             * createTime : null
             * delFlag : 0
             */

            private int id;
            private int applicationtypeId;
            private String title;
            private int orderNum;
            private String content;
            private Object createTime;
            private int delFlag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getApplicationtypeId() {
                return applicationtypeId;
            }

            public void setApplicationtypeId(int applicationtypeId) {
                this.applicationtypeId = applicationtypeId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }
        }

        public static class ToolListBean {
            /**
             * id : 4028801e61f3faec0161f3fb0e4c0001
             * name : 辅具测试
             * imageListURL : {"json":[{"sType":1,"attachmentID":30,"attachmentUrl":"attachment/crfImage/20180305/1026034028801e61f3faec0161f3faec930000.png"}]}
             * serviceObject : 1
             * content : <strong>测试数据</strong>
             */

            private String id;
            private String name;
            private String imageListURL;
            private int serviceObject;
            private String content;

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

            public String getImageListURL() {
                return imageListURL;
            }

            public void setImageListURL(String imageListURL) {
                this.imageListURL = imageListURL;
            }

            public int getServiceObject() {
                return serviceObject;
            }

            public void setServiceObject(int serviceObject) {
                this.serviceObject = serviceObject;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
