package com.shenkangyun.disabledproject.BeanFolder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 16001 on 2017/10/19 0019.
 */
@Entity
public class BwlBean {

    @Id(autoincrement = true)
    private Long _id;
    String title = "";
    String content = "";
    boolean isTX = false;
    int day = 0;
    int mouth = 0;
    int year = 0;
    String time = "";
    String Start = "";
    String End = "";

    public BwlBean(String title, String content, boolean isTX, String Start, String End) {
        this.title = title;
        this.content = content;
        this.isTX = isTX;
        this.Start = Start;
        this.End = End;
    }

    @Generated(hash = 1404725093)
    public BwlBean(Long _id, String title, String content, boolean isTX, int day, int mouth,
                   int year, String time, String Start, String End) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.isTX = isTX;
        this.day = day;
        this.mouth = mouth;
        this.year = year;
        this.time = time;
        this.Start = Start;
        this.End = End;
    }

    @Generated(hash = 842931203)
    public BwlBean() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsTX() {
        return this.isTX;
    }

    public void setIsTX(boolean isTX) {
        this.isTX = isTX;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMouth() {
        return this.mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStart() {
        return this.Start;
    }

    public void setStart(String Start) {
        this.Start = Start;
    }

    public String getEnd() {
        return this.End;
    }

    public void setEnd(String End) {
        this.End = End;
    }


}
