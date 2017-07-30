package com.caotong.windowmanager.timer.bean;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.caotong.windowmanager.timer.R;
import com.caotong.windowmanager.timer.service.TimerService;

/**
 * Created by Administrator on 2017/4/25.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    private SharedPreferences mSharedPreferences;
    private final Handler mHandler = new Handler();
    private Toast mToast;
    public static boolean bStandardTimer = false;//防止创建多个windowmanager

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void putInt(String key, int value) {
        if (mInstance == null || mInstance.mSharedPreferences == null)
            return;
        mInstance.mSharedPreferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key, int defValue) {
        if (mInstance == null || mInstance.mSharedPreferences == null)
            return defValue;
        return mInstance.mSharedPreferences.getInt(key, defValue);
    }

    public void toast(final int resId) {
        if (mInstance == null)
            return;
        mInstance.mHandler.post(new Runnable() {

            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mInstance, resId, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(resId);
                }
                mToast.show();
            }
        });
    }

    public void startTimer(Context context) {
        try {
            if (!MyApplication.bStandardTimer) {
                Intent intent = new Intent(context, TimerService.class);
                intent.putExtra("timer_msg", 3);
                context.startService(intent);
                MyApplication.bStandardTimer = true;
            }
        } catch (Exception e) {
            toast(R.string.app_not_exists);
        }
    }
}
