package com.example.test.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Room;

/**
 * Created by Rajat Sangrame on 13/10/19.
 * http://github.com/rajatsangrame
 */

@Entity(tableName = "image_table", indices = {@Index(value = "animal", unique = true)})
public class Image extends Room {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "image")
    private String image;

    @NonNull
    @ColumnInfo(name = "animal")
    private String animal;


    public Image(@NonNull String animal, @NonNull String image) {
        this.animal = animal;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    public String getAnimal() {
        return animal;
    }

    public void setAnimal(@NonNull String animal) {
        this.animal = animal;
    }
}
