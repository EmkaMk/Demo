package com.example.demo.network;

import com.example.demo.utils.AutoValueAdapterFactory;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

  public static final String API_BASE_URL = "http://jsonplaceholder.typicode.com/";
  private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
      new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create());


  private static Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
      .client(new OkHttpClient.Builder()
          .build())
      .addConverterFactory(gsonConverterFactory)
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build();

  public static <S> S createService(Class<S> serviceClass) {
    return retrofit.create(serviceClass);
  }


}
