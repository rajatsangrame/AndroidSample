package com.example.test.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

/**
 * Created by Rajat Sangrame on 17/10/19.
 * http://github.com/rajatsangrame
 */
public class PokemonRepository {

    private PokemonDao mPokemonDao;
    private LiveData<List<Pokemon>> mAllIPokemon;
    private static final String TAG = "PokemonRepository";

    public PokemonRepository(Context context) {

        PokemonDatabase db = PokemonDatabase.getInstance(context);
        mPokemonDao = db.pokemonDao();
        mAllIPokemon = mPokemonDao.getAllPokemon();
    }

    public void insert(Pokemon pokemon) {
        new InsertAsyncTask(mPokemonDao, DbOperation.INSERT).execute(pokemon);
    }

    public void delete(Pokemon pokemon) {
        new InsertAsyncTask(mPokemonDao, DbOperation.DELETE).execute(pokemon);
    }

    public void deleteAll() {
        new InsertAsyncTask(mPokemonDao, DbOperation.DELETE_ALL).execute();
    }



    private static class InsertAsyncTask extends AsyncTask<Pokemon, Void, Void> {

        private PokemonDao mAsyncTaskDao;
        private DbOperation mOperation;

        InsertAsyncTask(PokemonDao dao, DbOperation operation) {
            mAsyncTaskDao = dao;
            mOperation = operation;
        }


        @Override
        protected Void doInBackground(Pokemon... params) {
            switch (mOperation) {
                case DELETE:
                    //mAsyncTaskDao.delete(params[0].getId());
                    break;
                case INSERT:
                    long result = mAsyncTaskDao.insert(params[0]);
                    Log.i(TAG, "doInBackground: Result: " + result);
                    break;
                case DELETE_ALL:
                    mAsyncTaskDao.deleteAll();
                    break;
            }
            return null;
        }
    }

    private enum DbOperation {
        INSERT, DELETE, DELETE_ALL
    }


    // Getter and Setters
    public PokemonDao getmPokemonDao() {
        return mPokemonDao;
    }

    public void setmPokemonDao(PokemonDao mPokemonDao) {
        this.mPokemonDao = mPokemonDao;
    }

    public LiveData<List<Pokemon>> getmAllIPokemon() {
        return mAllIPokemon;
    }

    public void setmAllIPokemon(LiveData<List<Pokemon>> mAllIPokemon) {
        this.mAllIPokemon = mAllIPokemon;
    }
}
