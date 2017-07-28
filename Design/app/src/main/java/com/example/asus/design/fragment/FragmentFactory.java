package com.example.asus.design.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by ASUS on 2017/6/26.
 */
public class FragmentFactory {

    public static Fragment getFragment(int position)
    {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
               return  fragment=new IndexFragment();
            case 1:
                return fragment=new FindFragment();
            case 2:
                return fragment=new MineFragment();
        }
        return fragment;
    }
}
