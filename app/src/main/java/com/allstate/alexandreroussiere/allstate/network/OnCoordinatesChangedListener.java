package com.allstate.alexandreroussiere.allstate.network;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public interface OnCoordinatesChangedListener {

    void updateUI(float x, float y, float z);
    void displayMessageError(String errorMessage);
}
