package com.allstate.alexandreroussiere.allstate.helper;

import android.content.Context;

import com.allstate.alexandreroussiere.allstate.database.CatFact;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class DatabaseHelper {

    private RealmConfiguration realmConfig;
    public Realm database;

    public DatabaseHelper(Context context){
        realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public void addFactToDatabase(String fact){
        database = Realm.getInstance(realmConfig);
        database.beginTransaction();
        int id;
        CatFact catFact = database.createObject(CatFact.class);
        catFact.setFact(fact);

        //Auto-incrementation of the id
        try {
            id = database.where(CatFact.class).max("id").intValue() + 1;
        } catch( ArrayIndexOutOfBoundsException ex ) {
            id = 0;
        }

        catFact.setId(id);
        database.commitTransaction();
    }

    public ArrayList<String> getAllFactsFromDatabase(){

        database = Realm.getInstance(realmConfig);
        ArrayList<String> facts = new ArrayList<>();
        RealmResults<CatFact> results = database.where(CatFact.class).findAll().sort("id", Sort.DESCENDING);
        for ( CatFact f : results ) {
            facts.add(f.getFact());
        }
        return facts;
    }
}
