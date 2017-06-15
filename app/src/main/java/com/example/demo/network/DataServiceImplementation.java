package com.example.demo.network;

import com.example.demo.data.Post;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DataServiceImplementation implements DataService {

  private RestApiEndpoint apiEndpoint;

  public DataServiceImplementation() {
    apiEndpoint = ServiceGenerator.createService(RestApiEndpoint.class);
  }

  @Override public Observable<List<Post>> fetchPosts() {
    return apiEndpoint.getPosts().subscribeOn(Schedulers.io()).
        doOnError(new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            throwable.printStackTrace();
          }
        });
  }
}
