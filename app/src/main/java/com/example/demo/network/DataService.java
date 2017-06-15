package com.example.demo.network;

import com.example.demo.data.Post;
import java.util.List;
import rx.Observable;

public interface DataService {

  Observable<List<Post>> fetchPosts();
}
