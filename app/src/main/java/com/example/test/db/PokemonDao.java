package com.example.test.db;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


/**
 * Created by Rajat Sangrame on 17/10/19.
 * http://github.com/rajatsangrame
 */

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM pokemon_table ORDER BY id ASC")
    LiveData<List<Pokemon>> getAllPokemon();

    @Query("SELECT COUNT(*) FROM pokemon_table")
    Integer pokemonsCount();

    @Insert
    long insert(Pokemon pokemons);

    @Query("DELETE FROM pokemon_table")
    void deleteAll();
}
