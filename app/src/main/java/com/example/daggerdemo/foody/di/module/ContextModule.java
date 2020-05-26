package com.example.daggerdemo.foody.di.module;

import android.content.Context;

import com.example.daggerdemo.foody.di.scope.FoodyApplicationContext;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @FoodyApplicationContext
    @FoodyApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
