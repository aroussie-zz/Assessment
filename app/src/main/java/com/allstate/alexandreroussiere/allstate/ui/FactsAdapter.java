package com.allstate.alexandreroussiere.allstate.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allstate.alexandreroussiere.allstate.R;

import java.util.ArrayList;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactsRowHolder> {

    private ArrayList<String> data = new ArrayList<>();
    private Context mContext;

    @Override
    public FactsRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_row_layout,null);
        mContext = parent.getContext();
        return new FactsRowHolder(v);
    }

    @Override
    public void onBindViewHolder(FactsRowHolder holder, int position) {
        String fact = data.get(position);
        holder.facts.setText(fact);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FactsRowHolder extends RecyclerView.ViewHolder {
        TextView facts;
        public FactsRowHolder(View itemView) {
            super(itemView);
            facts = (TextView) itemView.findViewById(R.id.fact_description);
        }

    }

    public void setData(ArrayList<String> facts){
        data = facts;
    }

}

