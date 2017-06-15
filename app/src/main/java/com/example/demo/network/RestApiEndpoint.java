package com.example.demo.network;

import com.example.demo.data.Post;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface RestApiEndpoint {

  @GET("posts") Observable<List<Post>> getPosts();
}
