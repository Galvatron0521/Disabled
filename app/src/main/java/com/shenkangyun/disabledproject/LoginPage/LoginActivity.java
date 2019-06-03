package com.shenkangyun.disabledproject.LoginPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.CanjiBean;
import com.shenkangyun.disabledproject.BeanFolder.LoginBean;
import com.shenkangyun.disabledproject.BeanFolder.ServiceProjectBean;
import com.shenkangyun.disabledproject.DBFolder.ServiceProject;
import com.shenkangyun.disabledproject.DBFolder.User;
import com.shenkangyun.disabledproject.DBFolder.canjiType;
import com.shenkangyun.disabledproject.MainPage.MainActivity;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_account)
    EditText loginAccount;
    @BindView(R.id.login_passWord)
    EditText loginPassWord;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private SharedPreferences sp;
    private String userName;
    private String password;
    private String accountTxt;
    private String passWordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FuncUtil.iniSystemBar(this, R.color.white);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userName = sp.getString("USER_NAME", "");
        password = sp.getString("PASSWORD", "");
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            accountTxt = userName;
            passWordTxt = password;
            loginAccount.setText(userName);
            loginPassWord.setText(password);
            initLogin();
        }
        if (!TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
            accountTxt = userName;
            loginAccount.setText(userName);
        }

        Message message = new Message();
        message.arg1 = 1;
        handler.sendMessage(message);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == 1) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DataSupport.deleteAll(ServiceProject.class);
                        DataSupport.deleteAll(canjiType.class);
                        OkHttpUtils.post()
                                .url(Base.URL)
                                .addParams("act", "selectContent")
                                .addParams("data", new selectContent(Base.getMD5Str(), Base.getTimeSpan(), "1", "canjiType").toJson())
                                .build()
                                .execute(new GsonCallBack<CanjiBean>() {
                                    @Override
                                    public void onSuccess(CanjiBean response) throws JSONException {
                                        for (int i = 0; i < response.getData().getSelectList().size(); i++) {
                                            canjiType canjiType = new canjiType();
                                            int detailCode = response.getData().getSelectList().get(i).getTypeDetailCode();
                                            String detailName = response.getData().getSelectList().get(i).getTypeDetailName();

                                            canjiType.setTypeDetailCode(detailCode);
                                            canjiType.setTypeDetailName(detailName);
                                            canjiType.save();
                                        }
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });

                        OkHttpUtils.post()
                                .url(Base.URL)
                                .addParams("act", "selectContent")
                                .addParams("data", new selectContent(Base.getMD5Str(), Base.getTimeSpan(), "1", "serviceObject").toJson())
                                .build()
                                .execute(new GsonCallBack<ServiceProjectBean>() {
                                    @Override
                                    public void onSuccess(ServiceProjectBean response) throws JSONException {
                                        for (int i = 0; i < response.getData().getSelectList().size(); i++) {
                                            ServiceProject serviceProject = new ServiceProject();
                                            int detailCode = response.getData().getSelectList().get(i).getTypeDetailCode();
                                            String detailName = response.getData().getSelectList().get(i).getTypeDetailName();

                                            serviceProject.setTypeDetailCode(detailCode);
                                            serviceProject.setTypeDetailName(detailName);
                                            serviceProject.save();
                                        }
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                    }
                }).start();
            }
        }
    };

    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                accountTxt = loginAccount.getText().toString();
                passWordTxt = loginPassWord.getText().toString();
                if (TextUtils.isEmpty(accountTxt) && TextUtils.isEmpty(passWordTxt)) {
                    Toast.makeText(this, "请将用户名或密码填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    initLogin();
                }
                break;
        }
    }

    private void initLogin() {
        DataSupport.deleteAll(User.class);
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "login")
                .addParams("data", new Login(Base.getMD5Str(), Base.getTimeSpan(), "1", accountTxt, passWordTxt).toJson())
                .build().execute(new GsonCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean response) throws JSONException {

                String status = response.getStatus();
                if ("0".equals(status)) {
                    initUserInfo(response);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USER_NAME", accountTxt);
                    editor.putString("PASSWORD", passWordTxt);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initUserInfo(LoginBean response) {
        User user = new User();
        int id = response.getData().getPatient().getId();
        int age = response.getData().getPatient().getAge();
        int isCheck = response.getData().getPatient().getIsCheck();
        String sex = response.getData().getPatient().getSex();
        String name = response.getData().getPatient().getName();
        String mobile = response.getData().getPatient().getMobile();
        String idCard = response.getData().getPatient().getIdCard();
        String idType = response.getData().getPatient().getIdType();
        String street = response.getData().getPatient().getStreet();
        String cityID = response.getData().getPatient().getCityID();
        String address = response.getData().getPatient().getAddress();
        String canjiType = response.getData().getPatient().getCanjiType();
        String provinceID = response.getData().getPatient().getProvinceID();
        String TypeContent = response.getData().getPatient().getCanjiTypeContent();
        String contactPerson = response.getData().getPatient().getContactPerson();
        String certificateStatus = response.getData().getPatient().getCertificateStatus();
        String certificateContent = response.getData().getPatient().getCertificateStatusContent();
        user.setUserID(id);
        user.setAge(age);
        user.setIsCheck(isCheck);
        user.setSex(sex);
        user.setName(name);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setIdType(idType);
        user.setStreet(street);
        user.setCityID(cityID);
        user.setAddress(address);
        user.setCanjiType(canjiType);
        user.setProvinceID(provinceID);
        user.setCanjiTypeContent(TypeContent);
        user.setContactPerson(contactPerson);
        user.setCertificateStatus(certificateStatus);
        user.setCertificateStatusContent(certificateContent);
        user.save();
    }

    static class selectContent {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String typeCode;

        public selectContent(String appKey, String timeSpan, String mobileType, String typeCode) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.typeCode = typeCode;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class Login {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String idCard;
        private String password;

        public Login(String appKey, String timeSpan, String mobileType, String idCard, String password) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.idCard = idCard;
            this.password = password;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
