package com.course_project;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.ParserConfigurationException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {
    RssFeed feed = null;
    String RSS_URL = "https://www.solidot.org/index.rss";
    // private RecyclerView recyclerView;
    @BindView(R.id.rv_test) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
              this.feed = new RssFeed_SAXParser().getFeed(RSS_URL);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        initView();
    }

    private void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter=new RecyclerAdapter(this.feed, this);
//        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getApplicationContext(),
//                        "click: " + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getApplicationContext(),
//                        "long click: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerView.setAdapter(adapter);
    }

}
