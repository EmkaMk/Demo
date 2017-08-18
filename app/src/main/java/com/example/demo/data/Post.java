package com.example.demo.data;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import java.lang.reflect.Type;

@AutoValue public abstract class Post implements Parcelable {

  public static TypeAdapter<Post> typeAdapter(Gson gson) {
    return new AutoValue_Post.GsonTypeAdapter(gson);
  }

  public static void registerDeserializer(GsonBuilder builder) {
    builder.registerTypeAdapter(Post.class, getDeserializer());
  }

  private static JsonDeserializer<Post> getDeserializer() {
    return new JsonDeserializer<Post>() {
      @Override
      public Post deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
          throws JsonParseException {
        return context.deserialize(json, AutoValue_Post.class);
      }
    };
  }

  Post() {
  }

  public abstract int userId();

  public abstract int id();

  @NonNull public abstract String title();

  @NonNull public abstract String body();
}
