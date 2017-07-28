package com.example.asus.design.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.design.R;
import com.example.asus.design.SpacesItemDecoration;
import com.example.asus.design.activity.MainActivity;
import com.example.asus.design.activity.ProductionActivity;
import com.example.asus.design.activity.SearchActivity;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ASUS on 2017/6/26.
 */
public class IndexFragment extends Fragment {

    private View view;
    private MainActivity mainActivity;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置  
    private int oldPosition = 0;
    //存放图片的id  
    private int[] imageIds = new int[]{
            R.drawable.lunbo_1,
            R.drawable.lunbo_2,
            R.drawable.lunbo_3,
            R.drawable.lunbo_4,
            R.drawable.lunbo_5
    };
    //存放图片的标题  
    private String[] titles = new String[]{
            "不能只让我一个人馋成狗",
            "躲进幸福的小时光里",
            "简约的设计",
            "珍贵的童年时光",
            "LOMO摄影"
    };
    private TextView tv_picture_title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    RecyclerView recy;
    LinearLayout ll_index_search;

    private int mScrollY;
    private int mScrollX;
    private View headView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);

        //三个fragment都调用BaseActivity的方法，使状态栏变化
        if ( view.getContext() instanceof MainActivity)
        {
            mainActivity = (MainActivity) view.getContext();
            mainActivity.setStatusBar();
        }
        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recy = (RecyclerView) getActivity().findViewById(R.id.recy);
        initManager();
        mViewPaper = (ViewPager) headView.findViewById(R.id.vp_advertisement);

       ll_index_search = (LinearLayout) getActivity().findViewById(R.id.ll_index_search);
        ll_index_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        //显示的图片  
        images = new ArrayList<ImageView> ();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点  
        dots = new ArrayList<View>();
        dots.add(headView.findViewById(R.id.v_dot_1));
        dots.add(headView.findViewById(R.id.v_dot_2));
        dots.add(headView.findViewById(R.id.v_dot_3));
        dots.add(headView.findViewById(R.id.v_dot_4));
        dots.add(headView.findViewById(R.id.v_dot_5));
      tv_picture_title = (TextView)headView.findViewById(R.id.tv_picture_title);
        tv_picture_title.setText(titles[0]);
        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int  position) {
                tv_picture_title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int  arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    private void initManager() {
        MyAdapter myAdapter = new MyAdapter();
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//一行有2个item
        HeaderAndFooterWrapper wrapper=new HeaderAndFooterWrapper(myAdapter);
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_index_head, null);
       wrapper.addHeaderView(headView);
        recy.setLayoutManager(manager);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);//两个item中间的间距
        recy.setLayoutManager(manager);
        recy.setAdapter(wrapper);
        wrapper.notifyDataSetChanged();
    }

    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub  
            view.addView(images.get(position));
            return images.get(position);
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }
    }

    //利用线程池定时执行动画轮播 
     @Override
     public void onStart() {
         // TODO Auto-generated method stub  
         super.onStart();
         scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
         scheduledExecutorService.scheduleWithFixedDelay(
                 new ViewPageTask(),2,2,TimeUnit.SECONDS);
     }

     //图片轮播任务 
     private class ViewPageTask implements Runnable{
        public void run() {
             currentItem = (currentItem + 1) % imageIds.length;
             mHandler.sendEmptyMessage(0);
        }
     }

     //接收子线程传递过来的数据 
     private Handler mHandler = new Handler(){
         public void handleMessage(android.os.Message msg) {
             mViewPaper.setCurrentItem(currentItem);
         };
     };
     public void onStop() {
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    class  MyAdapter extends RecyclerView.Adapter
    {


        @Override
        public int getItemViewType(int position) {

            if (position%2==0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            int type = getItemViewType(viewType);
            switch (type)
            {
                case 0:
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.index_item_recy_one, parent, false);
                    MyHolder holder=new MyHolder(view);
                    return holder;
                case  1:
                    View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.index_item_recy_two, parent, false);
                    MyNewHolder myHolder=new MyNewHolder(view1);
                    return myHolder;
            }
            return null ;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 30;
        }

        class  MyHolder extends RecyclerView.ViewHolder
        {
            ImageView iv_index_image_one;
            LinearLayout ll_item_recy_one;
            TextView tv_index_title_one;
            public MyHolder(View itemView) {
                super(itemView);
                iv_index_image_one= (ImageView) itemView.findViewById(R.id.iv_index_image_one);
                tv_index_title_one = (TextView) itemView.findViewById(R.id.tv_index_title_one);
                ll_item_recy_one= (LinearLayout) itemView.findViewById(R.id.ll_item_recy_one);
                ll_item_recy_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ProductionActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }

        class  MyNewHolder extends RecyclerView.ViewHolder
        {
            ImageView iv_index_image_two;
            TextView tv_index_title_two;
            LinearLayout ll_item_recy_two;

            public MyNewHolder(View itemView) {
                super(itemView);
                iv_index_image_two= (ImageView) itemView.findViewById(R.id.iv_index_image_two);
                tv_index_title_two= (TextView) itemView.findViewById(R.id.tv_index_title_two);
                ll_item_recy_two= (LinearLayout) itemView.findViewById(R.id.ll_item_recy_two);
                ll_item_recy_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ProductionActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

}


