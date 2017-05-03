package com.example.administrator.jiekouhuidiao;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ImageStateInterface {
    private Button button;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 实例化控件
         */
        //实现方式一 (推荐)
        onLincoln();
        //实现方式二
        //onLincolnTwo();

    }

    /**
     * 实现方式一  这个需要  implements ImageStateInterface 接口
     */
    private void onLincoln() {
        button = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DownloadImageUtil downloadImageUtil = new DownloadImageUtil();
                /**
                 调用StartDownLoad方法，目的是将MainActivity注册到接口那里，
                 使接口知道，需要调用的是哪个类中的实现该接口的方法。
                 */
                downloadImageUtil.StartDownLoad(MainActivity.this,
                        getApplicationContext());
            }
        });

    }

    @Override
    public void onImageStart() {
        Log.d("lincoln", "onImageStart");
        button.setText("onImageStart");
        mTextView.setText("onImageStart");

    }

    @Override
    public void onImageSuccess(final Bitmap bitmap) {
        Log.d("lincoln", "onImageSuccess");
        runOnUiThread(new Runnable() {

            @SuppressLint("NewApi")
            @Override
            public void run() {
                button.setText("onImageSuccess");
                mTextView.setText("onImageSuccess");
                button.setBackground(new BitmapDrawable(bitmap));
            }
        });
    }

    @Override
    public void onImageFailed() {

    }

    @Override
    public void OnEnd() {
        Toast.makeText(MainActivity.this, "完成啦!!", Toast.LENGTH_SHORT).show();
    }
    /**---------------------实现方式一 结束------------------------*/


    /**
     * --------------------------实现方式二-----------------------------
     */

    private void onLincolnTwo() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DownloadImageUtil.StartDownLoad(imageInterface,
                        MainActivity.this);
            }
        });

    }

    ImageStateInterface imageInterface = new ImageStateInterface() {

        @Override
        public void onImageStart() {
            Log.d("lincoln", "onImageStart");
            button.setText("onImageStart");
        }

        @Override
        public void onImageSuccess(final Bitmap bitmap) {
            Log.d("lincoln", "onImageSuccess");
            runOnUiThread(new Runnable() {

                @SuppressLint("NewApi")
                @Override
                public void run() {
                    button.setText("onImageSuccess");

                    button.setBackground(new BitmapDrawable(bitmap));
                }
            });
        }

        @Override
        public void onImageFailed() {

        }

        @Override
        public void OnEnd() {
            Toast.makeText(MainActivity.this, "哈哈!!", Toast.LENGTH_SHORT).show();
        }
    };
    /**-----------------------------------------------------------------------------*/

}
