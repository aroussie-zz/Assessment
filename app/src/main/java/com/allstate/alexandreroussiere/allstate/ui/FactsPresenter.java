package com.allstate.alexandreroussiere.allstate.ui;

import android.content.Context;
import android.util.Log;

import com.allstate.alexandreroussiere.allstate.Constant;
import com.allstate.alexandreroussiere.allstate.helper.DatabaseHelper;
import com.allstate.alexandreroussiere.allstate.model.Facts;
import com.allstate.alexandreroussiere.allstate.network.FactsService;
import com.allstate.alexandreroussiere.allstate.network.OnDataFetchedListener;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class FactsPresenter {

    private static final String TAG = "FactsPresenter";

    private OnDataFetchedListener listener;
    private FactsService service;
    private Facts facts;
    private DatabaseHelper database;

    public FactsPresenter(OnDataFetchedListener listener, Context context){
        this.listener = listener;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FactsService.class);
        database = new DatabaseHelper(context);
    }

    public void fetchData(){

        Call<Facts> result = service.findFacts(Constant.NUMBER_FACTS);
        result.enqueue(new Callback<Facts>() {
            @Override
            public void onResponse(Response<Facts> response, Retrofit retrofit) {

                if (response != null) {
                    facts = response.body();
                    if (facts != null) {
                        addFactInDatabase(facts.getFacts().get(0));
                    } else {
                        listener.updateUI(database.getAllFactsFromDatabase());
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Error fetching Data");
                t.printStackTrace();
                listener.displayErrorMessage("Error fetching data");
            }
        });
    }

    private void addFactInDatabase(String fact){
        database.addFactToDatabase(fact);
        listener.updateUI(database.getAllFactsFromDatabase());
    }

    public void displayFacts(){
        listener.updateUI(database.getAllFactsFromDatabase());
    }

}
