package com.example.test.worker;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bumptech.glide.Glide;
import com.example.test.Constants;
import com.example.test.db.Image;
import com.example.test.db.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rajat Sangrame on 12/10/19.
 * http://github.com/rajatsangrame
 */
public class DogWorker extends Worker {

    public DogWorker(@NonNull Context context,
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
                    .load(Constants.URL_DOG)
                    .submit()
                    .get();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
            byte[] image = stream.toByteArray();
            imageRepository.insert(new Image("dog", image));
            bitmap.recycle();

            return Result.success();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return Result.failure();

        }

    }
}
