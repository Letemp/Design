package com.example.asus.design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.design.R;

public class CommentActivity extends BaseActivity {

    Button btn_back_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        btn_back_comment = (Button)findViewById(R.id.btn_back_comment);

        btn_back_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, ProductionActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
