package com.shenkangyun.disabledproject.UtilsFolder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.shenkangyun.disabledproject.BeanFolder.BwlBean;
import com.shenkangyun.disabledproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 16001 on 2017/10/19 0019.
 */

public class EditTextDialog {

    public interface CallBack {
        void Confirm(BwlBean bean);
    }

    private static String StartTime;
    private static String EndTime;

    public static AlertDialog init(int id, final Context content, final CallBack callBack) {
        final View view = LayoutInflater.from(content).inflate(id, null, false);
        final EditText ed_title = view.findViewById(R.id.ed_title);
        final EditText ed_content = view.findViewById(R.id.ed_cot);
        final TextView tv_start = view.findViewById(R.id.tv_start);
        final TextView tv_end = view.findViewById(R.id.tv_end);

        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(content);
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        StartTime = String.valueOf(date.getTime());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String format = simpleDateFormat.format(date);
                        tv_start.setText(format);
                    }
                });
                datePickDialog.show();
            }
        });
        tv_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog datePickDialog = new DatePickDialog(content);
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        EndTime = String.valueOf(date.getTime());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String format = simpleDateFormat.format(date);
                        tv_end.setText(format);
                    }
                });
                datePickDialog.show();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(content);
        final AlertDialog alertDialog = builder.setTitle("添加备忘录")
                .setView(view)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("insert", "onClick: " + StartTime + "\t" + EndTime);
                        BwlBean bwlBean = new BwlBean(ed_title.getText().toString(),
                                ed_content.getText().toString(), false, StartTime, EndTime);
                        callBack.Confirm(bwlBean);

                    }
                })
                .setPositiveButton("取消", null)
                .create();
        return alertDialog;
    }
}
