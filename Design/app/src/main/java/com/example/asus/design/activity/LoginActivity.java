package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.design.R;

public class LoginActivity extends BaseActivity {

    private ImageView iv_back_login;//返回按钮
    private Button btn_login;//登录按钮
    private TextView tv_register_soon;//立即注册按钮
    private TextView tv_forget_password;//忘记密码按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv_back_login = (ImageView)findViewById(R.id.iv_back_login);
        btn_login = (Button)findViewById(R.id.btn_login);
        tv_register_soon = (TextView)findViewById(R.id.tv_register_soon);
        tv_forget_password = (TextView)findViewById(R.id.tv_forget_password);

        //返回按钮的监听事件
        iv_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                intent.putExtra("back",1);
                startActivity(intent);
                finish();
            }
        });

        //登录按钮的监听事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //立即注册按钮的监听事件
        tv_register_soon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //忘记密码按钮的监听事件
        tv_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}
