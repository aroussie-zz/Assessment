package com.allstate.alexandreroussiere.allstate.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.allstate.alexandreroussiere.allstate.R;

import java.util.ArrayList;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactsRowHolder> {

    private OnItemClickListener itemClickListener;
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

    public String getFact(int position) { return data.get(position); }

    public class FactsRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView facts;
        public FactsRowHolder(View itemView) {
            super(itemView);
            facts = (TextView) itemView.findViewById(R.id.fact_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setData(ArrayList<String> facts){
        data = facts;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.itemClickListener = mItemClickListener;
    }

}

