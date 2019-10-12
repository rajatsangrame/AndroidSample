package com.example.test.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Rajat Sangrame on 13/10/19.
 * http://github.com/rajatsangrame
 */

@Dao
public interface ImageDao {

    @Query("SELECT * FROM image_table")
    LiveData<List<Image>> getAllImages();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Image image);

    @Query("SELECT * FROM image_table WHERE id=:id")
    Image getImage(int id);


    @Query("DELETE FROM image_table WHERE id=:id")
    void delete(int id);


    @Query("DELETE FROM image_table")
    void deleteAll();
}
