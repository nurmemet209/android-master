package com.cn.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cn.fragment.CartFrag;
import com.cn.fragment.ClassificationFrag;
import com.cn.fragment.FindFrag;
import com.cn.fragment.MainFragment;
import com.cn.fragment.MeFragment;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import lib.lhh.fiv.library.FrescoImageView;

/**
 * Created by nurmemet on 2015/12/17.
 */
public class MainPageAdapter extends FragmentPagerAdapter  implements AdvancedPagerSlidingTabStrip.ViewTabProvider{
    private Context mContext;
    public MainPageAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag= MainFragment.getInstance();
                break;
            case 1:
                frag= ClassificationFrag.getInstance();
                break;
            case 2:
                frag= FindFrag.getInstance();
                break;
            case 3:
                frag= CartFrag.getInstance();
                break;
            case 4:
                frag= MeFragment.getInstance();
                break;
            default:
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return 5;
    }






    @Override
    public View onSelectIconView(int position, View view, ViewGroup parent) {
        FrescoImageView draweeView;
        if(view == null){
            draweeView = new FrescoImageView(mContext);
            draweeView.setLayoutParams(new RelativeLayout.LayoutParams(UIHelper.dip2px(mContext,27),UIHelper.dip2px(mContext,27)));
            draweeView.setScaleType(ImageView.ScaleType.CENTER);
        }else{
            draweeView = (FrescoImageView)view;
        }

        switch (position){
            case  0:
                draweeView.loadView("",R.mipmap.home_selected);
                break;
            case  1:
                draweeView.loadView("",R.mipmap.classification_selected);
                break;
            case  2:
                draweeView.loadView("",R.mipmap.find_selected);
                break;
            case  3:
                draweeView.loadView("",R.mipmap.cart);
                break;
            case  4:
                draweeView.loadView("",R.mipmap.me_selected);
                break;
            default:
                break;
        }
        return draweeView;
    }

    @Override
    public View onIconView(int position, View view, ViewGroup parent) {
        FrescoImageView draweeView;

        if(view == null){
            draweeView = new FrescoImageView(mContext);
            draweeView.setLayoutParams(new RelativeLayout.LayoutParams(UIHelper.dip2px(mContext,27),UIHelper.dip2px(mContext,27)));
            draweeView.setScaleType(ImageView.ScaleType.CENTER);
        }else{
            draweeView = (FrescoImageView) view;
        }

        switch (position){
            case  0:
                draweeView.loadView("",R.mipmap.home_unselected);

                break;
            case  1:
                draweeView.loadView("",R.mipmap.classification);
                break;
            case  2:
               draweeView.loadView("",R.mipmap.find_unselected);
                break;
            case  3:
                draweeView.loadView("",R.mipmap.cart_unselected);
                break;
            case  4:
                draweeView.loadView("",R.mipmap.me_unselected);
                break;
            default:
                break;
        }
        return draweeView;
    }

    @Override
    public String getPageIconText(int position) {
        String str="";
        switch (position){
            case 0:
                str=mContext.getString(R.string.home);
                break;
            case 1:
                str=mContext.getString(R.string.specification);
                break;
            case 2:
                str=mContext.getString(R.string.find);
                break;
            case 3:
                str=mContext.getString(R.string.cart);
                break;
            case 4:
                str=mContext.getString(R.string.my_papa);
                break;
            default:
                break;


        }
        return str;
    }
}
