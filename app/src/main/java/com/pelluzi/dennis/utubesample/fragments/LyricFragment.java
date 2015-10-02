package com.pelluzi.dennis.utubesample.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pelluzi.dennis.utubesample.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dennis on 01/10/2015.
 */
public class LyricFragment extends Fragment implements View.OnClickListener {

    public static final int STATE_PAUSED  = 0;
    public static final int STATE_PLAYING = 1;

    public void setPlayPauseButtonState(int state) {
        if(mPlayPause != null) {

            int imgRes = state == STATE_PLAYING ? android.R.drawable.ic_media_pause
                    : android.R.drawable.ic_media_play;

            mPlayPause.setImageResource(imgRes);
            mPlayPause.setTag(state);
        }
    }

    public interface PlayerButtonListener {
        public void onPlayClick();
        public void onPauseClick();
    }

    private TextView mLyrics;
    private ImageButton mPlayPause;
    private PlayerButtonListener mListener;

    public LyricFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lyrics, container, false);

        mLyrics = (TextView) view.findViewById(R.id.txt_lyrics);
        mLyrics.setMovementMethod(new ScrollingMovementMethod());

        loadLyrics();

        mPlayPause = (ImageButton) view.findViewById(R.id.btn_play_pause);
        mPlayPause.setOnClickListener(this);
        mPlayPause.setTag(STATE_PAUSED);

        return view;
    }

    private void loadLyrics() {
        try {
            StringBuilder sb = new StringBuilder();
            InputStream lyrics = getContext().getAssets().open("weird_fishes.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(lyrics));
            String str;

            while ((str=in.readLine()) != null) {
                sb.append(str);
            }

            in.close();

            mLyrics.setText(sb);

        } catch (IOException e) {
            Log.e("APP", "error read file");
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mListener = (PlayerButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PlayerButtonListener");
        }
    }

    @Override
    public void onClick(View view) {
        int state = (int) view.getTag();

        if(state == STATE_PAUSED) {
            // start to play
            mListener.onPlayClick();
            setPlayPauseButtonState(STATE_PLAYING);
        }
        else {
            // pause
           mListener.onPauseClick();
           setPlayPauseButtonState(STATE_PAUSED);
        }

    }
}
