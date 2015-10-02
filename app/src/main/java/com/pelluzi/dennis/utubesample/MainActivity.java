package com.pelluzi.dennis.utubesample;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.youtube.player.YouTubePlayer;
import com.pelluzi.dennis.utubesample.adapters.ViewPagerAdapter;
import com.pelluzi.dennis.utubesample.fragments.LyricFragment;
import com.pelluzi.dennis.utubesample.fragments.VideoFragment;

/**
 * Created by dennis on 01/10/2015.
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        NavigationView.OnNavigationItemSelectedListener, LyricFragment.PlayerButtonListener,
        YouTubePlayer.PlaybackEventListener {

    private ViewPager mViewPager;
    private ViewPagerAdapter mPageAdapter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.addOnPageChangeListener(this);

        mPageAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPageAdapter);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // Initializing Drawer Layout and ActionBarToggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawers();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == ViewPagerAdapter.PAGE_VIDEO) {
            mToolbar.setTitle(R.string.menu_youtube_video);
        }
        else if (position == ViewPagerAdapter.PAGE_LYRIC) {
            mToolbar.setTitle(R.string.menu_lyrics);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // toggle check state
        if(menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);

        //Closing drawer on item click
        mDrawerLayout.closeDrawers();

        final int id = menuItem.getItemId();

        if(id == R.id.menu_youtube) {
            mViewPager.setCurrentItem(ViewPagerAdapter.PAGE_VIDEO);
        }
        else if(id == R.id.menu_lyrics) {
            mViewPager.setCurrentItem(ViewPagerAdapter.PAGE_LYRIC);
        }

        return true;
    }

    @Override
    public void onPlayClick() {
        VideoFragment fragment = (VideoFragment) mPageAdapter.getItem(ViewPagerAdapter.PAGE_VIDEO);
        fragment.play();
    }

    @Override
    public void onPauseClick() {
        VideoFragment fragment = (VideoFragment) mPageAdapter.getItem(ViewPagerAdapter.PAGE_VIDEO);
        fragment.pause();
    }

    @Override
    public void onPlaying() {
        LyricFragment fragment = (LyricFragment) mPageAdapter.getItem(ViewPagerAdapter.PAGE_LYRIC);
        fragment.setPlayPauseButtonState(LyricFragment.STATE_PLAYING);
    }

    @Override
    public void onPaused() {
        LyricFragment fragment = (LyricFragment) mPageAdapter.getItem(ViewPagerAdapter.PAGE_LYRIC);
        fragment.setPlayPauseButtonState(LyricFragment.STATE_PAUSED);
    }

    @Override
    public void onStopped() {
        LyricFragment fragment = (LyricFragment) mPageAdapter.getItem(ViewPagerAdapter.PAGE_LYRIC);
        fragment.setPlayPauseButtonState(LyricFragment.STATE_PAUSED);

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}
