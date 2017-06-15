package com.example.demo.recyclerView;

import dagger.Module;
import dagger.Provides;

@Module public class MainPresenterProvider {
  @Provides MainPresenter getMainPresenter() {
    return new MainPresenter();
  }
}
