package com.alex.aldente;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class TimerFragment extends Fragment {

    private TextView timerTextView;
    private CustomTimer timer;
    private ImageView timerImage;



    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_TIME = "EXTRA_TIME";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";

    public TimerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String message = getArguments().getString(EXTRA_MESSAGE);
        int time = getArguments().getInt(EXTRA_TIME);
        int image = getArguments().getInt(EXTRA_IMAGE);

        View v = inflater.inflate(R.layout.timer_fragment_main, container, false);

        timerImage = (ImageView)v.findViewById(R.id.timer_image);

        timerImage.setImageResource(image);

        timerTextView = (TextView)v.findViewById(R.id.timer_text);

        timerTextView.setText(message);


        // custom Timer
        timer = new CustomTimer(time,100,v);

        // Reset on LongClick
        timerImage.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                timer = timer.reset();
                return true;
            }
        });

        // Start,Pause & Resume on Click
        timerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer.isRunning) {
                    Log.d("Timer", "Pause");
                    timer.pause();

                } else if (timer.isPaused) {
                    Log.d("Timer", "Resume");
                    timer.resume();
                    //timer.onTick(5000);

                } else {
                    timer.start();
                    //Make Image appear more transparent
                    timer.donutProgress.setUnfinishedStrokeColor(Color.parseColor("#AA7aff37"));
                }

            }

        });

        return v;
    }

    public static final TimerFragment newInstance(String message,int time, int image)

    {

        TimerFragment f = new TimerFragment();

        Bundle bdl = new Bundle(1);

        bdl.putString(EXTRA_MESSAGE, message);
        bdl.putInt(EXTRA_TIME,time);
        // TODO: Set default Image if image is not valid.
        bdl.putInt(EXTRA_IMAGE, image);

        f.setArguments(bdl);

        return f;

    }
}
