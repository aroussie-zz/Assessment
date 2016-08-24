package com.allstate.alexandreroussiere.allstate.ui;

import android.util.Log;

import com.allstate.alexandreroussiere.allstate.Constant;
import com.allstate.alexandreroussiere.allstate.R;
import com.allstate.alexandreroussiere.allstate.model.Facts;
import com.allstate.alexandreroussiere.allstate.network.FactsService;
import com.allstate.alexandreroussiere.allstate.network.OnDataFetchedListener;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.ArrayList;

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

    OnDataFetchedListener listener;
    FactsService service;
    Facts facts;

    public FactsPresenter(OnDataFetchedListener listener){

        this.listener = listener;

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new com.squareup.okhttp.Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Log.i(TAG, "URL: " + chain.request().url());
                com.squareup.okhttp.Response response = chain.proceed(chain.request());
                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        service = retrofit.create(FactsService.class);

    }

    public void fetchData(){

        Call<Facts> result = service.findFacts(Constant.NUMBER_FACTS);
        result.enqueue(new Callback<Facts>() {
            @Override
            public void onResponse(Response<Facts> response, Retrofit retrofit) {

                if (response != null) {
                    facts = response.body();
                    Log.d(TAG,"facts: " + facts.getFacts().size());
                    if (facts != null ) {
                        listener.updateUI(getFactsFound());
                    }else {
                        listener.updateUI(new ArrayList<String>());
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

    private ArrayList<String> getFactsFound(){

        ArrayList<String> response = new ArrayList<>();

        for ( String fact: facts.getFacts()){
            response.add(fact);
        }

        return response;
    }


}
