package com.example.demo.navDrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.demo.R;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.subjects.PublishSubject;

public class NavigationDrawerAdapter extends BaseAdapter {
  private Context context;
  private List<NavigationItem> items = new ArrayList<>();
  private PublishSubject<String> drawerItemClickStream;

  public NavigationDrawerAdapter(Context context) {
    this.context = context;
    items.add(new NavigationItem("Click Me Im not dummy"));
    items.add(new NavigationItem("Dummy Item 1"));
    items.add(new NavigationItem("Dummy Item 2"));
    items.add(new NavigationItem("Dummy Item 3"));
    items.add(new NavigationItem("Dummy Item 4"));
    items.add(new NavigationItem("Dummy Item 5"));
    drawerItemClickStream = PublishSubject.create();
  }

  @Override public int getCount() {

    return items.size();
  }

  @Override public Object getItem(int position) {
    return items.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    View view;
    ViewHolder holder;

    if (convertView == null || convertView.getTag() == null) {
      LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.drawer_item, null);
      holder = new ViewHolder(view);
      holder.bindPosition(position);
    } else {
      view = convertView;
      holder = (ViewHolder) convertView.getTag();
    }
    holder.title.setText(items.get(position).getText());
    return view;
  }

  public Observable<String> getNavigationDrawerClickStream() {
    return drawerItemClickStream.asObservable();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.drawerItem) TextView title;
    int position;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bindPosition(int position) {
      this.position = position;
    }

    @OnClick(R.id.drawerItem) void onItemClicked() {
      NavigationItem item = (NavigationItem) getItem(position);
      if (position == 0) {
        drawerItemClickStream.onNext(item.getText());
      }
    }
  }
}
