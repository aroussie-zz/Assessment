package com.allstate.alexandreroussiere.allstate.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.allstate.alexandreroussiere.allstate.R;
import com.allstate.alexandreroussiere.allstate.network.OnDataFetchedListener;

import java.util.ArrayList;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class FactsFragment extends Fragment implements OnDataFetchedListener,Button.OnClickListener {

    private static final String TAG = "Facts Fragment";

    private View view;
    private RecyclerView recyclerView;
    private FactsAdapter adapter;
    private FactsPresenter presenter;
    private TextView emptyView;
    private Button button_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.facts_layout, container, false);
        setViews();
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        setUp();
        presenter.displayFacts();

    }

    @Override
    public void updateUI(ArrayList<String> facts) {

        adapter.setData(facts);

        if (adapter.getItemCount() != 0 ) {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();

    }

    private void setUp() {
        presenter = new FactsPresenter(this,getContext());
        adapter = new FactsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        button_add.setOnClickListener(this);
    }

    private void setViews(){
        recyclerView = (RecyclerView) view.findViewById(R.id.factsFound_list);
        emptyView = (TextView)view.findViewById(R.id.empty_view);
        button_add = (Button)view.findViewById(R.id.button_add);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        presenter.fetchData();
    }


}
