package com.example.arjun_mu.threadsample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_KEY = "MSG_KEY";
    ScrollView mScrollView;
    TextView mTextView;

    Handler myHandler;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = (ScrollView) findViewById(R.id.scrollView2);
        mTextView = (TextView) findViewById((R.id.textView));

        myHandler=new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String message = bundle.getString(MSG_KEY);
                mTextView.append(message);
            }
        };
    }

    public void runthread(View view) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handlerpost(View view) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Handler handler = new Handler();
        handler.post(runnable);

    }

    public void handlerwithpostdelayed(View view) {


        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                mTextView.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 5000);


    }

    public void threadinstance(View view) {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        Thread thread = new Thread(runnable);
        thread.start();

    }

    public void sendmsgtouifrombgthread(View view) {


        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(1000);
                        Message message=new Message();
                        Bundle bundle=new Bundle();
                        bundle.putString(MSG_KEY,"threadcomplete"+i);
                        message.setData(bundle);
                        myHandler.sendMessage(message);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }


        };

        Thread thread=new Thread(runnable);
        thread.start();


    }
}
