package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MechanismBean {


    /**
     * status : 0
     * data : {"pageCount":8,"totalCount":15,"pageNo":0,"list":[{"id":"1","name":"泰山区人民医院儿康中心","responsibilityName":"测试","content":"<span style=\"font-size:32px;font-family:KaiTi_GB2312;\"><strong>泰山区人民医院儿康中心<\/strong><\/span><br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"18880868988","xAxis":"117.139116","yAxis":"36.197475","bdContent":"","createUser":"admin","createTime":1510130204000,"updateUser":"admin","updateTime":1521793035000,"delFlag":0,"delTime":1520473718000,"remark":""},{"id":"10","name":"泰安市中医二院","responsibilityName":"","content":"<strong><span style=\"font-size:32px;font-family:KaiTi_GB2312;\">泰安市中医二院<\/span><\/strong><br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510735008000,"updateUser":"admin","updateTime":1520477590000,"delFlag":0,"delTime":"","remark":""},{"id":"11","name":"泰山区人民医院","responsibilityName":"","content":"<span style=\"font-size:32px;font-family:KaiTi_GB2312;\">泰山区人民医院<\/span><strong><span style=\"font-size:32px;\"><\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"117.150758","yAxis":"36.186023","bdContent":"","createUser":"admin","createTime":1510648612000,"updateUser":"admin","updateTime":1520574240000,"delFlag":0,"delTime":"","remark":""},{"id":"12","name":"省庄镇卫生院","responsibilityName":"","content":"<span style=\"font-family:KaiTi_GB2312;font-size:32px;\"><strong>省庄镇卫生院<\/strong><\/span>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648615000,"updateUser":"admin","updateTime":1520233047000,"delFlag":0,"delTime":"","remark":""},{"id":"13","name":"邱家店镇卫生院","responsibilityName":"","content":"<strong><span style=\"font-size:32px;font-family:KaiTi_GB2312;\">邱家店镇卫生院<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648618000,"updateUser":"admin","updateTime":1520233175000,"delFlag":0,"delTime":"","remark":""},{"id":"14","name":"各街道卫生服务中心","responsibilityName":"","content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">各街道卫生服务中心<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648620000,"updateUser":"admin","updateTime":1520233262000,"delFlag":0,"delTime":"","remark":""},{"id":"15","name":"泰山区残疾人辅助器具服务中心","responsibilityName":"","content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">泰山区残疾人辅助器具服务中心<\/strong><\/span>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648622000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"2","name":"泰山区小天使康复中心","responsibilityName":"","content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">泰山区小天使康复中心<\/strong><\/span>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648625000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""}]}
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
         * totalCount : 15
         * pageNo : 0
         * list : [{"id":"1","name":"泰山区人民医院儿康中心","responsibilityName":"测试","content":"<span style=\"font-size:32px;font-family:KaiTi_GB2312;\"><strong>泰山区人民医院儿康中心<\/strong><\/span><br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"18880868988","xAxis":"117.139116","yAxis":"36.197475","bdContent":"","createUser":"admin","createTime":1510130204000,"updateUser":"admin","updateTime":1521793035000,"delFlag":0,"delTime":1520473718000,"remark":""},{"id":"10","name":"泰安市中医二院","responsibilityName":"","content":"<strong><span style=\"font-size:32px;font-family:KaiTi_GB2312;\">泰安市中医二院<\/span><\/strong><br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510735008000,"updateUser":"admin","updateTime":1520477590000,"delFlag":0,"delTime":"","remark":""},{"id":"11","name":"泰山区人民医院","responsibilityName":"","content":"<span style=\"font-size:32px;font-family:KaiTi_GB2312;\">泰山区人民医院<\/span><strong><span style=\"font-size:32px;\"><\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"117.150758","yAxis":"36.186023","bdContent":"","createUser":"admin","createTime":1510648612000,"updateUser":"admin","updateTime":1520574240000,"delFlag":0,"delTime":"","remark":""},{"id":"12","name":"省庄镇卫生院","responsibilityName":"","content":"<span style=\"font-family:KaiTi_GB2312;font-size:32px;\"><strong>省庄镇卫生院<\/strong><\/span>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648615000,"updateUser":"admin","updateTime":1520233047000,"delFlag":0,"delTime":"","remark":""},{"id":"13","name":"邱家店镇卫生院","responsibilityName":"","content":"<strong><span style=\"font-size:32px;font-family:KaiTi_GB2312;\">邱家店镇卫生院<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648618000,"updateUser":"admin","updateTime":1520233175000,"delFlag":0,"delTime":"","remark":""},{"id":"14","name":"各街道卫生服务中心","responsibilityName":"","content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">各街道卫生服务中心<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648620000,"updateUser":"admin","updateTime":1520233262000,"delFlag":0,"delTime":"","remark":""},{"id":"15","name":"泰山区残疾人辅助器具服务中心","responsibilityName":"","content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">泰山区残疾人辅助器具服务中心<\/strong><\/span>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648622000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"2","name":"泰山区小天使康复中心","responsibilityName":"","content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">泰山区小天使康复中心<\/strong><\/span>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"","village":"","phone":"","xAxis":"","yAxis":"","bdContent":"","createUser":"admin","createTime":1510648625000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""}]
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
             * name : 泰山区人民医院儿康中心
             * responsibilityName : 测试
             * content : <span style="font-size:32px;font-family:KaiTi_GB2312;"><strong>泰山区人民医院儿康中心</strong></span><br />
             * provinceID : 370000
             * cityID : 370900
             * regionID : 370902
             * street :
             * village :
             * phone : 18880868988
             * xAxis : 117.139116
             * yAxis : 36.197475
             * bdContent :
             * createUser : admin
             * createTime : 1510130204000
             * updateUser : admin
             * updateTime : 1521793035000
             * delFlag : 0
             * delTime : 1520473718000
             * remark :
             */

            private String id;
            private String name;
            private String responsibilityName;
            private String content;
            private String provinceID;
            private String cityID;
            private String regionID;
            private String street;
            private String village;
            private String phone;
            private double xAxis;
            private double yAxis;
            private String bdContent;
            private String createUser;
            private long createTime;
            private String updateUser;
            private long updateTime;
            private int delFlag;
            private long delTime;
            private String remark;

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

            public String getResponsibilityName() {
                return responsibilityName;
            }

            public void setResponsibilityName(String responsibilityName) {
                this.responsibilityName = responsibilityName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getProvinceID() {
                return provinceID;
            }

            public void setProvinceID(String provinceID) {
                this.provinceID = provinceID;
            }

            public String getCityID() {
                return cityID;
            }

            public void setCityID(String cityID) {
                this.cityID = cityID;
            }

            public String getRegionID() {
                return regionID;
            }

            public void setRegionID(String regionID) {
                this.regionID = regionID;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getVillage() {
                return village;
            }

            public void setVillage(String village) {
                this.village = village;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public double getXAxis() {
                return xAxis;
            }

            public void setXAxis(double xAxis) {
                this.xAxis = xAxis;
            }

            public double getYAxis() {
                return yAxis;
            }

            public void setYAxis(double yAxis) {
                this.yAxis = yAxis;
            }

            public String getBdContent() {
                return bdContent;
            }

            public void setBdContent(String bdContent) {
                this.bdContent = bdContent;
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

            public long getDelTime() {
                return delTime;
            }

            public void setDelTime(long delTime) {
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
