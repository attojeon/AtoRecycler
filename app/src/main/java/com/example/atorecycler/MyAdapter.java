package com.example.atorecycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static List<NewsData> newsDataList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtTitle;
        public TextView txtContent;
        public TextView txtAuthor;
        public TextView txtDate;
        public SimpleDraweeView imgTitle;

        public MyViewHolder(View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txtTitle);
            txtContent = v.findViewById(R.id.txtContent);
            txtAuthor = v.findViewById(R.id.txtAuthor);
            txtDate = v.findViewById(R.id.txtDate);
            //imgTitle = v.findViewById(R.id.imgTitle);
            imgTitle = v.findViewById(R.id.imgTitle);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if( pos != RecyclerView.NO_POSITION) {
                        NewsData item = newsDataList.get(pos);
                        Log.d(">>> Clicked : ", item.title);

                        Context context = v.getContext();
                        Intent intent = new Intent(context, NewsDetailActivity.class);
                        intent.putExtra("news", (Serializable) item);
                        context.startActivity(intent);
                    }
                }
            });
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> news, Context context) {
        Log.d("ato", "MyViewHolder created");
        newsDataList = news;

        //
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.row_news, parent, false) ;
        MyAdapter.MyViewHolder vh = new MyAdapter.MyViewHolder(view) ;

        return vh ;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsData news = newsDataList.get(position);
        holder.txtTitle.setText(news.getTitle());
        holder.txtContent.setText(news.getContent());
        holder.txtAuthor.setText(news.getAuthor());
        holder.txtDate.setText(news.getPublishedAt());

        String imgurl = news.getImageUrl();
        if( imgurl != null && imgurl.length() > 5 ) {
            Uri uri = Uri.parse( news.getImageUrl());
            holder.imgTitle.setImageURI(uri);
        }
        else {
            Uri uri = Uri.parse("https://picsum.photos/536/354");
            holder.imgTitle.setImageURI( uri );
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsDataList == null ? 0 : newsDataList.size();
    }
}
