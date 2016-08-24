package com.allstate.alexandreroussiere.allstate.network;

import com.allstate.alexandreroussiere.allstate.model.Facts;

import java.util.ArrayList;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public interface OnDataFetchedListener {

     void updateUI(ArrayList<String> facts);
     void displayErrorMessage(String errorMessage);

}
