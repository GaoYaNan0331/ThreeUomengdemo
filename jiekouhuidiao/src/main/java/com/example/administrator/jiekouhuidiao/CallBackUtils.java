package com.example.administrator.jiekouhuidiao;/**
 * Created by apple on 16/8/30.
 */

/**
 * 作者：ban on 16/8/30 16:58
 */
public class CallBackUtils {

    private static CallBack mCallBack;

    public static void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public static void doCallBackMethod(){
        String info = "这里CallBackUtils即将发送的数据。";
        mCallBack.doSomeThing(info);
    }

}
