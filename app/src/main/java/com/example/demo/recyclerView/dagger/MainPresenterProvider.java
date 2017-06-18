package com.example.demo.recyclerView.dagger;

import com.example.demo.recyclerView.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module public class MainPresenterProvider {
  @Provides
  MainPresenter getMainPresenter() {
    return new MainPresenter();
  }
}
