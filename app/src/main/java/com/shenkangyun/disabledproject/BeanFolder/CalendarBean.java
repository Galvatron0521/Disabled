package com.shenkangyun.disabledproject.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class CalendarBean {


    /**
     * status : 0
     * data : {"list":[{"calendarid":24,"patientid":1,"title":"工作","calendardescribe":"工作内容","start":1504137600000,"end":1504224000000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1504942798000},{"calendarid":30,"patientid":1,"title":"出门旅游","calendardescribe":"旅游哈哈哈","start":1504483200000,"end":1504569600000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505024043000},{"calendarid":31,"patientid":1,"title":"444","calendardescribe":"444","start":1504742400000,"end":1504828800000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505116675000},{"calendarid":32,"patientid":1,"title":"名称","calendardescribe":"6666","start":1504137600000,"end":1504224000000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800776000},{"calendarid":33,"patientid":1,"title":"生日","calendardescribe":"19808080","start":1504051200000,"end":1504137600000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800799000},{"calendarid":34,"patientid":1,"title":"明天放假","calendardescribe":"1","start":1506729600000,"end":1506816000000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800837000},{"calendarid":35,"patientid":1,"title":"1","calendardescribe":"1","start":1506357000000,"end":1506358800000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800865000},{"calendarid":36,"patientid":1,"title":"1","calendardescribe":"1","start":1506355200000,"end":1506357000000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1505800883000},{"calendarid":38,"patientid":1,"title":"test","calendardescribe":"147258","start":1520045788000,"end":1520045790000,"delflag":"0","calendartype":null,"remark2":0,"remark3":0,"remark4":1520045794000}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * calendarid : 24
             * patientid : 1
             * title : 工作
             * calendardescribe : 工作内容
             * start : 1504137600000
             * end : 1504224000000
             * delflag : 0
             * calendartype : null
             * remark2 : 0
             * remark3 : 0
             * remark4 : 1504942798000
             */

            private int calendarid;
            private int patientid;
            private String title;
            private String calendardescribe;
            private long start;
            private long end;
            private String delflag;
            private Object calendartype;
            private int remark2;
            private int remark3;
            private long remark4;

            public int getCalendarid() {
                return calendarid;
            }

            public void setCalendarid(int calendarid) {
                this.calendarid = calendarid;
            }

            public int getPatientid() {
                return patientid;
            }

            public void setPatientid(int patientid) {
                this.patientid = patientid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCalendardescribe() {
                return calendardescribe;
            }

            public void setCalendardescribe(String calendardescribe) {
                this.calendardescribe = calendardescribe;
            }

            public long getStart() {
                return start;
            }

            public void setStart(long start) {
                this.start = start;
            }

            public long getEnd() {
                return end;
            }

            public void setEnd(long end) {
                this.end = end;
            }

            public String getDelflag() {
                return delflag;
            }

            public void setDelflag(String delflag) {
                this.delflag = delflag;
            }

            public Object getCalendartype() {
                return calendartype;
            }

            public void setCalendartype(Object calendartype) {
                this.calendartype = calendartype;
            }

            public int getRemark2() {
                return remark2;
            }

            public void setRemark2(int remark2) {
                this.remark2 = remark2;
            }

            public int getRemark3() {
                return remark3;
            }

            public void setRemark3(int remark3) {
                this.remark3 = remark3;
            }

            public long getRemark4() {
                return remark4;
            }

            public void setRemark4(long remark4) {
                this.remark4 = remark4;
            }
        }
    }
}
