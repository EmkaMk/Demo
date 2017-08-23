package com.example.demo.recyclerView;

import com.example.demo.data.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import rx.Observable;

public interface MainView {

  Observable<?> onFloatButtonClick();

  void setItems(List<Post> items);

  void saveItems(List<Post> items);

  Set<Post> getItems();

  void redirectToGraphScreen(ArrayList<Post> posts);
}
