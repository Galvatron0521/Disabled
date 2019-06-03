package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CanjiBean {

    /**
     * status : 0
     * data : {"selectList":[{"typeName":"残疾类型","typeDetailCode":1,"typeCode":"canjiType","typeDetailName":"智力"},{"typeName":"残疾类型","typeDetailCode":2,"typeCode":"canjiType","typeDetailName":"精神"},{"typeName":"残疾类型","typeDetailCode":3,"typeCode":"canjiType","typeDetailName":"肢体"},{"typeName":"残疾类型","typeDetailCode":4,"typeCode":"canjiType","typeDetailName":"听力"},{"typeName":"残疾类型","typeDetailCode":5,"typeCode":"canjiType","typeDetailName":"视力"},{"typeName":"残疾类型","typeDetailCode":6,"typeCode":"canjiType","typeDetailName":"言语"},{"typeName":"残疾类型","typeDetailCode":7,"typeCode":"canjiType","typeDetailName":"多重"},{"typeName":"残疾类型","typeDetailCode":8,"typeCode":"canjiType","typeDetailName":"其他"}]}
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
             * typeName : 残疾类型
             * typeDetailCode : 1
             * typeCode : canjiType
             * typeDetailName : 智力
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
