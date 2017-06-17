package com.example.demo.recyclerView;

import com.example.demo.data.Post;
import java.util.List;
import rx.Observable;

public interface MainView {

    Observable<?> onFloatButtonClick();

    void setPhotoItems(List<Post> items);

    void redirectToGraphScreen();


}
