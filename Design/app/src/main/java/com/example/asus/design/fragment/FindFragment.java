package com.example.asus.design.fragment;

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
import com.example.asus.design.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/6/26.
 */
public class FindFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ViewPager myviewpager;
    //选项卡中的按钮  
    private Button btn_first;
    private Button btn_second;
    //作为指示标签的按钮  
    private ImageView cursor;
    //标志指示标签的横坐标  
    float cursorX = 0;

    /*首先创建两个数组，便于根据下标得到某个按钮以及对应的宽度,注意两个数组实例化的位置不同，
    btnArgs是像平常一样在onCreate方法中实例化，而widthArgs在滑动的时候再实例化，因为在onCreate方法
    中获取不了所有按钮的宽度，因为系统还未测量它们的宽度btnArgs的实例化*/
    //所有按钮的宽度的数组  
    private int[] widthArgs;
    //所有标题按钮的数组  
    private Button[] btnArgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    //初始化布局  
    public void initView(){
        myviewpager = (ViewPager)getActivity().findViewById(R.id.myviewpager);
        btn_first = (Button)getActivity().findViewById(R.id.btn_first);
        btn_second = (Button)getActivity().findViewById(R.id.btn_second);
        btnArgs = new Button[]{btn_first,btn_second};

        //初始化按钮数组  
        btnArgs = new Button[]{btn_first,btn_second};
        //指示标签设置为红色  
        cursor = (ImageView)getActivity().findViewById(R.id.cursor_btn);
        cursor.setBackgroundColor(Color.parseColor("#d54f3b"));

        //初始化指示器位置和大小
        btn_first.post(new Runnable(){
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();
                //减去边距*2，以对齐标题栏文字 
                lp.width = btn_first.getWidth()-btn_first.getPaddingLeft()*2;
                cursor.setLayoutParams(lp);
                cursor.setX(btn_first.getPaddingLeft());
            }
        });

        //为myviewpager注册监听
        myviewpager.setOnPageChangeListener(this);
        btn_first.setOnClickListener((View.OnClickListener) this);
        btn_second.setOnClickListener((View.OnClickListener) this);

        //fragment的集合，对应每个子页面  
        ArrayList<Fragment> fragments;
        fragments = new ArrayList<Fragment>();
        fragments.add(new CameraFragment());
        fragments.add(new DesignFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),fragments);

        //将装载了数据的adapter设置给viewpager
        myviewpager.setAdapter(adapter);
        //为所有标题按钮注册监听
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);

        //先重置所有按钮颜色  
        resetButtonColor();
        //再将第一个按钮字体设置为红色，表示默认选中第一个  
        btn_first.setTextColor(Color.parseColor("#d54f3b"));
        }


    //重置所有按钮的颜色  
    public void resetButtonColor(){
        btn_first.setBackgroundColor(Color.parseColor("#ffffff"));
        btn_second.setBackgroundColor(Color.parseColor("#ffffff"));
        btn_first.setTextColor(Color.parseColor("#bcbcbc"));
        btn_second.setTextColor(Color.parseColor("#bcbcbc"));
        }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //实现三个接口方法，这里关键在于重写onPageSelected方法，onPageSelected会在每次滑动ViewPager的时
    // 候触发，所以所有滑动时的变化都可以在这里面定义，比如标题按钮的颜色随着滑动的变化等
    @Override
    public void onPageSelected(int arg0) {

        //widthArgs的实例化
        if(widthArgs==null){
            widthArgs = new int[]{btn_first.getWidth(),
                    btn_second.getWidth(),
                    };
        }
        //每次滑动首先重置所有按钮的颜色  
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为红色  
        btnArgs[arg0].setTextColor(Color.parseColor("#d54f3b"));
        //cursorAnim(0);
        cursorAnim(arg0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //实现每个标题的onClick事件，点击跳转到相应页面
    @Override
    public void onClick(View whichbtn) {

        switch (whichbtn.getId()) {
            case R.id.btn_first:
                myviewpager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.btn_second:
                myviewpager.setCurrentItem(1);
                cursorAnim(1);
                break;

    }

}

    //指示器的跳转，传入当前所处的页面的下标  
    private void cursorAnim(int curItem) {

        //每次调用，就将指示器的横坐标设置为0，即开始的位置  
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度  
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字  
        lp.width = widthArgs[curItem]-btnArgs[0].getPaddingLeft()*2;
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度  
        for(int i=0; i<curItem; i++){
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置  
        cursor.setX(cursorX+btnArgs[curItem].getPaddingLeft());
    }
    }
