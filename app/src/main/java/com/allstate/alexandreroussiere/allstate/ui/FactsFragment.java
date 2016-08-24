package com.allstate.alexandreroussiere.allstate.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.allstate.alexandreroussiere.allstate.R;
import com.allstate.alexandreroussiere.allstate.network.OnDataFetchedListener;

import java.util.ArrayList;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class FactsFragment extends Fragment implements OnDataFetchedListener {

    private static final String TAG = "Facts Fragment";

    private View view;
    private RecyclerView recyclerView;
    private FactsAdapter adapter;
    private FactsPresenter presenter;
    private TextView emptyView;
    private ArrayList<String> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.facts_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.factsFound_list);
        emptyView = (TextView)view.findViewById(R.id.empty_view);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"HEEEEEEERE");
        presenter = new FactsPresenter(this);
        Log.d(TAG,"HEEEEEEERE22222");
        adapter = new FactsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        Log.d(TAG,"HEEEEEEERE33333");
        presenter.fetchData();


    }

    @Override
    public void updateUI(ArrayList<String> facts) {
        Log.d(TAG, "data: " + facts.size());

        adapter.setData(facts);
        Log.d(TAG,"HEEEEEEERE55555555");

        /*
        if (adapter.getItemCount() != 0) {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        */
        adapter.notifyDataSetChanged();

    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
}
