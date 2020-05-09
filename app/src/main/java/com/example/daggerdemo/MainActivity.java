package com.example.daggerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.daggerdemo.adapter.RandomUserAdapter;
import com.example.daggerdemo.component.DaggerMainActivityComponent;
import com.example.daggerdemo.component.MainActivityComponent;
import com.example.daggerdemo.interfaces.RandomUsersApi;
import com.example.daggerdemo.model.RandomUsers;
import com.example.daggerdemo.module.MainActivityModule;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Ref: https://medium.com/@harivigneshjayapalan/dagger-2-for-android-beginners-advanced-part-ii-eb6f8d8a8926
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Inject
    RandomUsersApi randomUsersApi;
    @Inject
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
        mainActivityComponent.injectMainActivity(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        randomUsersApi.getRandomUsers(2).enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {


                Log.i(TAG, "onResponse: ");

                if (response.body() != null) {
                    mAdapter.setItems(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {

                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
