package com.shenkangyun.disabledproject.BaseFolder;

import com.shenkangyun.disabledproject.DBFolder.User;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/22.
 */

public class Base {

//    public static final String URL = "http://111.17.215.37:803/sky_canlian/api/app_patient/";
//    public static final String imageURLs = "http://111.17.215.37:803/";
//    public static final String ImgURl = "http://111.17.215.37:803/form/fileUpload.html?modules=crfImage&mobileType=1";

    public static final String URL = "http://192.168.0.210:8080/sky_canlian/api/app_patient/";
    public static final String imageURLs = "http://192.168.0.210:8080/disability/";
    public static final String ImgURl = "http://192.168.0.210:8080/disability/form/fileUpload.html?modules=crfImage&mobileType=1";

//    public static final String imageURLs = "http://192.168.0.158:8080/disability/";
//    public static final String URL = "http://192.168.0.158:8080/sky_canlian/api/app_patient/";
//    public static final String ImgURl = "http://192.168.0.158:8080/disability/form/imagefileUpload.html?modules=crfImage&mobileType=1";

    public static String getMD5Str() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");
        String md5 = new Md5Hash("shenkangyun_canlian_patient", timeFormat.format(new Date()), 1).toString();
        return md5;
    }

    public static String getTimeSpan() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");
        return timeFormat.format(new Date());
    }

    public static String get() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");

        String md5 = new Md5Hash("shenkangyun_canlian_patient", timeFormat.format(new Date()), 1).toString();
        return md5;
    }

    public static String getUserID() {
        String id = null;
        List<User> users = DataSupport.findAll(User.class);
        for (int i = 0; i < users.size(); i++) {
            id = String.valueOf(users.get(i).getUserID());
        }
        return id;
    }

    public static String getUserName() {
        String name = null;
        List<User> users = DataSupport.findAll(User.class);
        for (int i = 0; i < users.size(); i++) {
            name = String.valueOf(users.get(i).getName());
        }
        return name;
    }

}
