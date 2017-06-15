package com.example.demo.data;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue public abstract class Post implements Parcelable {

  public static TypeAdapter<Post> typeAdapter(Gson gson) {
    return new AutoValue_Post.GsonTypeAdapter(gson);
  }

   Post(){}

   public abstract int userId();

   public abstract int id();

  @NonNull public abstract String title();

  @NonNull public abstract String body();

}
