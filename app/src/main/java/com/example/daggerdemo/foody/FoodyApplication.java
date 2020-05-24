package com.example.daggerdemo.foody;

import android.app.Activity;
import android.app.Application;

import com.example.daggerdemo.foody.di.component.DaggerFoodyComponent;
import com.example.daggerdemo.foody.di.component.FoodyComponent;
import com.example.daggerdemo.foody.di.module.ContextModule;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
public class FoodyApplication extends Application {

    private FoodyComponent foodyComponent;

    public static FoodyApplication get(Activity activity) {
        return (FoodyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        foodyComponent = DaggerFoodyComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public FoodyComponent getFoodyComponent() {
        return foodyComponent;
    }
}