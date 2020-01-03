package com.example.atorecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class NewsDetailActivity extends AppCompatActivity {

    public TextView txtTitle;
    public TextView txtContent;
    public TextView txtAuthor;
    public TextView txtDate;
    public SimpleDraweeView imgTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();

        NewsData news = (NewsData)intent.getSerializableExtra("news");
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtDate = findViewById(R.id.txtDate);
        imgTitle = findViewById(R.id.imgTitle);

        txtTitle.setText(news.getTitle());
        txtContent.setText(news.getContent());
        txtAuthor.setText(news.getAuthor());
        txtDate.setText(news.getPublishedAt());

        String imgurl = news.getImageUrl();
        if( imgurl != null && imgurl.length() > 5 ) {
            Uri uri = Uri.parse( news.getImageUrl());
            imgTitle.setImageURI(uri);
        }
        else {
            Uri uri = Uri.parse("https://picsum.photos/536/354");
            imgTitle.setImageURI( uri );
        }

    }
}

