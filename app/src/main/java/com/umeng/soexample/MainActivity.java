package com.umeng.soexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.umeng.soexample.ui.TongPaoActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btn_time;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        countDownTime();
    }

    private void initView() {
        btn_time = (TextView) findViewById(R.id.btn_time);
        btn_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        disposable.dispose();
        startActivity(new Intent(MainActivity.this, TongPaoActivity.class));
        finish();
    }

    @SuppressLint("CheckResult")
    private void countDownTime() {
        disposable = Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long l = 5 - aLong;
                        btn_time.setText(l + " 跳转");
                        if (l == 0) {
                            startActivity(new Intent(MainActivity.this, TongPaoActivity.class));
                            finish();
                        }
                    }
                });
    }

}
