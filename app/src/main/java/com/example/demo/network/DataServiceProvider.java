package com.example.demo.network;

import dagger.Module;
import dagger.Provides;

@Module public class DataServiceProvider {
  @Provides DataService getDataService() {
    return new DataServiceImplementation();
  }
}
