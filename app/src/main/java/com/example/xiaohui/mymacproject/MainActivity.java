package com.example.xiaohui.mymacproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvResult =
                (TextView) findViewById(R.id.tv_hello);
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]Hello github ssh");
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://api.putao.so/sbiz/movie/list?citycode=%E6%B7%B1%E5%9C%B3");
                    InputStream inputStream = url
                            .openStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int hasRead = -1;
                    while ((hasRead = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, hasRead);
                    }
                    final String resultStr = baos.toString();
                    Log.d(TAG, "run() called with: " + baos.toString());
                    tvResult.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText(resultStr);
                                }
                            }
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
