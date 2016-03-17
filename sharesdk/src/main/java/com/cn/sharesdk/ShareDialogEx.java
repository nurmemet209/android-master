package com.cn.sharesdk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;



import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by erqal on 2015/10/19.
 */
public class ShareDialogEx extends Dialog implements PlatformActionListener {

    private final int CANCEL_SHARE = 100;

    private final int WECHATE_MOMENTS = 101;
    private final int WECHATE_FAVRITES = 102;
    private final int WECHATE_FRIEND = 103;
    private final int QQ_FRIEND = 104;
    private final int QQ_ZONE = 105;
    private final int SINA_BLOG = 106;
    private final int SHARE_ERROR = 107;


    private TextView wechatFriend;
    private TextView wechatTimeLine;
    private TextView wechatFavrite;
    private TextView qqFriend;
    private TextView qqZone;
    private TextView sinaBlog;
    private TextView cancle;
    private View rootView;

    public ShareDialogEx(Context context) {
        super(context, R.style.share_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog_ex);
        //ShareSDK.initSDK(getContext());

        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width=WindowManager.LayoutParams.FILL_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);
        findViews();
        setListener();
    }

    @Override
    public void show() {
        super.show();
        Animation anim=AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_from_bottom_);
        rootView.setAnimation(anim);
        anim.start();

        rootView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        Animation anim=AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_out_to_bottom_);
        rootView.clearAnimation();
        rootView.setAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ShareDialogEx.super.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        anim.start();

    }

    private void findViews() {
        rootView=findViewById(R.id.root_view);
        wechatFriend = (TextView) findViewById(R.id.wechat_friend);
        wechatTimeLine = (TextView) findViewById(R.id.wechat_timeline);
        wechatFavrite = (TextView) findViewById(R.id.wechat_favrite);
        qqFriend = (TextView) findViewById(R.id.qq_friend);
        qqZone = (TextView) findViewById(R.id.qq_zone);
        sinaBlog = (TextView) findViewById(R.id.sina_blog);
        cancle = (TextView) findViewById(R.id.share_cancel);
    }

    private void setListener() {
        wechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //2、设置分享内容
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
                sp.setTitle("我是分享标题");  //分享标题
                sp.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");   //分享文本
                sp.setImageUrl("http://ermaarip.blogbas.com/uploadfile/2015/1019/20151019122108173.jpg");//网络图片rul
                sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

                //3、非常重要：获取平台对象
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(ShareDialogEx.this); // 设置分享事件回调
                // 执行分享
                wechat.share(sp);
            }
        });
        wechatTimeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                sp.setTitle("我是分享标题");  //分享标题
                sp.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");   //分享文本
                sp.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
                sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

                //3、非常重要：获取平台对象
                Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                wechatMoments.setPlatformActionListener(ShareDialogEx.this); // 设置分享事件回调
                // 执行分享
                wechatMoments.share(sp);
            }
        });
        wechatFavrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                sp.setTitle("我是分享标题");  //分享标题
                sp.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");   //分享文本
                sp.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
                sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

                //3、非常重要：获取平台对象
                Platform wechatMoments = ShareSDK.getPlatform(WechatFavorite.NAME);
                wechatMoments.setPlatformActionListener(ShareDialogEx.this); // 设置分享事件回调
                // 执行分享
                wechatMoments.share(sp);
            }
        });
        qqFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setTitle("我是分享标题");
                sp.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");
                sp.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
                sp.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情
                //3、非常重要：获取平台对象
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(ShareDialogEx.this); // 设置分享事件回调
                // 执行分享
                qq.share(sp);
            }
        });
        qqZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setTitle("测试分享的标题");
//                sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
//                sp.setText("测试分享的文本");
//                sp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
//                sp.setSite("发布分享的网站名称");
//                sp.setSiteUrl("发布分享网站的地址");

                Platform qzone = ShareSDK.getPlatform(QZone.NAME);
                qzone.setPlatformActionListener(ShareDialogEx.this); // 设置分享事件回调
// 执行图文分享
                qzone.share(sp);
            }
        });
        sinaBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2、设置分享内容
                Platform.ShareParams sp = new Platform.ShareParams();
                sp.setText("我是分享文本，啦啦啦~http://uestcbmi.com/"); //分享文本
                sp.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul

                //3、非常重要：获取平台对象
                Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                sinaWeibo.setPlatformActionListener(ShareDialogEx.this); // 设置分享事件回调
                // 执行分享
                sinaWeibo.share(sp);
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    public void onCancel(Platform arg0, int arg1) {//回调的地方是子线程，进行UI操作要用handle处理
        handler.sendEmptyMessage(CANCEL_SHARE);

    }

    @Override
    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        if (arg0.getName().equals(WechatMoments.NAME)) {
            handler.sendEmptyMessage(WECHATE_MOMENTS);
        } else if (arg0.getName().equals(Wechat.NAME)) {
            handler.sendEmptyMessage(WECHATE_FRIEND);
        } else if (arg0.getName().equals(WechatFavorite.NAME)) {
            handler.sendEmptyMessage(WECHATE_FAVRITES);
        } else if (arg0.getName().equals(SinaWeibo.NAME)) {// 判断成功的平台是不是新浪微博
            handler.sendEmptyMessage(SINA_BLOG);
        } else if (arg0.getName().equals(QQ.NAME)) {
            handler.sendEmptyMessage(QQ_FRIEND);
        } else if (arg0.getName().equals(QZone.NAME)) {
            handler.sendEmptyMessage(QQ_ZONE);
        }

    }

    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        arg2.printStackTrace();
        Message msg = new Message();
        msg.what = SHARE_ERROR;
        msg.obj = arg2.getMessage();
        handler.sendMessage(msg);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SINA_BLOG:
                    Toast.makeText(getContext(), R.string.sina_success, Toast.LENGTH_LONG).show();
                    break;

                case WECHATE_FRIEND:
                    Toast.makeText(getContext(), R.string.wechat_friend_success, Toast.LENGTH_LONG).show();
                    break;
                case WECHATE_MOMENTS:
                    Toast.makeText(getContext(), R.string.wechat_moment_success, Toast.LENGTH_LONG).show();
                    break;
                case WECHATE_FAVRITES:
                    Toast.makeText(getContext(), R.string.wechat_favrite_success, Toast.LENGTH_LONG).show();
                    break;
                case QQ_FRIEND:
                    Toast.makeText(getContext(), R.string.qq_friend_success, Toast.LENGTH_LONG).show();
                    break;
                case QQ_ZONE:
                    Toast.makeText(getContext(), R.string.qq_zone_success, Toast.LENGTH_LONG).show();
                    break;

                case CANCEL_SHARE:
                    Toast.makeText(getContext(), R.string.share_cancel, Toast.LENGTH_LONG).show();
                    break;
                case SHARE_ERROR:
                    Toast.makeText(getContext(), "分享失败啊" + msg.obj, Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }

    };


}
