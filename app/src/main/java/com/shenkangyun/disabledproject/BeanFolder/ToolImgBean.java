package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class ToolImgBean {

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
         * attachmentID : 32
         * attachmentUrl : attachment/crfImage/20180305/1054124028801e61f4145a0161f414b1820003.png
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
