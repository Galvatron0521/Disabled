package com.shenkangyun.disabledproject.BeanFolder;

/**
 * Created by Administrator on 2018/2/22.
 */

public class LoginBean {


    /**
     * status : 0
     * data : {"patient":{"id":1,"name":"石屹哲","sex":"男","age":5,"address":"泰安市财源街道三里社区","provinceID":null,"cityID":null,"regionID":null,"street":null,"mobile":"18253806536","contactPerson":"","idType":"1","idCard":"370902201205270319","password":"e10adc3949ba59abbe56e057f20f883e","canjiType":"7","canjiTypeContent":"","certificateStatus":"2","certificateStatusContent":"非持证残疾儿童","isCheck":1,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}}
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
         * patient : {"id":1,"name":"石屹哲","sex":"男","age":5,"address":"泰安市财源街道三里社区","provinceID":null,"cityID":null,"regionID":null,"street":null,"mobile":"18253806536","contactPerson":"","idType":"1","idCard":"370902201205270319","password":"e10adc3949ba59abbe56e057f20f883e","canjiType":"7","canjiTypeContent":"","certificateStatus":"2","certificateStatusContent":"非持证残疾儿童","isCheck":1,"createUser":null,"createTime":null,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}
         */

        private PatientBean patient;

        public PatientBean getPatient() {
            return patient;
        }

        public void setPatient(PatientBean patient) {
            this.patient = patient;
        }

        public static class PatientBean {
            /**
             * id : 1
             * name : 石屹哲
             * sex : 男
             * age : 5
             * address : 泰安市财源街道三里社区
             * provinceID : null
             * cityID : null
             * regionID : null
             * street : null
             * mobile : 18253806536
             * contactPerson :
             * idType : 1
             * idCard : 370902201205270319
             * password : e10adc3949ba59abbe56e057f20f883e
             * canjiType : 7
             * canjiTypeContent :
             * certificateStatus : 2
             * certificateStatusContent : 非持证残疾儿童
             * isCheck : 1
             * createUser : null
             * createTime : null
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private int id;
            private String name;
            private String sex;
            private int age;
            private String address;
            private String provinceID;
            private String cityID;
            private String regionID;
            private String street;
            private String mobile;
            private String contactPerson;
            private String idType;
            private String idCard;
            private String password;
            private String canjiType;
            private String canjiTypeContent;
            private String certificateStatus;
            private String certificateStatusContent;
            private int isCheck;
            private String createUser;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String delTime;
            private String remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getContactPerson() {
                return contactPerson;
            }

            public void setContactPerson(String contactPerson) {
                this.contactPerson = contactPerson;
            }

            public String getIdType() {
                return idType;
            }

            public void setIdType(String idType) {
                this.idType = idType;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCanjiType() {
                return canjiType;
            }

            public void setCanjiType(String canjiType) {
                this.canjiType = canjiType;
            }

            public String getCanjiTypeContent() {
                return canjiTypeContent;
            }

            public void setCanjiTypeContent(String canjiTypeContent) {
                this.canjiTypeContent = canjiTypeContent;
            }

            public String getCertificateStatus() {
                return certificateStatus;
            }

            public void setCertificateStatus(String certificateStatus) {
                this.certificateStatus = certificateStatus;
            }

            public String getCertificateStatusContent() {
                return certificateStatusContent;
            }

            public void setCertificateStatusContent(String certificateStatusContent) {
                this.certificateStatusContent = certificateStatusContent;
            }

            public int getIsCheck() {
                return isCheck;
            }

            public void setIsCheck(int isCheck) {
                this.isCheck = isCheck;
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
