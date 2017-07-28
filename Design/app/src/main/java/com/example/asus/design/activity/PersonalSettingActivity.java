package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.asus.design.R;
import com.example.asus.design.utils.CustomToolBar;

public class PersonalSettingActivity extends BaseActivity {

    RelativeLayout rl_username;
    RelativeLayout rl_introduction;
    CustomToolBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);
        toolbar= (CustomToolBar) findViewById(R.id.toolbar);
        toolbar.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalSettingActivity.this.finish();
            }
        });
        rl_username = (RelativeLayout) findViewById(R.id.rl_username);
        rl_introduction = (RelativeLayout) findViewById(R.id.rl_introduction);

        rl_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalSettingActivity.this, ChangeUsernameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rl_introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalSettingActivity.this, IntroductionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
