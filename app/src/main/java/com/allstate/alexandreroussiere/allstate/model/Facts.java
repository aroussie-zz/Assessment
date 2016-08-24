package com.allstate.alexandreroussiere.allstate.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class Facts {

    @SerializedName(value = "facts")
    ArrayList<String> facts;

    public ArrayList<String> getFacts() { return facts; }

}
