package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cn.adapter.BannerAdapter;
import com.cn.pppcar.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class MainFragment extends Fragment {

    private View mainView;
    @Bind(R.id.banner)
    protected AutoScrollViewPager banner;

    public static MainFragment getInstance() {
        MainFragment frag = new MainFragment();
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.main_frag, null);
        ButterKnife.bind(this, mainView);
        initBanner();
        return mainView;
    }

    private void initBanner(){

        ArrayList viewList=new ArrayList();

         ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));;
         DisplayImageOptions options=new DisplayImageOptions.Builder()
                 .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                 ///.showImageForEmptyUri(R.drawable.meinv) // 设置图片Uri为空或是错误的时候显示的图片
                 //.showImageOnFail(R.drawable.meinv) // 设置图片加载或解码过程中发生错误显示的图片
                 .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                 .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                 .build();;
        ArrayList<String> urlList=getList();
        for (int i=0;i<urlList.size();i++){
            View itemView=LayoutInflater.from(getActivity()).inflate(R.layout.main_frag_banner_item,null);
            ImageView img=(ImageView) itemView.findViewById(R.id.banner_img);
            imageLoader.displayImage(
                    urlList.get(i),
                    img, options);
            viewList.add(itemView);
        }

        BannerAdapter adapter=new BannerAdapter(getActivity(),viewList);
        banner.setAdapter(adapter);
        banner.setInterval(4000);
       // banner.setScrollDurationFactor(5);
        banner.setCycle(true);
        banner.setOffscreenPageLimit(urlList.size());
        banner.setBorderAnimation(false);
        banner.startAutoScroll();




    }
    private ArrayList<String> getList(){
        ArrayList<String> imageUrlList=new ArrayList<>();
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList
                .add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");

    return imageUrlList;
    }
}
