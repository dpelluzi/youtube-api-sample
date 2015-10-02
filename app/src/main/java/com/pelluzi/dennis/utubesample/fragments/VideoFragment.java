package com.pelluzi.dennis.utubesample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.pelluzi.dennis.utubesample.GoogleApiKey;
import com.pelluzi.dennis.utubesample.R;

/**
 * Created by dennis on 01/10/2015.
 */
public class VideoFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    public static final String VIDEO_ID = "Q888PBtrWc0";


    private YouTubePlayer mPlayer;
    private YouTubePlayer.PlaybackEventListener mPlaybackListener;

    public VideoFragment() {

    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mPlaybackListener = (YouTubePlayer.PlaybackEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PlaybackEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        YouTubePlayerSupportFragment fragment =
                (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.youtube_fragment);
        fragment.initialize(GoogleApiKey.DEBUG_KEY, this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("UTube",  "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("UTube", "onStop");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d("APP", "init success");
        mPlayer = youTubePlayer;
        mPlayer.setPlaybackEventListener(mPlaybackListener);
        if (!wasRestored) {
            mPlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Log.d("APP", "init failure");

        Toast.makeText(getActivity(), R.string.errorMessage, Toast.LENGTH_LONG).show();
    }

    public void pause() {
        if(mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void play() {
        if(mPlayer != null) {
            mPlayer.play();
        }
    }
}
