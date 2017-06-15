package com.example.demo.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.demo.R;
import com.example.demo.data.Post;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

  private List<Post> postItems;

  public RecyclerAdapter() {
    this.postItems = new ArrayList<>();
  }

  public void setPostItems(List<Post> postItems) {
    this.postItems = postItems;
    notifyDataSetChanged();
  }

  public void clearDataset() {
    postItems.clear();
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.recycler_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.userID.append(String.valueOf(postItems.get(position).userId()));
    holder.id.append(String.valueOf(postItems.get(position).id()));
    holder.title.append(postItems.get(position).title());
  }

  @Override public int getItemCount() {
    return this.postItems.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.userId) TextView userID;
    @Bind(R.id.id) TextView id;
    @Bind(R.id.title) TextView title;


    protected ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}


