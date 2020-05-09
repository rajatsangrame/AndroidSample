package com.example.daggerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.daggerdemo.adapter.RandomUserAdapter;
import com.example.daggerdemo.component.DaggerMainActivityComponent;
import com.example.daggerdemo.component.MainActivityComponent;
import com.example.daggerdemo.interfaces.RandomUsersApi;
import com.example.daggerdemo.model.RandomUsers;
import com.example.daggerdemo.module.MainActivityModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RandomUserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Battle of Bastards
        /*
        Cash cash = new Cash();
        Soldiers soldiers = new Soldiers();
        BattleComponents components = DaggerBattleComponents
                .builder()
                .bravosModule(new BravosModule(cash, soldiers))
                .build();
        components.getWar().prepare();
        components.getWar().attend();
        components.getCash();
        components.getSoldiers();
        */
        //endregion

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserApplicationComponent())
                .build();
        RandomUsersApi randomUsersApi = mainActivityComponent.getRandomUserService();
        randomUsersApi.getRandomUsers(10).enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {

                Log.i(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {

                Log.i(TAG, "onFailure: ");
            }
        });
        mAdapter = mainActivityComponent.getRandomUserAdapter();
    }
}
