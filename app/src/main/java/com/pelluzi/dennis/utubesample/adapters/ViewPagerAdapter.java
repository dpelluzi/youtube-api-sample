package com.pelluzi.dennis.utubesample.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.pelluzi.dennis.utubesample.fragments.LyricFragment;
import com.pelluzi.dennis.utubesample.fragments.VideoFragment;

/**
 * Created by dennis on 01/10/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;

    public static final int PAGE_VIDEO = 0;
    public static final int PAGE_LYRIC = 1;

    private VideoFragment mVideoFragment;
    private LyricFragment mLyricFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case PAGE_VIDEO:
                if(mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                }
                fragment = mVideoFragment;
                break;

            case PAGE_LYRIC:
                if(mLyricFragment == null) {
                    mLyricFragment = new LyricFragment();
                }
                fragment = mLyricFragment;
                break;
            default:
                // this case newer should happens.
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
