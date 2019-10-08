package com.example.test;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "user_table", indices = {@Index(value = "roll_no", unique = true)})

public class User {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String mFirstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String mLastName;

    @NonNull
    @ColumnInfo(name = "roll_no")
    private int mRollNumber;

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull int rollNumber) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mRollNumber = rollNumber;
    }


    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public int getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(int mRollNumber) {
        this.mRollNumber = mRollNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
