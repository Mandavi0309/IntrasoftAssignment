package com.android.intrasoft.newsapplication.networkcommunication;


import android.content.Context;
import com.android.intrasoft.newsapplication.interfaces.WebServiceCallBack;
import com.android.intrasoft.newsapplication.interfaces.WebServiceRequest;
import org.json.JSONObject;


public class APIManager {


  private static APIManager apiManager;

  private APIManager() {

  }

  public static APIManager getInstance() {
    if (apiManager == null) {
      apiManager = new APIManager();
    }
    return apiManager;
  }

  public void getNewsData(Context context, WebServiceCallBack webServiceCallBack,
      String requestType, JSONObject request) {
    new OkHttpWebCallAsyncTask(context, webServiceCallBack, WebServiceConstants.NEWS_URL,
        WebServiceRequest.NEWS, requestType, request).execute();

  }


}
