package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class PolicyBean {

    /**
     * status : 0
     * data : {"pageCount":100,"totalCount":1,"pageNo":0,"list":[{"id":"1","title":"残疾人驾驶汽车学费补贴政策","disabilityType":"4028801e61df786e0161df786eeb0000","content":"1、补贴对象。户口在泰山区；持有泰山区核发的《中华人民共和国残疾人证》；在指定的机动车驾驶培训单位培训，经泰安市交通警察支队考试取得合格驾驶证的残疾人（肢体为C5证，聋人为C1/C2证）。2、补贴标准。申请人参加驾驶汽车所需学费，由区残联一次性给予每人1500元的补贴。3、申请补贴所需资料。残疾人取得《中华人民共 和国机动车驾驶证》C5（C1/C2）驾照后，申请人携带以下资料到区残联提出申请：（1）身份证原件及其复印件（2）户口薄原件及其复印件（3）《中华人民共和国残疾人证》原件及其复印件（4）《中华人民共和国机动车驾驶证》原件及其复印件（5）驾校培训收费发票原件及其复印件。区残联在收到资料，审查核准后给予补贴，并对取得C5（C1/C2）驾驶执照的残疾人建档立卡、登记造册。","createUser":"admin","createTime":1510727337000,"updateUser":"admin","updateTime":1519872667000,"delFlag":0,"delTime":null,"remark":null,"typeDetailCode":1}]}
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
         * totalCount : 1
         * pageNo : 0
         * list : [{"id":"1","title":"残疾人驾驶汽车学费补贴政策","disabilityType":"4028801e61df786e0161df786eeb0000","content":"1、补贴对象。户口在泰山区；持有泰山区核发的《中华人民共和国残疾人证》；在指定的机动车驾驶培训单位培训，经泰安市交通警察支队考试取得合格驾驶证的残疾人（肢体为C5证，聋人为C1/C2证）。2、补贴标准。申请人参加驾驶汽车所需学费，由区残联一次性给予每人1500元的补贴。3、申请补贴所需资料。残疾人取得《中华人民共 和国机动车驾驶证》C5（C1/C2）驾照后，申请人携带以下资料到区残联提出申请：（1）身份证原件及其复印件（2）户口薄原件及其复印件（3）《中华人民共和国残疾人证》原件及其复印件（4）《中华人民共和国机动车驾驶证》原件及其复印件（5）驾校培训收费发票原件及其复印件。区残联在收到资料，审查核准后给予补贴，并对取得C5（C1/C2）驾驶执照的残疾人建档立卡、登记造册。","createUser":"admin","createTime":1510727337000,"updateUser":"admin","updateTime":1519872667000,"delFlag":0,"delTime":null,"remark":null,"typeDetailCode":1}]
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
             * title : 残疾人驾驶汽车学费补贴政策
             * disabilityType : 4028801e61df786e0161df786eeb0000
             * content : 1、补贴对象。户口在泰山区；持有泰山区核发的《中华人民共和国残疾人证》；在指定的机动车驾驶培训单位培训，经泰安市交通警察支队考试取得合格驾驶证的残疾人（肢体为C5证，聋人为C1/C2证）。2、补贴标准。申请人参加驾驶汽车所需学费，由区残联一次性给予每人1500元的补贴。3、申请补贴所需资料。残疾人取得《中华人民共 和国机动车驾驶证》C5（C1/C2）驾照后，申请人携带以下资料到区残联提出申请：（1）身份证原件及其复印件（2）户口薄原件及其复印件（3）《中华人民共和国残疾人证》原件及其复印件（4）《中华人民共和国机动车驾驶证》原件及其复印件（5）驾校培训收费发票原件及其复印件。区残联在收到资料，审查核准后给予补贴，并对取得C5（C1/C2）驾驶执照的残疾人建档立卡、登记造册。
             * createUser : admin
             * createTime : 1510727337000
             * updateUser : admin
             * updateTime : 1519872667000
             * delFlag : 0
             * delTime : null
             * remark : null
             * typeDetailCode : 1
             */

            private String id;
            private String title;
            private String disabilityType;
            private String content;
            private String createUser;
            private long createTime;
            private String updateUser;
            private long updateTime;
            private int delFlag;
            private Object delTime;
            private Object remark;
            private int typeDetailCode;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDisabilityType() {
                return disabilityType;
            }

            public void setDisabilityType(String disabilityType) {
                this.disabilityType = disabilityType;
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

            public int getTypeDetailCode() {
                return typeDetailCode;
            }

            public void setTypeDetailCode(int typeDetailCode) {
                this.typeDetailCode = typeDetailCode;
            }
        }
    }
}
