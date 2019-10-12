package com.example.test.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Rajat Sangrame on 13/10/19.
 * http://github.com/rajatsangrame
 */

@Database(entities = {Image.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();

    private static volatile ImageDatabase INSTANCE;


    static ImageDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ImageDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ImageDatabase.class, "user_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(mRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback mRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    //new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ImageDao mDao;

        PopulateDbAsync(ImageDatabase db) {
            mDao = db.imageDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }

}
