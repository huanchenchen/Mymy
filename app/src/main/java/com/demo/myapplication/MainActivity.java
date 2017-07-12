package com.demo.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.linear)
    LinearLayout linear;

    Timer timer;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:
                    image.setVisibility(View.GONE);
                    linear.setVisibility(View.VISIBLE);
                    timer.cancel();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(123);
                    }
                }, 3000, 3000);
            }
        };
        thread.start();
    }

}
