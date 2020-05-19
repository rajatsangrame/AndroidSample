package com.example.daggerdemo.zomato.di.module;

import android.content.Context;

import com.example.daggerdemo.zomato.di.scope.FoodyAppContext;
import com.example.daggerdemo.zomato.di.scope.FoodyApplicationScope;

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

    @FoodyAppContext
    @FoodyApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
