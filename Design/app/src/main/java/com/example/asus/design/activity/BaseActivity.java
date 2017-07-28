package com.example.asus.design.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.design.R;
import com.example.asus.design.utils.StatusBarUtil;

/**
 * Created by ASUS on 2017/6/21.
 */

//activity公共的方法类
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();//设置状态栏颜色的方法
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    //设置状态栏颜色的方法
    public void setStatusBar(){
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent),0);
    }

    //在fragment中调用该方法
    public BaseActivity getBaseActivity() {
        return this;
    }

}
