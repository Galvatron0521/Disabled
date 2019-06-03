package com.shenkangyun.disabledproject.MinePage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.UpdateBean;
import com.shenkangyun.disabledproject.DBFolder.User;
import com.shenkangyun.disabledproject.LoginPage.LoginActivity;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.APKVersionCodeUtils;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MinePageFragment extends Fragment {
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    private SharedPreferences sp;
    private ProgressDialog pd;

    private String versionName;
    private String appVersion;
    private String appUrl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        User user = DataSupport.findFirst(User.class);
        String name = user.getName();
        String idCard = user.getIdCard();
        tvName.setText(name);
        tvNumber.setText(idCard);
        imgPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @OnClick({R.id.img_portrait, R.id.tv_changeWord, R.id.tv_quit, R.id.tv_update, R.id.tv_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_portrait:
                break;
            case R.id.tv_changeWord:
                Intent change = new Intent(getContext(), ChangeWordActivity.class);
                startActivityForResult(change, 6);
                break;
            case R.id.tv_quit:
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("PASSWORD", "");
                edit.commit();
                Intent quit = new Intent(getContext(), LoginActivity.class);
                startActivity(quit);
                getActivity().finish();
                break;
            case R.id.tv_update:
                initUpdate();
                break;
            case R.id.tv_about:
                Intent about = new Intent(getContext(), AboutActivity.class);
                startActivity(about);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6 && resultCode == 7) {
            getActivity().finish();
        }
    }

    private void initUpdate() {
        versionName = APKVersionCodeUtils.getVerName(getContext());
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "appVesionCompare")
                .addParams("data", new appVesionCompare(Base.getMD5Str(), Base.getTimeSpan(), "1", versionName).toJson())
                .build().execute(new GsonCallBack<UpdateBean>() {
            @Override
            public void onSuccess(UpdateBean response) throws JSONException {
                appVersion = response.getData().getData().getApp_parent_version();
                appUrl = response.getData().getData().getApp_parent_version_url();
                if (appVersion.equals(versionName)) {
                    Toast.makeText(getContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                } else {
                    showDialogUpdate();//弹出提示版本更新的对话框
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();

    }

    private void loadNewVersionProgress() {
        //进度条对话框
        pd = new ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(appUrl, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Message message = new Message();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                pd.dismiss();
                Toast.makeText(getContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data = FileProvider.getUriForFile(getContext(), "com.shenkangyun.disabledproject.fileprovider", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private File getFileFromServer(String appUrl, ProgressDialog pd) throws IOException {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(appUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    static class appVesionCompare {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String version;

        public appVesionCompare(String appKey, String timeSpan, String mobileType, String version) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.version = version;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
