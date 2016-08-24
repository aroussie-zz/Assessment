package com.allstate.alexandreroussiere.allstate.network;

import com.allstate.alexandreroussiere.allstate.model.Facts;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public interface FactsService {

    @GET( "facts" )
    Call<Facts> findFacts ( @Query("number") int numberMax );
}
