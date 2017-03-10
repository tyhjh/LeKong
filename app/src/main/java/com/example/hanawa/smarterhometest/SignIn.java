package com.example.hanawa.smarterhometest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.utils.Defined;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    EditText et_number,et_pwd;
    Button btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        initView();


    }

    private void initView() {
        et_number= (EditText) findViewById(R.id.et_number);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        btn_sign_in= (Button) findViewById(R.id.btn_sign_in);

        TextView toolbar_title= (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("请登录");
        LinearLayout toolbar_back= (LinearLayout) findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(this);
        toolbar_back.setVisibility(View.VISIBLE);

        et_number.setClipToOutline(true);
        et_number.setOutlineProvider(Defined.getOutline(false,15,10));

        et_pwd.setClipToOutline(true);
        et_pwd.setOutlineProvider(Defined.getOutline(false,15,10));

        btn_sign_in.setClipToOutline(true);
        btn_sign_in.setOutlineProvider(Defined.getOutline(false,15,10));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.toolbar_back:
                this.finish();
                break;
        }

    }
}
