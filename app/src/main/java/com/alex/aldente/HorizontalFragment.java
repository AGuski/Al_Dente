package com.alex.aldente;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HorizontalFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public HorizontalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String message = getArguments().getString(EXTRA_MESSAGE);

        View v = inflater.inflate(R.layout.timer_fragment_main, container, false);

        TextView messageTextView = (TextView)v.findViewById(R.id.timer_text);

        messageTextView.setText(message);

        return v;

    }

    public static final HorizontalFragment newInstance(String message)

    {

        HorizontalFragment f = new HorizontalFragment();

        Bundle bdl = new Bundle(1);

        bdl.putString(EXTRA_MESSAGE, message);

        f.setArguments(bdl);

        return f;

    }
}
