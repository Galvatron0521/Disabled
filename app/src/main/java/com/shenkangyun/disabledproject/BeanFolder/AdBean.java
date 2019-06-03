package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class AdBean {

    /**
     * status : 0
     * data : {"adlist":[{"aID":1,"adType":1,"picUrl":"{\"json\":[{\"sType\":1,\"attachmentID\":34,\"attachmentUrl\":\"attachment/crfImage/20180303/0824474028805261fdd89e0161fdd89e5b0000.png\"}]}","listOrder":1,"delFlag":0,"createTime":1518838680000},{"aID":2,"adType":1,"picUrl":"{\"json\":[{\"sType\":1,\"attachmentID\":35,\"attachmentUrl\":\"attachment/crfImage/20180303/0825084028805261fdd89e0161fdd8f0510002.png\"}]}","listOrder":2,"delFlag":0,"createTime":1519454295000},{"aID":4,"adType":1,"picUrl":"{\"json\":[{\"sType\":1,\"attachmentID\":36,\"attachmentUrl\":\"attachment/crfImage/20180303/0825224028805261fdd89e0161fdd9266e0004.png\"}]}","listOrder":3,"delFlag":0,"createTime":1520382323000},{"aID":5,"adType":1,"picUrl":"{\"json\":[{\"sType\":1,\"attachmentID\":37,\"attachmentUrl\":\"attachment/crfImage/20180303/0826364028805261fdd89e0161fdda490b0008.png\"}]}","listOrder":4,"delFlag":0,"createTime":1520382407000},{"aID":6,"adType":1,"picUrl":"{\"json\":[{\"sType\":1,\"attachmentID\":38,\"attachmentUrl\":\"attachment/crfImage/20180303/0826564028805261fdd89e0161fdda95c3000a.png\"}]}","listOrder":5,"delFlag":0,"createTime":1520382421000}]}
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
        private List<AdlistBean> adlist;

        public List<AdlistBean> getAdlist() {
            return adlist;
        }

        public void setAdlist(List<AdlistBean> adlist) {
            this.adlist = adlist;
        }

        public static class AdlistBean {
            /**
             * aID : 1
             * adType : 1
             * picUrl : {"json":[{"sType":1,"attachmentID":34,"attachmentUrl":"attachment/crfImage/20180303/0824474028805261fdd89e0161fdd89e5b0000.png"}]}
             * listOrder : 1
             * delFlag : 0
             * createTime : 1518838680000
             */

            private int aID;
            private int adType;
            private String picUrl;
            private int listOrder;
            private int delFlag;
            private long createTime;

            public int getAID() {
                return aID;
            }

            public void setAID(int aID) {
                this.aID = aID;
            }

            public int getAdType() {
                return adType;
            }

            public void setAdType(int adType) {
                this.adType = adType;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getListOrder() {
                return listOrder;
            }

            public void setListOrder(int listOrder) {
                this.listOrder = listOrder;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }
}
