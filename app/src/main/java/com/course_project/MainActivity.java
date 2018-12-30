package com.course_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    RssFeed feed = null;
    String RSS_URL = "https://www.solidot.org/index.rss";
    // private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        start_view();
    }
    public void start_view(){
        AsyncTask<String, String, String> StartView = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    MainActivity.this.feed = new RssFeed_SAXParser().getFeed(RSS_URL);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (MainActivity.this.feed != null){
                    return "done";
                }
                return "Nodone";
            }
            @Override
            protected void onPostExecute(String s){
                if (s.equals("done")){
                    Bundle bundle = new Bundle();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    bundle.putSerializable("data", MainActivity.this.feed);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                }
            }
        };
        StartView.execute();
    }

}
