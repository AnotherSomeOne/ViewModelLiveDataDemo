package com.mengk.viewmodellivedata.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mengk.viewmodellivedata.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Desc 分享的dialog
 * Date 2019/10/19
 * author mengk
 */
public class BottomDialog extends Dialog {

    private TextView tvCancel;
    private Context context;
//    private ShareHandler shareHandler;

    public BottomDialog(@NonNull Context context) {
        super(context, R.style.common_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share_info);
        try {
//            setCanceledOnTouchOutside(true);
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.BOTTOM;
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(attributes);
            window.setBackgroundDrawableResource(R.color.transparent);
            window.setWindowAnimations(R.style.dialog_bottom);
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tvCancel = findViewById(R.id.tv_cancel_info_share);
//        shareHandler = new ShareHandler();
//        initEvent();
    }

    /**
     * 微信 QQ 微博等各大平台的分享回调监听
     * 该回调没有运行在ui线程
     */
//    private PlatformActionListener platformActionListener = new PlatformActionListener() {
//        @Override
//        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//            Message msg = Message.obtain();
//            msg.what = 1;
//            shareHandler.sendMessage(msg);
//        }
//
//        @Override
//        public void onError(Platform platform, int i, Throwable throwable) {
//            Message msg = Message.obtain();
//            msg.what = 2;
//            StringBuffer buffer = new StringBuffer(getPlatformName(platform.getName()));
//            if (!platform.isClientValid()) {
//                //客户端未安装
//                buffer.append("未安装");
//            } else if (!platform.isAuthValid()) {
//                //未认证
//                buffer.append("未授权");
//            } else {
//                buffer.append("授权出错");
//            }
//            msg.obj = buffer.toString();//throwable.getMessage();
//            shareHandler.sendMessage(msg);
//            LogUtils.INSTANCE.e("===z", "onError Throwable = " + throwable.getMessage());
//        }
//
//        @Override
//        public void onCancel(Platform platform, int i) {
//            Message msg = Message.obtain();
//            msg.what = 3;
//            shareHandler.sendMessage(msg);
//        }
//    };
//
//    /**
//     * 该handler用于主线程更新ui
//     */
//    private static class ShareHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            try {
//                switch (msg.what) {
//                    case 1://分享成功
//                        ToastUtils.showToast("分享成功");
//                        break;
//                    case 2://分享失败
//                        ToastUtils.showToast("分享失败," + msg.obj);
//                        break;
//                    case 3://分享取消
//                        break;
//                    default:
//                        ToastUtils.showToast("分享取消");
//                        break;
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void initEvent() {
//        adapter.setOnItemChildClickListener((adapter, view, position) -> {
//            if (view.getId() == R.id.ll_share_root_view) {
//                Object item = adapter.getItem(position);
//                if (item instanceof InfoShareBean) {
//                    int id = ((InfoShareBean) item).getId();
//                    switch (id) {
//                        case 1://微信好友分享
//                            shareToWeChat(Wechat.NAME);
//                            break;
//                        case 2://朋友圈分享
//                            shareToWeChat(WechatMoments.NAME);
//                            break;
//                        case 3://QQ好友分享
//                            shareToQQ(QQ.NAME);
//                            break;
//                        case 4://QQ空间分享
//                            shareToQQ(QZone.NAME);
//                            break;
//                        case 5://新浪微博分享
//                            shareToSinaWeiBo();
//                            break;
//                        default://测试shareSdk
//                            testShare();
//                            break;
//                    }
//                }
//            }
//        });
//        tvCancel.setOnClickListener(v -> InfoShareDialog.this.dismiss());
//    }
//
//    /**
//     * 分享到新浪微博
//     */
//    private void shareToSinaWeiBo() {
//        try {
//            //分享网络图片需要审核通过后才可以
//            String img = Environment.getExternalStorageDirectory().getAbsolutePath() + "/a.jpg";
//            String testImg = bean.getImageUrl();
//            AlertDialog dialog = new MaterialLoadingDialog.Builder(context).show("加载中");
//            dialog.show();
//
//            OnekeyShare oks = new OnekeyShare();
//            //关闭sso授权
//            oks.disableSSOWhenAuthorize();
//            //指定微博平台，如果不添加这行，则弹出9宫格供用户选择
//            oks.setPlatform(SinaWeibo.NAME);
//            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//            //分享网络图片，新浪分享网络图片，需要申请高级权限,否则会报10014的错误
//            //权限申请：新浪开放平台-你的应用中-接口管理-权限申请-微博高级写入接口-statuses/upload_url_text
//            //注意：本地图片和网络图片，同时设置时，只分享本地图片
//            oks.setImageUrl(testImg);
////        oks.setImagePath(img);
//            //是否直接分享（true则直接分享），false是有九格宫，true没有
//            oks.setSilent(false);
//            oks.setText(bean.getText() + bean.getUrl());
//            oks.setCallback(platformActionListener);
//
//            //执行分享
//            oks.show(context);
//            this.dismiss();
//            dialog.dismiss();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 分享到QQ 和 QQ空间
//     *
//     * @param platformName
//     */
//    private void shareToQQ(String platformName) {
//        Platform.ShareParams sp = new Platform.ShareParams();
//        sp.setTitle(bean.getTitle());
//        sp.setTitleUrl(bean.getTitleUrl()); // 标题的超链接
//        sp.setText(bean.getText());
//        sp.setImageUrl(bean.getImageUrl());
////        sp.setSite("发布分享的网站名称");
////        sp.setSiteUrl("https://www.baidu.com");
//        sp.setUrl(bean.getUrl());
//
//        Platform platform = ShareSDK.getPlatform(platformName);
//        platform.setPlatformActionListener(platformActionListener);
//
//        //执行分享
//        platform.share(sp);
//        this.dismiss();
//    }
//
//    /**
//     * 分享到微信或朋友圈
//     *
//     * @param platformName 平台
//     */
//    private void shareToWeChat(String platformName) {
//        String netImg = bean.getImageUrl();
//        Platform.ShareParams sp = new Platform.ShareParams();
//        sp.setShareType(Platform.SHARE_WEBPAGE);
//        sp.setTitle(bean.getTitle());
//        sp.setText(bean.getText());
//        sp.setImageUrl(netImg);
//        sp.setUrl(bean.getUrl());
//        Platform platform = ShareSDK.getPlatform(platformName);
////        platform.SSOSetting(false);
//        platform.setPlatformActionListener(platformActionListener);
//
//        //执行分享
//        platform.share(sp);
//        this.dismiss();
//    }
//
//    /**
//     * 测试shareSdk分享
//     */
//    private void testShare() {
//
//        OnekeyShare oks = new OnekeyShare();
//        // 关闭sso授权 oks.disableSSOWhenAuthorize();
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("分享"); // getString(R.string.share)
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        // 启动分享GUI
//        oks.show(context);
//
//    }
//
//    private List<InfoShareBean> getShareList() {
//        List<InfoShareBean> list = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//            InfoShareBean infoShareBean = new InfoShareBean();
//            infoShareBean.setId(i);
//            list.add(infoShareBean);
//        }
//        return list;
//    }
//
//    private String getPlatformName(String name) {
//        String appName;
//        if(Wechat.NAME.equals(name)||WechatMoments.NAME.equals(name)){
//            appName="微信";
//        }else if(QQ.NAME.equals(name)||QZone.NAME.equals(name)){
//            appName="QQ";
//        }else if(SinaWeibo.NAME.equals(name)){
//            appName="新浪微博";
//        }else{
//            appName=name;
//        }
//        return appName;
//    }
}
