package com.example.demo;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.demo.data.Post;
import com.example.demo.navDrawer.NavigationDrawerAdapter;
import com.example.demo.navDrawer.NavigationDrawerView;
import com.example.demo.recyclerView.RecyclerViewFragment;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
  @Bind(R.id.navList) NavigationDrawerView navigationDrawerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setupNavigationDrawer();
    navigationDrawerView.setDrawerLayout(drawerLayout);
    handleDrawerClicks();
  }

  private void setupNavigationDrawer() {
    navigationDrawerView.setAdapter(new NavigationDrawerAdapter(this));
  }

  private void handleDrawerClicks() {
    navigationDrawerView.getClicks().subscribe(new Action1<String>() {
      @Override public void call(String s) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContent, fragment);
        transaction.commit();
        navigationDrawerView.closeDrawer();
      }
    });
  }


}
