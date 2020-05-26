package com.example.daggerdemo.foody.di.module;

import android.content.Context;

import com.example.daggerdemo.foody.data.rest.RetrofitApi;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationContext;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajat Sangrame on 12/5/20.
 * http://github.com/rajatsangrame
 */
@Module(includes = {ViewModelModule.class, OkHttpClientModule.class})
public class ApplicationModule {

    public static final String BASE_URL = "https://developers.zomato.com/api/v2.1/";

    @Provides
    public RetrofitApi randomUsersApi(Retrofit retrofit) {
        return retrofit.create(RetrofitApi.class);
    }

    @FoodyApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @FoodyApplicationScope
    @Provides
    RestaurantRepository getRepository(@FoodyApplicationContext Context context, RetrofitApi retrofitApi) {
        return new RestaurantRepository(context, retrofitApi);
    }

}
