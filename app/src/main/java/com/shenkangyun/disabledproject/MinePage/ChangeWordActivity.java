package com.shenkangyun.disabledproject.MinePage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shenkangyun.disabledproject.BaseFolder.Base;
import com.shenkangyun.disabledproject.BeanFolder.ChangeResult;
import com.shenkangyun.disabledproject.BeanFolder.ResultBean;
import com.shenkangyun.disabledproject.DBFolder.User;
import com.shenkangyun.disabledproject.LoginPage.LoginActivity;
import com.shenkangyun.disabledproject.R;
import com.shenkangyun.disabledproject.UtilsFolder.FuncUtil;
import com.shenkangyun.disabledproject.UtilsFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ChangeWordActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.change_old)
    EditText changeOld;
    @BindView(R.id.change_new)
    EditText changeNew;
    @BindView(R.id.change_confirm)
    EditText changeConfirm;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private String idCard;
    private String old;
    private String newWord;
    private String comfirm;
    private List<User> users;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_word);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.white);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("更改密码");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
    }

    private void initView() {
        users = new ArrayList<>();
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        users = DataSupport.findAll(User.class);
        idCard = users.get(0).getIdCard();
        btnLogin.setOnClickListener(this);
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "updatePassword")
                .addParams("data", new updatePassword(Base.getMD5Str(), Base.getTimeSpan(), "1", Base.getUserID(), idCard, old, newWord).toJson())
                .build().execute(new GsonCallBack<ChangeResult>() {
            @Override
            public void onSuccess(ChangeResult response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("PASSWORD", "");
                    edit.commit();
                    Intent intentBack = new Intent();

                    setResult(7, intentBack);
                    Intent intentLogin = new Intent(ChangeWordActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                } else {
                    Toast.makeText(ChangeWordActivity.this, response.getData().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                old = changeOld.getText().toString();
                newWord = changeNew.getText().toString();
                comfirm = changeConfirm.getText().toString();
                if (TextUtils.isEmpty(old) || TextUtils.isEmpty(newWord) || TextUtils.isEmpty(comfirm)) {
                    Toast.makeText(this, "请确保密码信息已填写完全", Toast.LENGTH_SHORT).show();
                } else {
                    initData();
                }
                break;
        }
    }

    static class updatePassword {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String id;
        private String idCard;
        private String password;
        private String passwordother;

        public updatePassword(String appKey, String timeSpan, String mobileType, String id, String idCard, String password, String passwordother) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.id = id;
            this.idCard = idCard;
            this.password = password;
            this.passwordother = passwordother;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
