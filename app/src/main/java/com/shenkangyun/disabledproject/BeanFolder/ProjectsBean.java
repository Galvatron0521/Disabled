package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ProjectsBean {

    /**
     * status : 0
     * data : {"pageCount":8,"totalCount":5,"pageNo":0,"list":[{"typeDetailName":"0-11岁(矫治手术年龄可放宽到16岁)","id":"1","datatypedetailId":209,"name":"白内障复明手术","content":"白内障摘除术和人工晶体植入术，做好术后护理。标准参见《临床诊疗指南\u2014眼科学分册》(中华医学会编著，人民卫生出版社)","createUser":"admin","createTime":1510730896000,"updateUser":null,"updateTime":1519375553000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":"12岁以上智力残疾人","id":"2","datatypedetailId":211,"name":"辅助器具适配及服务","content":"盲杖、读书机等视力残疾人辅助器具，根据使用年限评估调换。","createUser":"admin","createTime":1510730900000,"updateUser":null,"updateTime":1519375561000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":"低视力者","id":"3","datatypedetailId":204,"name":"定向行走及适应训练","content":"功能评估；定向技能及行走训练，每年4次，每次2小时；社会适应能力训练，每年4次，每次2小时。","createUser":"admin","createTime":1510730902000,"updateUser":null,"updateTime":1520218919000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":"成人听力残疾人","id":"4","datatypedetailId":208,"name":"支持性服务","content":"中途盲者心理疏导，忙后半年内，每月不少于1次。","createUser":"admin","createTime":1510730904000,"updateUser":null,"updateTime":1519375574000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":null,"id":"4028801e61c1addc0161c1addced0000","datatypedetailId":208,"name":"123123","content":"123123123","createUser":"admin","createTime":1519372852000,"updateUser":null,"updateTime":1519611599000,"delFlag":1,"delTime":1519611599000,"remark":null},{"typeDetailName":"12-17岁儿童少年","id":"4028801e61f4145a0161f41d62160008","datatypedetailId":207,"name":"测试数据","content":"<strong><span style=\"font-size:32px;color:#9933E5;\">测试数据测试<\/span><\/strong>","createUser":"admin","createTime":1520219021000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}]}
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
         * pageCount : 8
         * totalCount : 5
         * pageNo : 0
         * list : [{"typeDetailName":"0-11岁(矫治手术年龄可放宽到16岁)","id":"1","datatypedetailId":209,"name":"白内障复明手术","content":"白内障摘除术和人工晶体植入术，做好术后护理。标准参见《临床诊疗指南\u2014眼科学分册》(中华医学会编著，人民卫生出版社)","createUser":"admin","createTime":1510730896000,"updateUser":null,"updateTime":1519375553000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":"12岁以上智力残疾人","id":"2","datatypedetailId":211,"name":"辅助器具适配及服务","content":"盲杖、读书机等视力残疾人辅助器具，根据使用年限评估调换。","createUser":"admin","createTime":1510730900000,"updateUser":null,"updateTime":1519375561000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":"低视力者","id":"3","datatypedetailId":204,"name":"定向行走及适应训练","content":"功能评估；定向技能及行走训练，每年4次，每次2小时；社会适应能力训练，每年4次，每次2小时。","createUser":"admin","createTime":1510730902000,"updateUser":null,"updateTime":1520218919000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":"成人听力残疾人","id":"4","datatypedetailId":208,"name":"支持性服务","content":"中途盲者心理疏导，忙后半年内，每月不少于1次。","createUser":"admin","createTime":1510730904000,"updateUser":null,"updateTime":1519375574000,"delFlag":0,"delTime":null,"remark":null},{"typeDetailName":null,"id":"4028801e61c1addc0161c1addced0000","datatypedetailId":208,"name":"123123","content":"123123123","createUser":"admin","createTime":1519372852000,"updateUser":null,"updateTime":1519611599000,"delFlag":1,"delTime":1519611599000,"remark":null},{"typeDetailName":"12-17岁儿童少年","id":"4028801e61f4145a0161f41d62160008","datatypedetailId":207,"name":"测试数据","content":"<strong><span style=\"font-size:32px;color:#9933E5;\">测试数据测试<\/span><\/strong>","createUser":"admin","createTime":1520219021000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}]
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
             * typeDetailName : 0-11岁(矫治手术年龄可放宽到16岁)
             * id : 1
             * datatypedetailId : 209
             * name : 白内障复明手术
             * content : 白内障摘除术和人工晶体植入术，做好术后护理。标准参见《临床诊疗指南—眼科学分册》(中华医学会编著，人民卫生出版社)
             * createUser : admin
             * createTime : 1510730896000
             * updateUser : null
             * updateTime : 1519375553000
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private String typeDetailName;
            private String id;
            private int datatypedetailId;
            private String name;
            private String content;
            private String createUser;
            private long createTime;
            private String updateUser;
            private long updateTime;
            private int delFlag;
            private String delTime;
            private String remark;

            public String getTypeDetailName() {
                return typeDetailName;
            }

            public void setTypeDetailName(String typeDetailName) {
                this.typeDetailName = typeDetailName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getDatatypedetailId() {
                return datatypedetailId;
            }

            public void setDatatypedetailId(int datatypedetailId) {
                this.datatypedetailId = datatypedetailId;
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

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
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
