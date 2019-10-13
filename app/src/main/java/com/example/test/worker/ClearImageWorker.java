package com.example.test.worker;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bumptech.glide.Glide;
import com.example.test.Constants;
import com.example.test.db.Image;
import com.example.test.db.ImageRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rajat Sangrame on 12/10/19.
 * http://github.com/rajatsangrame
 */
public class ClearImageWorker extends Worker {

    private static final String TAG = "ClearImageWorker";

    public ClearImageWorker(@NonNull Context context,
                            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Context context = getApplicationContext();
        ImageRepository imageRepository = new ImageRepository(context);

        try {
            File outputDirectory = new File(context.getFilesDir(),
                    Constants.OUTPUT_PATH);

            if (outputDirectory.exists()) {
                File[] entries = outputDirectory.listFiles();
                if (entries != null && entries.length > 0) {
                    for (File entry : entries) {
                        String name = entry.getName();
                        if (!TextUtils.isEmpty(name) && name.endsWith(".png")) {
                            boolean deleted = entry.delete();
                            Log.i(TAG, String.format("Deleted %s - %s",
                                    name, deleted));
                        }
                    }
                }

                imageRepository.deleteAll();
            }

            return Result.success();
        } catch (Exception exception) {
            Log.e(TAG, "Error cleaning up", exception);
            return Result.failure();
        }
    }
}
