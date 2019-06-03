package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class NewsBean {

    /**
     * status : 0
     * data : {"newslist":[{"id":7,"newsType":1,"title":"321","picUrl":null,"content":"1232123","createTime":1519176646000,"delFlag":0},{"id":5,"newsType":1,"title":"1231","picUrl":null,"content":"312313123","createTime":1519176448000,"delFlag":0},{"id":4,"newsType":1,"title":"123321111","picUrl":null,"content":"<strong>21232211<\/strong>","createTime":1517799264000,"delFlag":0},{"id":3,"newsType":1,"title":"23121","picUrl":null,"content":"<strong><span style=\"color:#9933E5;font-size:32px;\">123123333<\/span><\/strong>","createTime":1517799126000,"delFlag":0}]}
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
        private List<NewslistBean> newslist;

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean {
            /**
             * id : 7
             * newsType : 1
             * title : 321
             * picUrl : null
             * content : 1232123
             * createTime : 1519176646000
             * delFlag : 0
             */

            private int id;
            private int newsType;
            private String title;
            private String picUrl;
            private String content;
            private long createTime;
            private int delFlag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNewsType() {
                return newsType;
            }

            public void setNewsType(int newsType) {
                this.newsType = newsType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }
        }
    }
}
