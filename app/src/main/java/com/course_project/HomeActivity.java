package com.course_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.rv_test)
    RecyclerView recyclerView;
    RssFeed feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (getIntent().getSerializableExtra("data") != null){
            feed = (RssFeed) getIntent().getSerializableExtra("data");
            initView();}
            else {Log.e("data", "error");
        }
    }
    private void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter=new RecyclerAdapter(this.feed, this);
        recyclerView.setAdapter(adapter);
    }
}
