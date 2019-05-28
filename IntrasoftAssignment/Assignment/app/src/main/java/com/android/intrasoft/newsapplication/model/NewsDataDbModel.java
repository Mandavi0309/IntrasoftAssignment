package com.android.intrasoft.newsapplication.model;

public class NewsDataDbModel {

  private int newsId;
  private String newsData;

  public NewsDataDbModel(String newsData) {
    this.newsData = newsData;
  }

  public int getNewsId() {
    return newsId;
  }

  public void setNewsId(int newsId) {
    this.newsId = newsId;
  }

  public String getNewsData() {
    return newsData;
  }

  public void setNewsData(String newsData) {
    this.newsData = newsData;
  }

  @Override
  public String toString() {
    return "NewsDataDbModel{" +
        "newsId=" + newsId +
        ", newsData='" + newsData + '\'' +
        '}';
  }
}
