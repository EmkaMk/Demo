package com.example.demo.recyclerView;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.jakewharton.rxbinding.view.RxView;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class RecyclerViewFragment extends Fragment implements MainView {

  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @Bind(R.id.graphButton) FloatingActionButton graphButton;
  @Inject RecyclerAdapter recyclerAdapter;
  @Inject MainPresenter mainPresenter;

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

  @Override public void redirectToGraphScreen() {
    LineGraphFragment fragment = new LineGraphFragment();
    //FragmentManager manager=getFragmentManager();
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.mainContent, fragment);
    transaction.commit();

  }
}
