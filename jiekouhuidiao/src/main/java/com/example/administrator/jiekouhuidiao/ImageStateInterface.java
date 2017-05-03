package com.example.administrator.jiekouhuidiao;

import android.graphics.Bitmap;

/**
 * data:2017/4/18
 * author:高亚男(Administrator)
 * function:
 */

public interface ImageStateInterface {
    void onImageStart();

    void onImageSuccess(Bitmap bitmap);

    void onImageFailed();

    void OnEnd();
}
