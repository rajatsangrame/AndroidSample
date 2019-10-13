package com.example.test;

/**
 * Created by Rajat Sangrame on 12/10/19.
 * http://github.com/rajatsangrame
 */
public class Constants {

    public final static String URL_DOG =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBVB1lA2J5bEZ6xTbpui2dka3tyf0oQWJ1NdGCrqFl0lnYgaSm";

    public final static String URL_CAT =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQfwU0vTHvHnif1VMaRKZPV3aqHQfElgYSanFT0pOeAM-3Gh97J";

    public final static String DOWNLOAD_WORK_NAME = "cat_dog_process";
    public final static String DOG_TAG = "dog_worker";
    public final static String CAT_TAG = "cat_worker";
    public final static String CLEAR_IMAGE_TAG = "clear_image_worker";
    public final static int DOG_ONLY = 1;
    public final static int CAT_AND_DOG = 2;
    public static final String OUTPUT_PATH = "db_images";
    public static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";


    // Ensures this class is never instantiated
    private Constants() {}
}
