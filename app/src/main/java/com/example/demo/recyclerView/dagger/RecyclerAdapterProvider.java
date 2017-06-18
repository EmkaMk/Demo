package com.example.demo.recyclerView.dagger;

import com.example.demo.recyclerView.RecyclerAdapter;

import dagger.Module;
import dagger.Provides;

@Module public class RecyclerAdapterProvider {
  @Provides
  RecyclerAdapter getRecyclerAdapter() {
    return new RecyclerAdapter();
  }
}
