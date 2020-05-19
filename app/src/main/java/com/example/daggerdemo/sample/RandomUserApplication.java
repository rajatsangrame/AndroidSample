package com.example.daggerdemo.sample;

import android.app.Activity;
import android.app.Application;

import com.example.daggerdemo.sample.component.DaggerRandomUserComponent;
import com.example.daggerdemo.sample.component.RandomUserComponent;
import com.example.daggerdemo.sample.module.ContextModule;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
public class RandomUserApplication extends Application {

    private RandomUserComponent randomUserApplicationComponent;

    public static RandomUserApplication get(Activity activity) {
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public RandomUserComponent getRandomUserApplicationComponent() {
        return randomUserApplicationComponent;
    }
}
