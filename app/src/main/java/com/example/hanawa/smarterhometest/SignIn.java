package com.example.hanawa.smarterhometest;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.utils.Defined;
import com.example.hanawa.smarterhometest.utils.GetJson;
import com.example.hanawa.smarterhometest.utils.HttpUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    String name;
    String pwd;
    private static String TAG="SignIn";
    EditText et_number,et_pwd;
    Button btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPreferences sh=getSharedPreferences("data",MODE_PRIVATE);
        name=sh.getString("name",null);
        pwd=sh.getString("pwd",null);
        initView();
    }

    private void initView() {
        et_number= (EditText) findViewById(R.id.et_number);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        et_pwd.addTextChangedListener(watcher);
        et_number.addTextChangedListener(watcher);
        btn_sign_in= (Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(this);

        if(name!=null&&pwd!=null){
            et_number.setText(name);
            et_pwd.setText(pwd);
            btn_sign_in.setText("已登录");
        }

        TextView toolbar_title= (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("请登录");
        LinearLayout toolbar_back= (LinearLayout) findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(this);
        toolbar_back.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et_number.setClipToOutline(true);
            et_number.setOutlineProvider(Defined.getOutline(false,15,15));
            et_pwd.setClipToOutline(true);
            et_pwd.setOutlineProvider(Defined.getOutline(false,15,15));
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.toolbar_back:
                this.finish();
                break;
            case R.id.btn_sign_in:
                new HttpUtils(SignIn.this,et_number,TAG).signIn(et_number.getText().toString(),et_pwd.getText().toString());
                break;
            default:
                break;
        }

    }


    TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btn_sign_in.setText("登录");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



}
