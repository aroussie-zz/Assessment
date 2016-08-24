package com.allstate.alexandreroussiere.allstate.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class CatFact extends RealmObject {

    @Required
    private String fact;

    @PrimaryKey
    private int id;

    public String getFact() { return fact; }
    public void setFact(String str) { fact = str; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
