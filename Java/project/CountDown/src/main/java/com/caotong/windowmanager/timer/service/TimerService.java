package com.caotong.windowmanager.timer.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.caotong.windowmanager.timer.manager.MyWindowManager;
import com.caotong.windowmanager.timer.view.TimerStandardView;

/**
 * Created by Administrator on 2017/5/22.
 */
public class TimerService extends Service{

    int timer = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            timer = intent.getIntExtra("timer_msg", 0);
        }
        if (timer == 3) {
            TimerStandardView.playClick = false;
            MyWindowManager.getInstance().createStandardTimerWindow(getApplicationContext());
        } else if (timer == 4) {
            TimerStandardView.playClick = true;
            MyWindowManager.getInstance().createStandardTimerWindow(getApplicationContext());
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
