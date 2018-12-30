package com.course_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    RssFeed feed = null;

    public RecyclerAdapter(RssFeed feed,Context context){
        this.feed = feed;
        this.mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //把item的Layout转化成View传给ViewHolder
        View view = null;
        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler,parent,false);
            return new RecVH(view);}
        else {
            view = LayoutInflater.from(mContext).inflate(R.layout.header_recycler,parent,false);
            return new HeaderView(view);
        }

    }
    @Override
    public int getItemViewType(int position){
        if (position != feed.getItemCount()){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:  //不同的布局，做不同的事

                final RecVH holderOne = (RecVH) holder;
                holderOne.tvtitle.setText(feed.getItem(position).getTitle());
                holderOne.tvtime.setText(feed.getItem(position).getPubdate());
//                Log.e("title", feed.getItem(position).getTitle());
                holderOne.title = feed.getItem(position).getTitle();
                holderOne.content = feed.getItem(position).getDescription();
                holderOne.time = feed.getItem(position).getPubdate();
                break;
            case 1:
                final HeaderView holderTwo = (HeaderView) holder;
                holderTwo.tvbottom.setText("没有更多了哦~");
        }


    }

    @Override
    public int getItemCount() {
        //Log.e("origin_item: ", ""+feed.getItemCount());
        return feed.getItemCount()+1;
    }
    public class HeaderView extends RecyclerView.ViewHolder{
        @BindView(R.id.bottom_view)
        TextView tvbottom;
        public HeaderView (View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public class RecVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_test)
        TextView tvtitle;
        @BindView(R.id.time_view)
        TextView tvtime;
        public String title = "title";
        public String content = "content";
        public String time = "time";

        public RecVH (View view){
            super(view);
            ButterKnife.bind(this, view);

        }
        @OnClick(R.id.cv_test)
        void onclick(){
            Intent intent = new Intent(mContext, ContentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("content", content);
            bundle.putString("time", time);
            intent.putExtra("bun", bundle);
            mContext.startActivity(intent);
        }
    }


}
