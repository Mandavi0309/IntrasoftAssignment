package com.android.intrasoft.newsapplication.database;

import android.content.ContentValues;
import android.content.Context;
import com.android.intrasoft.newsapplication.model.NewsDataDbModel;
import com.android.intrasoft.newsapplication.utills.Constants;
import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

public class NewsDataDAO {

  private final String TAG = NewsDataDAO.class.getSimpleName();
  private NewsDbHandler dbHandler;
  private SQLiteDatabase database;
  private Context mContext;


  public static String[] NEWS_DATA_TABLE_COLUMNS = {

      NewsDbHandler.KEY_NEWS_ID,
      NewsDbHandler.KEY_NEWS_DATA
  };


  public NewsDataDAO(Context mContext) {
    this.mContext = mContext;
    open();
  }

  public void open() throws SQLException {
    if (dbHandler == null) {
      dbHandler = NewsDbHandler.getInstance(mContext);
    }
  }

  public void close() {
    dbHandler.close();
  }


  /**
   * Method to save News data in db
   */
  public long saveNewsData(NewsDataDbModel objNews) {
    database = dbHandler.getWritableDatabase(Constants.DATABASE_KEY);
    long rowId = 0;
    try {

      ContentValues values = new ContentValues();
      values.put(NewsDbHandler.KEY_NEWS_DATA, objNews.getNewsData());

      // Inserting Row
      rowId = database.insert(NewsDbHandler.NEWS_DATA_TABLE, null, values);
    } catch (Exception e) {
      e.printStackTrace();

    }
    return rowId;
  }

  /**
   * Method to delete News Data
   */
  public void deleteNewsData() {

    database = dbHandler.getWritableDatabase(Constants.DATABASE_KEY);
    database.delete(NewsDbHandler.NEWS_DATA_TABLE, null, null);

  }

  /**
   * Method to get  News Data
   *
   * @return NewsDataDbModel
   */
  public NewsDataDbModel getNewsData() {
    Cursor cursor = null;
    NewsDataDbModel objNews = null;
    database = dbHandler.getReadableDatabase(Constants.DATABASE_KEY);
    try {
      cursor = database.query(NewsDbHandler.NEWS_DATA_TABLE,
          NEWS_DATA_TABLE_COLUMNS, null, null, null, null,
          null);

      if (cursor.moveToFirst()) {

        do {

          String newsData = cursor.getString(cursor.getColumnIndex(NewsDbHandler.KEY_NEWS_DATA));

          objNews = new NewsDataDbModel(newsData);


        } while (cursor.moveToNext());

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }

    return objNews;
  }

}
