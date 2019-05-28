package com.android.intrasoft.newsapplication.utills;

import com.android.intrasoft.newsapplication.model.NewsListModelClass;
import com.google.gson.Gson;

public class Convert {


  public static NewsListModelClass newsListResponseConvert(String json) {

    Gson gsonObj = new Gson();
    return gsonObj.fromJson(json, NewsListModelClass.class);
  }

}
