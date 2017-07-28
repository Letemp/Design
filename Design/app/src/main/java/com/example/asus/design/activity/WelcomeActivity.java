package com.example.asus.design.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.asus.design.R;
import com.example.asus.design.adapter.WelcomePageAdapter;

import java.util.ArrayList;
import java.util.List;

//实现首次启动的引导页面
/*
 * 引导页面思路：
 * 1）先加载ViewPager，新建ImageView并添加到View集合中，然后设置Adapter并显示。
 * 2）然后加载LinearLayout，放置原点图片，同样新建ImageView设置背景后放在圆点图集合中。
 * 3）最后设置ViewPager的滑动监听事件，在滑动完成的监听OnPageSelected方法中，设置对应的圆点为选中，如果是最后一页，则显示按钮。
 *
 */
public class WelcomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private ViewPager vp;
    private int []imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点

    // 实例化原点View
    private ImageView iv_point;
    private ImageView []ivPointArray;

    //最后一页的按钮
    private Button btn_start_register;
    private Button btn_start_login;
    private int back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        back = getIntent().getIntExtra("back", 0);

        btn_start_register = (Button) findViewById(R.id.btn_start_register);
        btn_start_login = (Button) findViewById(R.id.btn_start_login);

        //设置注册按钮监听事件
        btn_start_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,RegisterActivity.class));
                finish();
            }
        });

        //设置登录按钮监听事件
        btn_start_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        });

        initViewPager(); //加载ViewPager
        initPoint();//加载底部圆点
    }

    //加载图片ViewPager
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.vp_welcome);
        imageIdArray = new int[]{R.drawable.welcome_1,R.drawable.welcome_2,R.drawable.bg_start};//实例化图片资源
        viewList = new ArrayList<>();

        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){

            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }
        vp.setAdapter(new WelcomePageAdapter(viewList));//View集合初始化好后，设置Adapter
        vp.setOnPageChangeListener(this);//设置滑动监听
    }
    //加载底部圆点
    private void initPoint() {
        vg = (ViewGroup) findViewById(R.id.ll_welcome_point);//这里实例化LinearLayout
        ivPointArray = new ImageView[viewList.size()];//根据ViewPager的item数量实例化数组

        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0;i<size;i++){
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(new ViewGroup.LayoutParams(40,40));
            iv_point.setPadding(50,0,50,0);//left,top,right,bottom
            ivPointArray[i] = iv_point;

            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0){
                iv_point.setBackgroundResource(R.drawable.point_white);
                }else{
                iv_point.setBackgroundResource(R.drawable.point_gray);
                }

            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    //滑动后的监听
    public void onPageSelected(int position) {

        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0;i<length;i++){
            ivPointArray[position].setBackgroundResource(R.drawable.point_white);
            if (position != i){
                ivPointArray[i].setBackgroundResource(R.drawable.point_gray);
                }
            }

        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            btn_start_register.setVisibility(View.VISIBLE);
            btn_start_login.setVisibility(View.VISIBLE);
            }else {
            btn_start_register.setVisibility(View.GONE);
            btn_start_login.setVisibility(View.GONE);
            }
        }

    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (back==1)
        {
            vp.setCurrentItem(2);
        }
    }
}

