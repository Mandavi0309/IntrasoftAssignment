package com.android.intrasoft.newsapplication.interfaces;

/**
 * Created by sagar on 19/6/17.
 */

public enum WebServiceRequest {

    NEWS(1);

    private int requestID;
    WebServiceRequest(int requestID ) {
        this.requestID = requestID;
    }

    public int getRequestID() {
        return requestID;
    }
}
