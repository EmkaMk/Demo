package com.example.demo.recyclerView;

import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { MainPresenterProvider.class, RecyclerAdapterProvider.class})

public interface MainPresenterComponent {

  void inject(RecyclerViewFragment fragment);
}
