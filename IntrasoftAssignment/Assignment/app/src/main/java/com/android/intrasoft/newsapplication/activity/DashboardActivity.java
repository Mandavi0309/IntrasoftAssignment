package com.android.intrasoft.newsapplication.activity;

import static com.android.intrasoft.newsapplication.utills.Constants.GET;
import static com.android.intrasoft.newsapplication.utills.Convert.newsListResponseConvert;
import static com.android.intrasoft.newsapplication.utills.DialogUtills.showToast;
import static com.android.intrasoft.newsapplication.utills.DialogUtills.stopProgressDialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import com.android.intrasoft.newsapplication.Adapter.MyNewsListAdapter;
import com.android.intrasoft.newsapplication.commom.Generic;
import com.android.intrasoft.newsapplication.database.NewsDataDAO;
import com.android.intrasoft.newsapplication.interfaces.WebServiceCallBack;
import com.android.intrasoft.newsapplication.interfaces.WebServiceRequest;
import com.android.intrasoft.newsapplication.model.NewsDataDbModel;
import com.android.intrasoft.newsapplication.model.NewsListModelClass;
import com.android.intrasoft.newsapplication.model.NewsListModelClass.NewsListModelItem;
import com.android.intrasoft.newsapplication.networkcommunication.APIManager;
import com.android.intrasoft.newsapplication.utills.DialogUtills;
import com.android.intrasoft.newsapplication.utills.ExceptionType;
import com.example.ankitaga.newsapplication.R;
import java.util.ArrayList;
import okhttp3.Headers;

public class DashboardActivity extends AppCompatActivity implements
    WebServiceCallBack {

  private MyNewsListAdapter myNewsListAdapter;
  private RecyclerView recyclerView;
  private ArrayList<NewsListModelItem> newsModelsList;
  private static final String TAG = DashboardActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setTitle(getString(R.string.todays_news));
    setContentView(R.layout.activity_dashboard);
    recyclerView = findViewById(R.id.news_list);
    if (Generic.isNetworkAvailable(this)) {
      new NewsDataDAO(DashboardActivity.this).deleteNewsData();
      loadNewsData();
    } else {
      NewsDataDbModel objNews = new NewsDataDAO(DashboardActivity.this).getNewsData();
      if (objNews != null && !TextUtils.isEmpty(objNews.getNewsData())) {
        newsModelsList = new ArrayList<>();
        NewsListModelClass newsListModel = newsListResponseConvert(objNews.getNewsData());
        Log.d(TAG, "Total news found: " + newsListModel.getTotalResults());
        if (newsListModel != null) {
          newsModelsList.addAll(newsListModel.getNewsListModelItem());
          if (newsModelsList.size() > 0) {
            setRecyclerDataToAdapter();
          }
        }
      } else {
        showToast(this, "No Internet connection.");
      }

    }


  }


  private void setRecyclerDataToAdapter() {
    myNewsListAdapter = new MyNewsListAdapter(newsModelsList, this);
    recyclerView.setAdapter(myNewsListAdapter);
  }


  private void loadNewsData() {
    DialogUtills.startProgressDialog(this, getResources().getString(R.string.please_wait));

    APIManager.getInstance().getNewsData(DashboardActivity.this, this,
        GET, null);
  }

  @Override
  public void onResponse(WebServiceRequest requestType, String response,
      ExceptionType responseStatus, Headers headers) {
    stopProgressDialog();
    switch (requestType) {
      case NEWS:
        if (response != null && responseStatus.equals(ExceptionType.CONNECTED)) {
          Log.d(TAG, response.toString());
          new NewsDataDAO(DashboardActivity.this).saveNewsData(new NewsDataDbModel(response));
          try {
            newsModelsList = new ArrayList<>();
            NewsListModelClass newsListModel = newsListResponseConvert(response);
            Log.d(TAG, "Total news found: " + newsListModel.getTotalResults());
            if (newsListModel != null && newsListModel.getStatus().equalsIgnoreCase("ok")) {
              newsModelsList.addAll(newsListModel.getNewsListModelItem());
              if (newsModelsList.size() > 0) {
                setRecyclerDataToAdapter();
              }
            } else {
              showToast(DashboardActivity.this, getString(R.string.error));
            }


          } catch (Exception e) {
            e.printStackTrace();
            showToast(this, getResources().getString(R.string.error));
          }
          break;
        }
    }
  }
}
