package com.example.demo.recyclerView;

import dagger.Module;
import dagger.Provides;

@Module public class RecyclerAdapterProvider {
  @Provides RecyclerAdapter getRecyclerAdapter() {
    return new RecyclerAdapter();
  }
}
