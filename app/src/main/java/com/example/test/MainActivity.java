package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.test.db.Image;
import com.example.test.db.ImageDao;
import com.example.test.db.ImageRepository;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ImageView mImageDog;
    private ImageView mImageCat;
    private Button mButtonProcess , mButtonCancel;
    private ProgressBar mProgressBar;
    private MyViewModel mainActivityViewModel;
    private static final String TAG = "MainActivity";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

        initUi();

    }

    private int getMode() {
        RadioGroup radioGroup = findViewById(R.id.radio_group);

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_dog:
                return 1;
            case R.id.radio_cat_dog:
                return 2;
        }
        return 1;
    }

    void initUi() {

        mImageDog = findViewById(R.id.img_dog);
        mImageCat = findViewById(R.id.img_cat);
        mButtonProcess = findViewById(R.id.btn_process);
        mButtonCancel = findViewById(R.id.btn_cancel);
        mProgressBar = findViewById(R.id.progress);

        mainActivityViewModel.getAllImage().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {

                Log.i(TAG, "onChanged: Size " + images.size());

                for (Image image : images) {

                    Bitmap bmp = BitmapFactory.decodeFile(image.getImage());

                    if (image.getAnimal().equals("cat")) {

                        mImageCat.setImageBitmap(bmp);
                    }
                    if (image.getAnimal().equals("dog")) {
                        mImageDog.setImageBitmap(bmp);

                    }
                }

                showWorkFinished();
            }
        });

        mButtonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showWorkInProgress();
                mainActivityViewModel.processWork(getMode());

            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivityViewModel.cancelWork();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_reset) {

            ImageRepository repository = mainActivityViewModel.getImageRepository();

            if (repository.getAllImages() != null) {

                repository.deleteAll();
                mImageDog.setImageBitmap(null);
                mImageCat.setImageBitmap(null);

                Toast.makeText(this, "DB Cleared", Toast.LENGTH_SHORT).show();
            }
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void showWorkInProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mButtonCancel.setVisibility(View.VISIBLE);
        mButtonProcess.setVisibility(View.GONE);
    }

    private void showWorkFinished() {
        mProgressBar.setVisibility(View.GONE);
        mButtonCancel.setVisibility(View.GONE);
        mButtonProcess.setVisibility(View.VISIBLE);
    }


}