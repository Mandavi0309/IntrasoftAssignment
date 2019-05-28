package com.android.intrasoft.newsapplication;

import android.app.Application;
import net.sqlcipher.database.SQLiteDatabase;

public class NewsApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    SQLiteDatabase.loadLibs(this);
  }
}
