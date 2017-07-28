package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asus.design.R;

public class ForgetPasswordActivity extends BaseActivity {

    private ImageView iv_back_forget_password;//返回按钮
    private Button btn_confirm;//确定按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        iv_back_forget_password = (ImageView)findViewById(R.id.iv_back_forget_password);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);

        //返回按钮的监听事件
        iv_back_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //确定按钮的监听事件
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
