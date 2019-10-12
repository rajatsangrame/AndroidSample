package com.example.test.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Rajat Sangrame on 13/10/19.
 * http://github.com/rajatsangrame
 */
public class ImageRepository {

    private ImageDao mImageDao;
    private LiveData<List<Image>> mAllImages;
    private static final String TAG = "ImageDatabaseRepo";

    public ImageRepository(Context context) {
        ImageDatabase db = ImageDatabase.getDatabase(context);
        mImageDao = db.imageDao();
        mAllImages = mImageDao.getAllImages();
    }


    public LiveData<List<Image>> getAllImages() {
        return mAllImages;
    }

    public ImageDao getImageDao() {
        return mImageDao;
    }

    public void insert(Image image) {
        new InsertAsyncTask(mImageDao, DbOperation.INSERT).execute(image);
    }

    public void delete(Image image) {
        new InsertAsyncTask(mImageDao, DbOperation.DELETE).execute(image);
    }

    public void deleteAll() {
        new InsertAsyncTask(mImageDao, DbOperation.DELETE_ALL).execute();
    }



    private static class InsertAsyncTask extends AsyncTask<Image, Void, Void> {

        private ImageDao mAsyncTaskDao;
        private DbOperation mOperation;

        InsertAsyncTask(ImageDao dao, DbOperation operation) {
            mAsyncTaskDao = dao;
            mOperation = operation;
        }

        @Override
        protected Void doInBackground(final Image... params) {

            switch (mOperation) {
                case DELETE:
                    mAsyncTaskDao.delete(params[0].getId());
                    break;
                case INSERT:
                    long result = mAsyncTaskDao.insert(params[0]);
                    Log.i(TAG, "doInBackground: Result: "+ result);
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
}
