package com.example.demo.graphs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.demo.R;
import com.github.mikephil.charting.charts.LineChart;

public class LineGraphFragment extends Fragment {

  @Bind(R.id.chart) LineChart lineChart;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.graph_layout, container, false);
    ButterKnife.bind(this, rootView);
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }
}
