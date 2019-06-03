package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class ServiceProjectBean {

    /**
     * status : 0
     * data : {"selectList":[{"typeName":"服务对象","typeDetailCode":1,"typeCode":"serviceObject","typeDetailName":"盲人"},{"typeName":"服务对象","typeDetailCode":2,"typeCode":"serviceObject","typeDetailName":"低视力者"},{"typeName":"服务对象","typeDetailCode":3,"typeCode":"serviceObject","typeDetailName":"0-6岁儿童"},{"typeName":"服务对象","typeDetailCode":4,"typeCode":"serviceObject","typeDetailName":"0-11岁儿童"},{"typeName":"服务对象","typeDetailCode":5,"typeCode":"serviceObject","typeDetailName":"12-17岁儿童少年"},{"typeName":"服务对象","typeDetailCode":6,"typeCode":"serviceObject","typeDetailName":"成人听力残疾人"},{"typeName":"服务对象","typeDetailCode":7,"typeCode":"serviceObject","typeDetailName":"0-11岁(矫治手术年龄可放宽到16岁)"},{"typeName":"服务对象","typeDetailCode":8,"typeCode":"serviceObject","typeDetailName":"12岁以上肢体残疾人"},{"typeName":"服务对象","typeDetailCode":9,"typeCode":"serviceObject","typeDetailName":"12岁以上智力残疾人"},{"typeName":"服务对象","typeDetailCode":10,"typeCode":"serviceObject","typeDetailName":"成年精神残疾人"}]}
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
        private List<SelectListBean> selectList;

        public List<SelectListBean> getSelectList() {
            return selectList;
        }

        public void setSelectList(List<SelectListBean> selectList) {
            this.selectList = selectList;
        }

        public static class SelectListBean {
            /**
             * typeName : 服务对象
             * typeDetailCode : 1
             * typeCode : serviceObject
             * typeDetailName : 盲人
             */

            private String typeName;
            private int typeDetailCode;
            private String typeCode;
            private String typeDetailName;

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public int getTypeDetailCode() {
                return typeDetailCode;
            }

            public void setTypeDetailCode(int typeDetailCode) {
                this.typeDetailCode = typeDetailCode;
            }

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getTypeDetailName() {
                return typeDetailName;
            }

            public void setTypeDetailName(String typeDetailName) {
                this.typeDetailName = typeDetailName;
            }
        }
    }
}
