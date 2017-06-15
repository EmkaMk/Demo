package com.example.demo.navDrawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ListAdapter;
import android.widget.ListView;
import rx.Observable;

public class NavigationDrawerView extends ListView {

  private DrawerLayout drawerLayout;
  private NavigationDrawerAdapter drawerAdapter;

  public NavigationDrawerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setDrawerLayout(DrawerLayout drawerLayout) {
    this.drawerLayout = drawerLayout;
  }

  @Override public void setAdapter(ListAdapter adapter) {
    if (!(adapter instanceof NavigationDrawerAdapter)) {
      throw new IllegalArgumentException();
    }
    drawerAdapter = (NavigationDrawerAdapter) adapter;
    super.setAdapter(adapter);
  }

  public Observable<String> getClicks() {

    return drawerAdapter.getNavigationDrawerClickStream();
  }

  public void closeDrawer() {
    drawerLayout.closeDrawer(Gravity.LEFT);
  }
}

