package com.example.demo.network;

import com.example.demo.recyclerView.MainPresenter;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component
    (modules = DataServiceProvider.class)
public interface DataServiceComponent {

  void inject(MainPresenter presenter);
}
