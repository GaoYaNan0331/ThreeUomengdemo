package com.example.administrator.threeuomengdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

import java.util.Map;

import static android.content.Intent.ACTION_DELETE;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                //找到控件
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
        //为授权登录设置监听事件
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //友盟分享 上下文、授权方法、上下文、要显示的平台、监听事件
                UMShareAPI.get(MainActivity.this).doOauthVerify(MainActivity.this, SHARE_MEDIA.QQ.toSnsPlatform().mPlatform, umAuthListener);
            }
        });

    }
    // 配置 授权回调监听方法
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //获取 data 中的 字段值
            /*data 是 授权成功后，返回的数据资源对象，我们通过get方法获取其中的数据。
            如：获取QQ昵称
            String qq_Name = data.get("screen_name");
            注意：
            1. 该 Map集合中的字段都是固定的，所以我们需要照表查询。
            2. 如果 该 字段中含有 非法或奇异字符时，会出现 乱码现象，注意。
            */
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            switch (action) {
                case ACTION_AUTHORIZE://授权登录后执行的操作
                    /*
                    参数：
                    参数一：Context 上下文
                    参数二：int 常量值  SHARE_MEDIA.QQ
                    参数三：UMAuthListener  授权回调监听事件
                    */


                    UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                    //
                    break;
                case ACTION_DELETE://注销
                    break;
                case ACTION_GET_PROFILE://获取用户信息
                    //页面切换
                    Picasso.with(MainActivity.this).load(data.get("iconurl")).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(image);
                    text.setText(data.get("screen_name") + "(" + data.get("gender") + "," + data.get("city") + ")");
                    Log.i("----用户信息---", data.get("name") + ",性别;" + data.get("gender") + ",city" + data.get("city"));
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
//重写 OnActivityResult 页面销毁传值 回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //最后在分享所在的Activity里复写onActivityResult方法,注意不可在fragment中实现，
        // 如果在fragment中调用分享，在fragment依赖的Activity中实现，
        // 如果不实现onActivityResult方法，会导致分享或回调无法正常进行。
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
