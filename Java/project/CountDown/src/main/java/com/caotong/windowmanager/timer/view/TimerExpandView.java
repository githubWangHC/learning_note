package com.caotong.windowmanager.timer.view;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.caotong.windowmanager.timer.R;
import com.caotong.windowmanager.timer.bean.MyApplication;
import com.caotong.windowmanager.timer.home.HomeWatcher;
import com.caotong.windowmanager.timer.home.OnHomePressedListener;
import com.caotong.windowmanager.timer.manager.MyWindowManager;
import com.caotong.windowmanager.timer.service.TimerService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caotong on 2017/5/22
 */
public class TimerExpandView extends RelativeLayout {

    private Context mContext;
    private LinearLayout expandLayout;
    private TextView expandTextView;

    private int expandMinute = 0;
    private int expandSecond = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private HomeWatcher homeWatcher;
    private AlphaAnimation alphaAnimation;
    private ObjectAnimator objectAnimator;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            timeProcess();
        }
    };

    public TimerExpandView(Context context) {
        super(context);
        mContext = context;
        initViews(context);
    }

    public TimerExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews(context);
    }

    public TimerExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.timer_expand_layout, this);
        homeWatcher = new HomeWatcher(context);
        registerResourceReceiver();
        expandLayout = (LinearLayout) findViewById(R.id.timer_expand_layout);
        expandTextView = (TextView) findViewById(R.id.timer_expand_minute_textview);
        expandMinute = MyApplication.getInstance().getInt("standard_minute", 0);
        expandSecond = MyApplication.getInstance().getInt("standard_second", 0);
        startTimer();
        expandLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    timerTask.cancel();
                    timer.cancel();
                } catch (Exception e) {

                }
                timerExpand();
            }
        });

        homeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                timerExpand();
            }

            @Override
            public void onHomeLongPressed() {
            }
        });
        homeWatcher.startWatch();
    }

    /**
     * 开始倒计时
     */
    public void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.obtainMessage().sendToTarget();
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void timeProcess() {
        if (expandMinute == 0) {
            if (expandSecond == 0) {
                expandTextView.clearAnimation();//结束动画
                objectAnimator.cancel();

                expandTextView.setText(getResources().getString(R.string.timer_end));
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                if (timerTask != null) {
                    timerTask = null;
                }
            } else {
                expandSecond--;
                if (expandSecond >= 10) {
                    expandTextView.setText("0" + expandMinute + " : " + expandSecond);
                } else {
                    alphaAnimation = new AlphaAnimation(0f, 1.0f);
                    alphaAnimation.setDuration(900);
                    alphaAnimation.setRepeatCount(Animation.INFINITE);
                    expandTextView.setAnimation(alphaAnimation);
                    alphaAnimation.start();

                    objectAnimator = shakeXY(expandTextView, 1f);
//                    objectAnimator.setRepeatCount(Animation.INFINITE);
                    objectAnimator.start();

                    expandTextView.setTextColor(Color.RED);

                    expandTextView.setText("0" + expandMinute + " : 0" + expandSecond);
                }
            }
        } else {
            if (expandSecond == 0) {
                expandSecond = 59;
                expandMinute--;
                if (expandMinute >= 10) {
                    expandTextView.setText(expandMinute + " : " + expandSecond);
                } else {
                    expandTextView.setText("0" + expandMinute + " : " + expandSecond);
                }
            } else {
                expandSecond--;
                if (expandSecond >= 10) {
                    if (expandMinute >= 10) {
                        expandTextView.setText(expandMinute + " : " + expandSecond);
                    } else {
                        expandTextView.setText("0" + expandMinute + " : " + expandSecond);
                    }
                } else {
                    if (expandMinute >= 10) {
                        expandTextView.setText(expandMinute + " : 0" + expandSecond);
                    } else {
                        expandTextView.setText("0" + expandMinute + " : 0" + expandSecond);
                    }
                }
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            timerExpand();
        }
        return super.dispatchKeyEvent(event);
    }

    private void registerResourceReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.skyworth.signal.switch");
        mContext.registerReceiver(resourceReceiver, filter);
    }

    /**
     * 外接串口
     */
    public BroadcastReceiver resourceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("register:", "register");
            String action = intent.getAction();
            Log.i("register:", action);
            if (action.equals("com.skyworth.signal.switch")) {
                timerExpand();
            }
        }
    };

    /**
     * 切至正常倒计时
     */
    private void timerExpand() {
        Intent intent = new Intent(mContext, TimerService.class);
        if (expandMinute == 0 && expandSecond == 0) {
            MyWindowManager.getInstance().removeExpandWindow(mContext);
            intent.putExtra("timer_msg", 3);
            mContext.startService(intent);
            MyApplication.bStandardTimer = false;
        } else {
            MyWindowManager.getInstance().removeExpandWindow(mContext);
            MyApplication.getInstance().putInt("standard_minute", expandMinute);
            MyApplication.getInstance().putInt("standard_second", expandSecond);
            intent.putExtra("timer_msg", 4);
            mContext.startService(intent);
        }
        homeWatcher.stopWatch();
        mContext.unregisterReceiver(resourceReceiver);
    }

    public ObjectAnimator shakeXY(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(1000);
    }
}