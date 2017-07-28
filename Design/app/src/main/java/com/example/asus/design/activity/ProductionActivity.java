package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.design.R;

public class ProductionActivity extends BaseActivity {

    Button btn_back_production;
    Button btn_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);

        btn_comment = (Button)findViewById(R.id.btn_comment);
        btn_back_production = (Button)findViewById(R.id.btn_back_production);

        btn_back_production.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, CommentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
