package com.example.demo.graphs;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.demo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.List;

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
    setData();
    lineChart.setTouchEnabled(true);
    lineChart.setDragEnabled(true);
    lineChart.setScaleEnabled(true);
    return rootView;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  private ArrayList<Entry> setYAxisValues(){
    ArrayList<Entry> yVals = new ArrayList<Entry>();
    yVals.add(new Entry(60, 0));
    yVals.add(new Entry(48, 1));
    yVals.add(new Entry(70.5f, 2));
    yVals.add(new Entry(100, 3));
    yVals.add(new Entry(180.9f, 4));

    return yVals;
  }

  private ArrayList<String> setXAxisValues(){
    ArrayList<String> xVals = new ArrayList<String>();
    xVals.add("10");
    xVals.add("20");
    xVals.add("30");
    xVals.add("30.5");
    xVals.add("40");

    return xVals;
  }

  private void setData() {
    ArrayList<String> xVals = setXAxisValues();

    ArrayList<Entry> yVals = setYAxisValues();

    LineDataSet set1;

    set1 = new LineDataSet(yVals, "DataSet 1");
    set1.setFillAlpha(110);

    set1.setColor(Color.BLACK);
    set1.setCircleColor(Color.BLACK);
    set1.setLineWidth(1f);
    set1.setCircleRadius(3f);
    set1.setDrawCircleHole(false);
    set1.setValueTextSize(9f);
    set1.setDrawFilled(true);

    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    dataSets.add(set1); // add the datasets

    // create a data object with the datasets
    LineData data = new LineData(dataSets);

    // set data
    lineChart.setData(data);

  }


}
