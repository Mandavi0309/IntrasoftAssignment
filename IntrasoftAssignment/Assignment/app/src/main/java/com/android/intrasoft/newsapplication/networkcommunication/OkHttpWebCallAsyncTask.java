package com.android.intrasoft.newsapplication.networkcommunication;


import static com.android.intrasoft.newsapplication.utills.Constants.APPLICATION_JSON;
import static com.android.intrasoft.newsapplication.utills.Constants.CONTENT_TYPE;
import static com.android.intrasoft.newsapplication.utills.Constants.GET;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.android.intrasoft.newsapplication.commom.Generic;
import com.android.intrasoft.newsapplication.interfaces.WebServiceCallBack;
import com.android.intrasoft.newsapplication.interfaces.WebServiceRequest;
import com.android.intrasoft.newsapplication.utills.ExceptionType;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpWebCallAsyncTask extends AsyncTask<Void, Void, Void> {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");
  private static final String TAG = "OkHttpWebCallAsyncTask";
  private final Context context;
  private Object requestParams;
  private String URL;
  private WebServiceCallBack webServiceCallBack;
  private String responseString;
  private Headers respHeaders;
  private WebServiceRequest requestType;
  private ExceptionType responseStatus;
  private String requestMethod;
  private int apiHitCount = 1;

  private OkHttpClient client = new OkHttpClient.Builder()
      .connectTimeout(24, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build();


  OkHttpWebCallAsyncTask(Context context,
      WebServiceCallBack webServiceCallBack,
      String URL,
      WebServiceRequest requestType, String requestMethod,
      Object request) {

    this.context = context;
    this.webServiceCallBack = webServiceCallBack;
    this.URL = URL;
    Log.e("key:: URL", "" + URL);
    this.requestParams = request;
    this.requestType = requestType;
    this.requestMethod = requestMethod;

  }


  @Override
  protected Void doInBackground(Void... param) {
    if (requestParams != null) {
      Log.v(TAG, "params::" + requestParams.toString());
    }
    if (Generic.isNetworkAvailable(context)) {
      Request request = null;
      if (requestMethod.equals(GET)) {
        request = new Request.Builder()
            .url(URL)
            .addHeader(CONTENT_TYPE, APPLICATION_JSON)
            .build();

      }

      callAPI(request);
    } else {
      responseString = null;
      responseStatus = ExceptionType.NETWORK_FAILURE;
    }
    return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    Log.v(TAG, "responseString :: " + responseString);
    try {
      webServiceCallBack
          .onResponse(requestType, responseString, ExceptionType.CONNECTED, respHeaders);


    } catch (Exception e) {
      webServiceCallBack
          .onResponse(requestType, responseString, ExceptionType.SERVER_ERROR, respHeaders);
      e.printStackTrace();
    }

  }

  private void callAPI(Request request) {
    try {
      apiHitCount++;

      Response response = client.newCall(request).execute();
      responseString = response.body().string();
      respHeaders = response.headers();
      responseStatus = ExceptionType.CONNECTED;


    } catch (SocketTimeoutException e) {

      if (apiHitCount < 4) {
        Log.e(TAG, "socketTimeException" + apiHitCount);
        callAPI(request);
      } else {
        Log.e(TAG, "socketTimeException");
      }

    } catch (Exception e) {
      e.printStackTrace();
      responseStatus = ExceptionType.SERVER_ERROR;
    }
  }


}

