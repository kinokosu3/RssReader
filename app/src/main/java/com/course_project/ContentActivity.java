package com.course_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView Title;
    @BindView(R.id.content)
    TextView Content;
    @BindView(R.id.time_content)
    TextView Time_Content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        display();
    }

    public void display(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bun");
        String title = bundle.getString("title");
        String content = bundle.getString("content");
        String time = bundle.getString("time");
        Title.setText(title);
        Content.setText(content);
        Time_Content.setText(time);
    }

}
