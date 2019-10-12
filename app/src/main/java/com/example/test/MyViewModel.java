package com.example.test;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

import com.example.test.db.Image;
import com.example.test.db.ImageRepository;
import com.example.test.worker.CatWorker;
import com.example.test.worker.DogWorker;

import java.util.List;

import static com.example.test.Constants.CAT_TAG;
import static com.example.test.Constants.DOG_TAG;
import static com.example.test.Constants.DOWNLOAD_WORK_NAME;

/**
 * Created by Rajat Sangrame on 12/10/19.
 * http://github.com/rajatsangrame
 */
public class MyViewModel extends AndroidViewModel {

    private WorkManager mWorkManager;
    private ImageRepository mImageRepository;
    private LiveData<List<Image>> mAllImage;


    public MyViewModel(@NonNull Application application) {
        super(application);
        mWorkManager = WorkManager.getInstance(application);
        mImageRepository = new ImageRepository(application);
        mAllImage = mImageRepository.getAllImages();
    }

    public void processWork(int method) {

        // Create charging constraint
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        // Create Work Request for Dog Image
        OneTimeWorkRequest dog = new OneTimeWorkRequest.Builder(DogWorker.class)
                .setConstraints(constraints)
                .addTag(DOG_TAG)
                .build();

        // Create Work Request for Cat Image
        OneTimeWorkRequest cat = new OneTimeWorkRequest.Builder(CatWorker.class)
                .setConstraints(constraints)
                .addTag(CAT_TAG)
                .build();


        WorkContinuation continuation = mWorkManager
                .beginUniqueWork(DOWNLOAD_WORK_NAME,
                        ExistingWorkPolicy.REPLACE,
                        dog);

        if (method == Constants.CAT_AND_DOG) {
            continuation = continuation.then(cat);
        }
        continuation.enqueue();

    }

    /**
     * Cancel work using the work's unique name
     */
    void cancelWork() {
        mWorkManager.cancelUniqueWork(DOWNLOAD_WORK_NAME);
    }

    public LiveData<List<Image>> getAllImage() {
        return mAllImage;
    }

    public ImageRepository getImageRepository() {
        return mImageRepository;
    }
}
