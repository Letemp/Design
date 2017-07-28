package com.example.asus.design.activity;

import android.os.Bundle;
import android.view.View;

import com.example.asus.design.R;
import com.example.asus.design.utils.CustomToolBar;

public class ChangePasswordActivity extends BaseActivity {

    CustomToolBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar= (CustomToolBar) findViewById(R.id.toolbar);
        toolbar.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordActivity.this.finish();
            }
        });
    }
}
