package com.android.intrasoft.newsapplication.database;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


public class NewsDbHandler extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "DataManager";
  private static NewsDbHandler mInstance = null;
  private Context mContext;
  //Table
  public static final String NEWS_DATA_TABLE = "news_table";

  /**
   * News table column names
   */
  public static final String KEY_NEWS_ID = "news_id";
  public static final String KEY_NEWS_DATA = "news_data";


  public static NewsDbHandler getInstance(Context ctx) {
    if (mInstance == null) {
      mInstance = new NewsDbHandler(ctx.getApplicationContext());
    }
    return mInstance;
  }


  private NewsDbHandler(Context ctx) {
    super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    mContext = ctx;
  }

  /**
   * schema for Login data
   */
  public static String CREATE_NEWS_DATA_TABLE =
      "CREATE TABLE IF NOT EXISTS " + NEWS_DATA_TABLE + "("
          + KEY_NEWS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
          + KEY_NEWS_DATA + " TEXT )";

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_NEWS_DATA_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + NEWS_DATA_TABLE);
    onCreate(db);
  }
}
