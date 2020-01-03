package com.example.atorecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getNews();
    }

    public void getNews() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=39dd7de4f675416f89e919483dfd0fcf";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                //Log.d(">>> Volley onResponse", response);

                try{
                    // 1.
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                    List<NewsData> newsDataList = new ArrayList<NewsData>();
                    for(int i=0; i < arrayArticles.length(); i++) {
                        JSONObject obj = arrayArticles.getJSONObject(i);
                        //Log.d(">>>News", obj.toString());
                        //{"source":{"id":null,"name":"Joins.com"},"author":"김성룡","title":"소방차 사이렌 소리내는 호주까치 등장, 최악 산불 영향일까? - 중앙일보 - 중앙일보","description":"호주 역사상 최악의 산불이 2달 동안 이어지고 있는 가운데, 호주까치가 소방차 사이렌 소리를 내는 영상이 SNS에 올라와 화제가 되고 있다. ABC시드니는 2일 페이스북을 통해 \"뉴이 지역에 거주하는 조지 앤드루스가 최근 경악할 만한 까치 소리를 공개했다\"며 앤드루스의 글과 영상을 소개했다. 앤드루스는 SNS에 이 영상 - 소방차,사이렌,소방차 사이렌,가운데 호주까치,사이렌 소리","url":"https:\/\/news.joins.com\/article\/23671724","urlToImage":"https:\/\/pds.joins.com\/news\/component\/htmlphoto_mmdata\/202001\/02\/99d2877d-6d3e-4cde-a2b4-0b0869289158.jpg","publishedAt":"2020-01-02T06:20:41Z","content":"2 , SNS .  . [ ]\r\nABC 2 \" \" . SNS \" . \" .   . [ ]\r\nABC \" , 'good morning, how are you?' , \", \" , \" .    (Australian magpie) . . , . , . , . , . .  30 . [AP=] \r\n1() . 17 , . [EPA=] \r\n.  BBC 1() 2 30 8 , 2 .  1 7 .  18 , .  200 , 11 900 ."}

                        NewsData data = new NewsData();
                        data.title = obj.getString("title");
                        data.imageUrl = obj.getString("urlToImage");
                        data.content = obj.getString("description");
                        data.author = obj.getString("author");
                        data.publishedAt = obj.getString("publishedAt");
                        //Log.d(">>> Image Url : ", data.imageUrl.toString());

                        newsDataList.add(data);
                    }

                    // 2.
                    MyAdapter mAdapter = new MyAdapter(newsDataList, MainActivity.this);
                    recyclerView.setAdapter(mAdapter);

                } catch(JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
