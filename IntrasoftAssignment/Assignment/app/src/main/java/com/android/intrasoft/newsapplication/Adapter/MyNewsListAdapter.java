package com.android.intrasoft.newsapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.intrasoft.newsapplication.activity.NewsDetailActivity;
import com.android.intrasoft.newsapplication.commom.Generic;
import com.android.intrasoft.newsapplication.model.NewsListModelClass.NewsListModelItem;
import com.android.intrasoft.newsapplication.utills.TimeAgo;
import com.example.ankitaga.newsapplication.R;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyNewsListAdapter extends RecyclerView.Adapter<MyNewsListAdapter.ViewHolder> {

  private final Context context;
  private ArrayList<NewsListModelItem> newsModelList;

  public MyNewsListAdapter(ArrayList<NewsListModelItem> newsModelList, Context context) {

    this.newsModelList = newsModelList;
    this.context = context;

  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View listItem = layoutInflater.inflate(R.layout.new_list, parent, false);
    ViewHolder viewHolder = new ViewHolder(listItem);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(MyNewsListAdapter.ViewHolder holder, int position) {
    final NewsListModelItem newsData = newsModelList.get(position);
    holder.tvTitle.setText(newsData.getTilte());
    holder.tvDesc.setText(newsData.getDescription());
    holder.tvSource.setText(newsData.getData().getName());

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    try {
      Date d = dateFormat.parse(newsData.getPublishedAt());
      long milliseconds = d.getTime();
      long duration = System.currentTimeMillis() - milliseconds;
      holder.tvTime.setText(TimeAgo.toDuration(duration));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Picasso.get().load(newsData.getUrlToImage())
        .fit().centerCrop().into(holder.ivNewsImage);
    holder.mainLayout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        if (Generic.isNetworkAvailable(context)) {
          Intent intent = new Intent(context, NewsDetailActivity.class);
          intent.putExtra("URL", newsData.getUrl());
          context.startActivity(intent);
        } else {
          Toast.makeText(context, "Check internet connection.", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return newsModelList.size();
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {

    final TextView tvTitle, tvDesc, tvSource, tvTime;
    final ImageView ivNewsImage;
    final View mView;
    final LinearLayout mainLayout;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      tvTitle = view.findViewById(R.id.tv_title);
      tvDesc = view.findViewById(R.id.tv_description);
      tvSource = view.findViewById(R.id.tv_source);
      tvTime = view.findViewById(R.id.time);
      ivNewsImage = view.findViewById(R.id.news_image);
      mainLayout = view.findViewById(R.id.main_rl);

    }
  }
}
