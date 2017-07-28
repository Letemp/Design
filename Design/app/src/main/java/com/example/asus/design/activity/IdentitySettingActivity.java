package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.asus.design.R;
import com.example.asus.design.utils.CustomToolBar;

public class IdentitySettingActivity extends BaseActivity {

    RelativeLayout rl_change_password;
    RelativeLayout rl_change_phone;
    CustomToolBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_setting);

        rl_change_password = (RelativeLayout) findViewById(R.id.rl_change_password);
        rl_change_phone = (RelativeLayout) findViewById(R.id.rl_change_phone);

        toolbar= (CustomToolBar) findViewById(R.id.toolbar);
        toolbar.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentitySettingActivity.this.finish();
            }
        });

        rl_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentitySettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rl_change_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentitySettingActivity.this, ChangePhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
