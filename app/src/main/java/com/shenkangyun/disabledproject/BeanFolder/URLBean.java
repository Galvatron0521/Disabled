package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */

public class URLBean {

    private List<JsonBean> json;

    public List<JsonBean> getJson() {
        return json;
    }

    public void setJson(List<JsonBean> json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * sType : 1
         * attachmentID : 34
         * attachmentUrl : attachment/crfImage/20180303/0824474028805261fdd89e0161fdd89e5b0000.png
         */

        private int sType;
        private int attachmentID;
        private String attachmentUrl;

        public int getSType() {
            return sType;
        }

        public void setSType(int sType) {
            this.sType = sType;
        }

        public int getAttachmentID() {
            return attachmentID;
        }

        public void setAttachmentID(int attachmentID) {
            this.attachmentID = attachmentID;
        }

        public String getAttachmentUrl() {
            return attachmentUrl;
        }

        public void setAttachmentUrl(String attachmentUrl) {
            this.attachmentUrl = attachmentUrl;
        }
    }
}
