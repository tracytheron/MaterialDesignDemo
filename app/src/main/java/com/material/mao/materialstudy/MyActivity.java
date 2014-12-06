package com.material.mao.materialstudy;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.material.mao.materialstudy.adapter.MyAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyActivity extends ActionBarActivity {
    @InjectView(R.id.my_recycler_view)
    RecyclerView mMyRecyclerView;
    @InjectView(R.id.left_drawer)
    ListView mLeftDrawer;
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mPlanetTitles;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        initMainContent();
        initDrawerLayout();

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Drawable myDrawable = getResources().getDrawable(R.color.primary);
        getSupportActionBar().setBackgroundDrawable(myDrawable);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    private void initMainContent(){
        mMyRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mMyRecyclerView.setLayoutManager(mLayoutManager);

        String[] myDataset = new String[]{"房师兄", "58同城", "微信", "米聊", "微博", "Google+", "陌陌", "Notepad+", "微米", "电话", "淘宝", "Dropbox+", "Facebook", "百度云"};
        mAdapter = new MyAdapter(myDataset);
        mMyRecyclerView.setAdapter(mAdapter);
    }
    private void initDrawerLayout(){
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.abc_action_bar_home_description,
                R.string.abc_action_bar_up_description){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("菜单");
            }

        };

        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mLeftDrawer.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,R.id.tv_action,mPlanetTitles));


        //mLeftDrawer.setOnItemClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
