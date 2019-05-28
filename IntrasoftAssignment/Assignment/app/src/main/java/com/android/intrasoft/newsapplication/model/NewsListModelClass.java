package com.android.intrasoft.newsapplication.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsListModelClass {

  @SerializedName("status")
  private String status;

  @SerializedName("totalResults")
  private String totalResults;


  @SerializedName("articles")
  private List<NewsListModelItem> newsListModelItem;

  public class NewsListModelItem {


    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String tilte;

    @SerializedName("description")
    private String description;

    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("url")
    private String url;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("content")
    private String content;


    @SerializedName("source")
    private Data data;

    public class Data {

      @SerializedName("name")
      private String name;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }
    }

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public String getTilte() {
      return tilte;
    }

    public void setTilte(String tilte) {
      this.tilte = tilte;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getUrlToImage() {
      return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
      this.urlToImage = urlToImage;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getPublishedAt() {
      return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
      this.publishedAt = publishedAt;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public Data getData() {
      return data;
    }

    public void setData(
        Data data) {
      this.data = data;
    }

  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(String totalResults) {
    this.totalResults = totalResults;
  }

  public List<NewsListModelItem> getNewsListModelItem() {
    return newsListModelItem;
  }

  public void setNewsListModelItem(
      List<NewsListModelItem> newsListModelItem) {
    this.newsListModelItem = newsListModelItem;
  }
}
