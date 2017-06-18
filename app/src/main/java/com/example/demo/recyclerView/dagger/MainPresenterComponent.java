package com.example.demo.recyclerView.dagger;

import com.example.demo.recyclerView.RecyclerViewFragment;

import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { MainPresenterProvider.class, RecyclerAdapterProvider.class})

public interface MainPresenterComponent {

  void inject(RecyclerViewFragment fragment);
}
