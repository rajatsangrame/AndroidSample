package com.example.daggerdemo.module;

import android.app.Activity;
import android.content.Context;

import com.example.daggerdemo.interfaces.RandomUserApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module
public class ActivityModule {
    private final Context context;

    ActivityModule(Activity context) {
        this.context = context;
    }

    @Named("activity_context")
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}
