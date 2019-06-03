package com.shenkangyun.disabledproject.UtilsFolder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FuncUtil {
    public static void iniSystemBar(Activity ctx, int statusbarBgId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;  
        }  
        setTranslucentStatus(ctx, true);  
        SystemBarTintManager tintManager = new SystemBarTintManager(ctx);  
        tintManager.setStatusBarTintEnabled(true);  
        tintManager.setStatusBarTintResource(statusbarBgId);
        setStausTextColor(ctx);
    }  
  
    @TargetApi(19)
    public static void setTranslucentStatus(Activity ctx, boolean on) {  
        Window win = ctx.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;  
        if (on) {  
            winParams.flags |= bits;  
        } else {  
            winParams.flags &= ~bits;  
        }  
        win.setAttributes(winParams);  
    }  
    public static void setTitlebarPadding(Activity ctx, View headerview) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {  
            return;  
        }  
        SystemBarTintManager tintManager = new SystemBarTintManager(ctx);  
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();  
        if (headerview != null) {  
            headerview.setPadding(0, config.getPixelInsetTop(true), 0,  config.getPixelInsetBottom());  
        }  
    }
    public static void setStausTextColor(Activity activity){

        if(setMeizuStatusBarDarkIcon(activity,true))return;
        if(setMiuiStatusBarDarkMode(activity,true))return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.setStatusBarColor(activity.getResources().getColor(android.R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    //设置成白色的背景，字体颜色为黑色。
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //设置成白色的背景，字体颜色为黑色。
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

}