package com.example.demo.graphs;

import android.graphics.Color;
import android.support.annotation.StringDef;
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
import com.example.demo.data.Post;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineGraphFragment extends Fragment {

    @Bind(R.id.chart)
    LineChart lineChart;
    private ArrayList<Post> posts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posts = getArguments().getParcelableArrayList("posts");
        //savedInstanceState.getParcelableArrayList("posts");

    }

    @Nullable
    @Override
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    private float getPostsPerUser(int userId) {
        float totalPosts = 0;
        for (Post post : posts) {
            if (post.userId() == userId) {
                totalPosts++;
            }

        }
        return totalPosts;
    }


    private ArrayList<String> getUserIDs() {
        ArrayList<String> userIds = new ArrayList<>();
        for (Post post : posts) {
            if (!userIds.contains(String.valueOf(post.userId()))) {
                userIds.add(String.valueOf(post.userId()));
            }
        }
        return userIds;
    }

    private ArrayList<Entry> setYAxisValues() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (String userId : getUserIDs()) {
            yVals.add(new Entry(Float.valueOf(userId), getPostsPerUser(Integer.valueOf(userId))));
        }
        return yVals;
    }

    private void setData() {
        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;
        LineData set2;

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
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        lineChart.setData(data);

    }


}
