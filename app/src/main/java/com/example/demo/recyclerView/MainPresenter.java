package com.example.demo.recyclerView;

import com.example.demo.Presenter;
import com.example.demo.data.Post;
import com.example.demo.network.DaggerDataServiceComponent;
import com.example.demo.network.DataService;
import com.example.demo.network.DataServiceProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainPresenter extends Presenter<MainView> {

  @Inject DataService dataService;
  private List<Post> posts;

  public MainPresenter() {
    DaggerDataServiceComponent.builder()
        .dataServiceProvider(new DataServiceProvider())
        .build()
        .inject(this);
  }

  @Override protected void onTakeView() {
    super.onTakeView();
    addViewSubscription(loadPhotos());
    addViewSubscription(redirectToGraphs());
  }

  @Override protected void onDropView() {
    super.dropView();
  }

  private Subscription loadPhotos() {
  if(getView().getPhotoItems()==null)
  {
    return fetchPostsFromRemote();
  }
  return fetchPostsFromRemote();

  }

  private Subscription fetchPostsFromRemote(){
    return dataService.fetchPosts()
        .doOnError(new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            throwable.printStackTrace();
          }
        })
        .map(photos -> photos.subList(0, 100))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<Post>>() {
          @Override public void call(List<Post> postItems) {
            posts = postItems;
            getView().savePhotoItems(postItems);
            getView().setPhotoItems(postItems);
          }
        });
  }

  private Subscription redirectToGraphs() {
    return getView().onFloatButtonClick().subscribe(new Action1<Object>() {
      @Override public void call(Object o) {
        getView().redirectToGraphScreen(new ArrayList<>(posts));
      }
    });
  }
}
