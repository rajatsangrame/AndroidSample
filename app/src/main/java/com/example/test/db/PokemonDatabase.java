package com.example.test.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Rajat Sangrame on 17/10/19.
 * http://github.com/rajatsangrame
 */
@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

    private static PokemonDatabase INSTANCE;

    public static PokemonDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PokemonDatabase.class,
                    "pokemon_table").build();
        }
        return INSTANCE;
    }
}