package com.example.demo.recyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.demo.R;
import com.example.demo.data.Post;
import com.example.demo.graphs.LineGraphFragment;
import com.example.demo.recyclerView.dagger.DaggerMainPresenterComponent;
import com.example.demo.recyclerView.dagger.MainPresenterProvider;
import com.example.demo.recyclerView.dagger.RecyclerAdapterProvider;
import com.example.demo.utils.AutoValueAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import javax.inject.Inject;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RecyclerViewFragment extends Fragment implements MainView {

  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @Bind(R.id.graphButton) FloatingActionButton graphButton;
  @Inject RecyclerAdapter recyclerAdapter;
  @Inject MainPresenter mainPresenter;



  private Context context;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DaggerMainPresenterComponent.builder()
        .mainPresenterProvider(new MainPresenterProvider())
        .build()
        .inject(this);
    DaggerMainPresenterComponent.builder()
        .recyclerAdapterProvider(new RecyclerAdapterProvider())
        .build()
        .inject(this);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.main_view, container, false);
    ButterKnife.bind(this, rootView);
    mainPresenter.takeView(this);
    setupRecyclerView();
    addDividerToRecyclerView();
    return rootView;
  }

  private void setupRecyclerView() {
    recyclerView.setAdapter(recyclerAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
  }

  private void addDividerToRecyclerView() {
    DividerItemDecoration divider =
        new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
    divider.setDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.vertical_divider));
    recyclerView.addItemDecoration(divider);
  }

  @Override public void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
  }

  @Override public Observable<?> onFloatButtonClick() {
    return RxView.clicks(graphButton);
  }

  @Override public void setPhotoItems(List<Post> items) {
    recyclerAdapter.setPostItems(items);
  }

  @Override public void savePhotoItems(List<Post> items) {
    if (context != null) {
      SharedPreferences sharedPreferences =
          context.getSharedPreferences("photoItems", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      Gson gson = new Gson();
      Set<String> serializedItems = new HashSet<>();
      for (Post post : items) {
        String json = gson.toJson(post);
        serializedItems.add(json);
      }
      editor.putStringSet("photoItems", serializedItems);
      editor.commit();
    }
  }

  @Override public Set<Post> getPhotoItems() {
    SharedPreferences sharedPref = context.getSharedPreferences("photoItems", Context.MODE_PRIVATE);
    Set<String> items = sharedPref.getStringSet("photoItems", new HashSet<>());
    GsonBuilder builder=new GsonBuilder();
    Post.registerDeserializer(builder);
    Set<Post> photoItems = new HashSet<>();
    for (String item : items) {
     Post post = builder.create().fromJson(item, Post.class);
      photoItems.add(post);
    }

    return photoItems;
  }

  @Override public void redirectToGraphScreen(ArrayList<Post> posts) {
    LineGraphFragment fragment = new LineGraphFragment();
    Bundle args = new Bundle();
    args.putParcelableArrayList("posts", posts);
    fragment.setArguments(args);
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.mainContent, fragment);
    transaction.commit();
  }
}
