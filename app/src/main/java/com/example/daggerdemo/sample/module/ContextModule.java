package com.example.daggerdemo.sample.module;

import android.content.Context;

import com.example.daggerdemo.sample.interfaces.ApplicationContext;
import com.example.daggerdemo.sample.interfaces.RandomUserApplicationScope;

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

    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
