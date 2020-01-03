package com.example.atorecycler;

import java.io.Serializable;

public class NewsData implements Serializable {
    public String title;
    public String imageUrl;
    public String content;
    public String author;
    public String publishedAt;

    public String getAuthor() {
        if( author.startsWith("null") )
            return "홍길동";
        return author;
    }

    public void setAuthor(String author) {
        if( author.startsWith("null") )
            this.author = "홍길동";
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
