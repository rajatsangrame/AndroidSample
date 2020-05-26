package com.example.daggerdemo.foody.di.module;

import android.content.Context;

import com.example.daggerdemo.BuildConfig;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationContext;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module(includes = ContextModule.class)
public class OkHttpClientModule {

    @Provides
    public OkHttpClient okHttpClient(Cache cache, Interceptor interceptor) {
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    public File file(@FoodyApplicationContext Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @Provides
    public Interceptor interceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        //.addQueryParameter()
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("user-key", BuildConfig.ApiKey)
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }
}
