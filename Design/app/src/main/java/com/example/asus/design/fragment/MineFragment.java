package com.example.asus.design.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.asus.design.R;
import com.example.asus.design.activity.MySettingActivity;
import com.example.asus.design.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/6/26.
 */
public class MineFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    ImageView iv_setting;
    private ViewPager vp_mine;
    //选项卡中的按钮  
    private Button btn_mine_like;
    private Button btn_mine_collect;
    private Button btn_mine_production;
    //作为指示标签的按钮  
    private ImageView btn_cursor;
    //标志指示标签的横坐标  
    float cursor_X = 0;
    //所有按钮的宽度的数组  
    private int[] widthArgs;
    //所有标题按钮的数组  
    private Button[] btnArgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);

    }

    //设置按钮的点击事件
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv_setting = (ImageView) getActivity().findViewById(R.id.iv_setting);
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySettingActivity.class);
                startActivity(intent);
            }
        });
        initView();
    }

    //初始化布局  
    public void initView() {
        vp_mine = (ViewPager) getActivity().findViewById(R.id.vp_mine);
        btn_mine_like = (Button) getActivity().findViewById(R.id.btn_mine_like);
        btn_mine_collect = (Button) getActivity().findViewById(R.id.btn_mine_collect);
        btn_mine_production = (Button) getActivity().findViewById(R.id.btn_mine_production);
        btnArgs = new Button[]{btn_mine_like, btn_mine_collect, btn_mine_production};

        //初始化按钮数组  
        btnArgs = new Button[]{btn_mine_like, btn_mine_collect, btn_mine_production};
        //指示标签设置为红色  
        btn_cursor = (ImageView) getActivity().findViewById(R.id.btn_cursor);
        btn_cursor.setBackgroundColor(Color.parseColor("#d54f3b"));

        //初始化指示器位置和大小
        btn_mine_like.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btn_cursor.getLayoutParams();
                //减去边距*2，以对齐标题栏文字 
                lp.width = btn_mine_like.getWidth() - btn_mine_like.getPaddingLeft() * 2;
                btn_cursor.setLayoutParams(lp);
                btn_cursor.setX(btn_mine_like.getPaddingLeft());
            }
        });

        //为myviewpager注册监听
        vp_mine.setOnPageChangeListener(this);
        //为所有标题按钮注册监听
        btn_mine_like.setOnClickListener((View.OnClickListener) this);
        btn_mine_collect.setOnClickListener((View.OnClickListener) this);
        btn_mine_production.setOnClickListener((View.OnClickListener) this);

        //fragment的集合，对应每个子页面  
        ArrayList<Fragment> fragments;
        fragments = new ArrayList<Fragment>();
        fragments.add(new MineLikeFragment());
        fragments.add(new MineCollectFragment());
        fragments.add(new MineProduntionFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);

        //将装载了数据的adapter设置给viewpager
        vp_mine.setAdapter(adapter);


        //先重置所有按钮颜色  
        resetButtonColor();
        //再将第一个按钮字体设置为红色，表示默认选中第一个  
        btn_mine_like.setTextColor(Color.parseColor("#d54f3b"));
    }


    //重置所有按钮的颜色  
    public void resetButtonColor() {
        btn_mine_like.setBackgroundColor(Color.parseColor("#ffffff"));
        btn_mine_collect.setBackgroundColor(Color.parseColor("#ffffff"));
        btn_mine_production.setBackgroundColor(Color.parseColor("#ffffff"));
        btn_mine_like.setTextColor(Color.parseColor("#bcbcbc"));
        btn_mine_collect.setTextColor(Color.parseColor("#bcbcbc"));
        btn_mine_production.setTextColor(Color.parseColor("#bcbcbc"));


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //实现三个接口方法，这里关键在于重写onPageSelected方法，onPageSelected会在每次滑动ViewPager的时
    // 候触发，所以所有滑动时的变化都可以在这里面定义，比如标题按钮的颜色随着滑动的变化等
    @Override
    public void onPageSelected(int arg0) {

        //widthArgs的实例化
        if (widthArgs == null) {
            widthArgs = new int[]{btn_mine_like.getWidth(),
                    btn_mine_collect.getWidth(),
                    btn_mine_production.getWidth(),
            };
        }
        //每次滑动首先重置所有按钮的颜色  
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为红色  
        btnArgs[arg0].setTextColor(Color.parseColor("#d54f3b"));
        //  cursorAnim(0);
        cursorAnim(arg0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //实现每个标题的onClick事件，点击跳转到相应页面
    @Override
    public void onClick(View whichbtn) {

        switch (whichbtn.getId()) {
            case R.id.btn_mine_like:
                vp_mine.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.btn_mine_collect:
                vp_mine.setCurrentItem(1);
                cursorAnim(1);
                break;
            case R.id.btn_mine_production:
                vp_mine.setCurrentItem(2);
                cursorAnim(2);
                break;

        }

    }

    //指示器的跳转，传入当前所处的页面的下标  
    private void cursorAnim(int curItem) {

        //每次调用，就将指示器的横坐标设置为0，即开始的位置  
        cursor_X = 0;
        //再根据当前的curItem来设置指示器的宽度  
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btn_cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字  
        lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
        btn_cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度  
        for (int i = 0; i < curItem; i++) {
            cursor_X = cursor_X + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置  
        btn_cursor.setX(cursor_X + btnArgs[curItem].getPaddingLeft());
    }

}
