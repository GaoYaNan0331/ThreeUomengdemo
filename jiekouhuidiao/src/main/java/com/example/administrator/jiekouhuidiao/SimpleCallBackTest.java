package com.example.administrator.jiekouhuidiao;/**
 * Created by apple on 16/8/30.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * 作者：ban on 16/8/30 16:58
 */
public class SimpleCallBackTest extends Activity implements CallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CallBackUtils.setCallBack(this);

        //1s后调用CallBackUtils的doCallBackMethod()方法。
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *  调用doCallBackMethod方法，目的是将SimpleCallBackTest注册到接口那里，
                 *  使接口知道，需要调用的是哪个类中的,实现该接口的方法。

                 *  调用CallBackUtils.doCallBackMethod()即调用了CallBack.doSomeThing(info)方法;
                 *  通过接口就可以把数据传输到这个类里的doSomeThing()方法里。
                 */
                //
                CallBackUtils.doCallBackMethod();
            }
        }, 1000);


    }

    @Override
    public void doSomeThing(String string) {
        Log.e("========", "拿到CallBackUtils的传递过来的数据=====" + string);
    }
}
