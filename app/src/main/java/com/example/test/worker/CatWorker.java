package com.example.test.worker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bumptech.glide.Glide;
import com.example.test.Constants;
import com.example.test.db.Image;
import com.example.test.db.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rajat Sangrame on 12/10/19.
 * http://github.com/rajatsangrame
 */
public class CatWorker extends Worker {

    private static final String TAG = "CatWorker";

    public CatWorker(@NonNull Context context,
                     @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Context context = getApplicationContext();
        ImageRepository imageRepository = new ImageRepository(context);

        try {

            Bitmap bitmap = Glide
                    .with(getApplicationContext())
                    .asBitmap()
                    .load(Constants.URL_CAT)
                    .submit()
                    .get();

            File output = WorkerUtil.writeBitmapToFile(context, bitmap);

            if (output.exists()) {

                imageRepository.insert(new Image("cat", output.getAbsolutePath()));
                return Result.success();
            }

        } catch (ExecutionException | InterruptedException | FileNotFoundException e) {

            Log.i(TAG, "doWork: " + e.getLocalizedMessage());
        }

        return Result.failure();
    }
}
