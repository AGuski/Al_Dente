package com.alex.aldente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AddTimerFragment extends Fragment {

    private TextView addTextView;


    public AddTimerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_fragment_main, container, false);

        addTextView = (TextView)v.findViewById(R.id.add_timer_text);

        addTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //AddTimer addTimer = (AddTimer) getActivity();
                //addTimer.addTimerFragment("NewTimer",666);

            }
        });

        return v;

    }



    public static final AddTimerFragment newInstance()

    {

        AddTimerFragment f = new AddTimerFragment();

        Bundle bdl = new Bundle(1);

        f.setArguments(bdl);

        return f;

    }
}
