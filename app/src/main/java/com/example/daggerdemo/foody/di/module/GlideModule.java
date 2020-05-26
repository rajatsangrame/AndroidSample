package com.example.daggerdemo.foody.di.module;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationContext;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 * <p>
 * Ref: https://medium.com/@elye.project/glide-image-loader-the-advanced-9a048624d3e4
 * Ref: https://medium.com/@nuhkocaa/manage-all-your-glides-in-a-single-class-with-glidemodule-on-android-4856ee4983a1
 */
@Module(includes = ContextModule.class)
public class GlideModule extends AppGlideModule {

    @FoodyApplicationScope
    @Provides
    public Glide getGlide(@FoodyApplicationContext Context context) {
        return Glide.get(context);
    }

}
