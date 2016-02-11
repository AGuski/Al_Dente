package com.alex.aldente;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

/**
 * Created by alex on 17.09.15.
 */
public class CustomTimer extends AltCountDownTimer {

    public boolean isRunning;
    public boolean isPaused;
    public DonutProgress donutProgress;
    private TextView timerTextView;
    long onePercent;
    long millisUntilFinished;
    long countDownInterval;
    int timeInSecs;
    View v;


    /**
     * @param timeInSecs        The number of seconds in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CustomTimer(int timeInSecs, long countDownInterval, View v) {
        super((long) timeInSecs*1000, countDownInterval);
        this.timeInSecs = timeInSecs;
        this.isRunning = false;
        this.isPaused = false;
        this.donutProgress = (DonutProgress) v.findViewById(R.id.donut_progress);
        timerTextView = (TextView) v.findViewById(R.id.timer_text);
        donutProgress.setRotation(-90);
        onePercent = timeInSecs*1000/100;
        this.v = v;
        this.millisUntilFinished = timeInSecs*1000;
        this.countDownInterval = countDownInterval;

    }


    @Override
    public void onTick(long millisUntilFinished) {

        this.millisUntilFinished = millisUntilFinished;
        this.isPaused = false;
        this.isRunning =true;
        donutProgress.setProgress(100 - (int ) (millisUntilFinished/onePercent) );

        String seconds = "" + (millisUntilFinished / 1000) % 60;
        String minutes = "" + millisUntilFinished / 60000;
        if (seconds.length() < 2) seconds = "0" + seconds;
        if (minutes.length() < 2) minutes = "0" + minutes;

        timerTextView.setText(minutes + ":" + seconds);

    }

    @Override
    public void onFinish() {

        Vibrator mVibrator = (Vibrator) v.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(1000);
        isRunning=false;
        donutProgress.setProgress(100);
        timerTextView.setText(R.string.timer_text_done);

    }

    public void pause() {
        this.cancel();
        isPaused=true;
        isRunning=false;

    }

    public void resume() {
        if (this.isPaused){
            // TODO: Resume the Donut Progress
            // TODO: Resume with correct Milliseconds
            Log.d("Millis", this.millisUntilFinished + "");
            this.start();
            Log.d("Millis", this.millisUntilFinished + "");
            this.onTick(6000);

        }

    }

    public CustomTimer reset() {
        this.cancel();
        CustomTimer newTimer = new CustomTimer(this.timeInSecs,this.countDownInterval,this.v);
        newTimer.donutProgress.setProgress(0);
        newTimer.timerTextView.setText("start!");
        // Make donut color transparent.
        this.donutProgress.setUnfinishedStrokeColor(Color.parseColor("#007aff37"));
        return newTimer;
    }
}
