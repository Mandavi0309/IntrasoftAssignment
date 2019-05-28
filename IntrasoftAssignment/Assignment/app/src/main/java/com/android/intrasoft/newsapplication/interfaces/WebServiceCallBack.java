package com.android.intrasoft.newsapplication.interfaces;


import com.android.intrasoft.newsapplication.utills.ExceptionType;
import okhttp3.Headers;



public interface WebServiceCallBack
{

    void onResponse(WebServiceRequest requestType, String response, ExceptionType responseStatus,
        Headers headers);

}
