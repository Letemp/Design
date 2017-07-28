package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.design.R;

public class RegisterActivity extends BaseActivity {

    private ImageView iv_back_register;//返回按钮
    private Button btn_register;//注册按钮
    private TextView tv_login_soon;//立即登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iv_back_register = (ImageView)findViewById(R.id.iv_back_register);
        btn_register = (Button)findViewById(R.id.btn_register);
        tv_login_soon = (TextView)findViewById(R.id.tv_login_soon);

        //返回按钮的监听事件
        iv_back_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,WelcomeActivity.class);
                intent.putExtra("back",1);
                startActivity(intent);
                finish();
            }
        });

        //注册按钮的监听事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //立即登录按钮的监听事件
        tv_login_soon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
