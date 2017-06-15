package com.example.demo.recyclerView;

public class PostItem {

  public int userId;
  public int id;
  public String title;

  public PostItem(int userId,int id,String title)
  {
    this.title=title;
    this.userId=userId;
    this.id=id;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getUserId() {

    return userId;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
}
