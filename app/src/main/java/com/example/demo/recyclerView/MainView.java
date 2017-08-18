package com.example.demo.recyclerView;

import com.example.demo.data.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import rx.Observable;

public interface MainView {

  Observable<?> onFloatButtonClick();

  void setPhotoItems(List<Post> items);

  void savePhotoItems(List<Post> items);

  Set<Post> getPhotoItems();

  void redirectToGraphScreen(ArrayList<Post> posts);
}
