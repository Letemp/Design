package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.design.R;
import com.example.asus.design.utils.CustomToolBar;

public class MySettingActivity extends BaseActivity {

    LinearLayout ll_personal_setting;
    LinearLayout ll_identity_setting;
    LinearLayout ll_about_design;
    LinearLayout ll_user_feedback;
    TextView tv_clean;
    TextView tv_quit;
    CustomToolBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        ll_personal_setting = (LinearLayout)findViewById(R.id.ll_personal_setting);
        ll_identity_setting = (LinearLayout)findViewById(R.id.ll_identity_setting);
        ll_about_design = (LinearLayout)findViewById(R.id.ll_about_design);
        ll_user_feedback = (LinearLayout)findViewById(R.id.ll_user_feedback);
        tv_clean = (TextView)findViewById(R.id.tv_clean);
        tv_quit = (TextView)findViewById(R.id.tv_quit);

        toolbar= (CustomToolBar) findViewById(R.id.toolbar);
        toolbar.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySettingActivity.this.finish();
            }
        });

        ll_personal_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, PersonalSettingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ll_identity_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, IdentitySettingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ll_about_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, AboutDesignActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ll_user_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this, UserFeedbackActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MySettingActivity.this, "清除缓存完毕", Toast.LENGTH_SHORT).show();
            }
        });

        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySettingActivity.this,WelcomeActivity.class);
                intent.putExtra("back",1);
                startActivity(intent);
                finish();
            }
        });

    }
}
