package com.example.daggerdemo.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.daggerdemo.R;
import com.example.daggerdemo.sample.adapter.RandomUserAdapter;
import com.example.daggerdemo.sample.component.DaggerMainActivityComponent;
import com.example.daggerdemo.sample.component.MainActivityComponent;
import com.example.daggerdemo.sample.interfaces.RandomUsersApi;
import com.example.daggerdemo.sample.model.RandomUsers;
import com.example.daggerdemo.sample.model.Result;
import com.example.daggerdemo.sample.module.MainActivityModule;
import com.example.daggerdemo.sample.util.Utils;


import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Dagger
 * Ref: https://medium.com/@harivigneshjayapalan/dagger-2-for-android-beginners-advanced-part-ii-eb6f8d8a8926
 * <p>
 * RxJava Part 1 & 2
 * Ref: https://medium.com/@kurtisnusbaum/rxandroid-basics-part-1-c0d5edcf6850
 * Ref: https://medium.com/@kurtisnusbaum/rxandroid-basics-part-2-6e877af352
 * Ref: https://mindorks.com/course/learn-rxjava
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

        //region Zomato
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserApplicationComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        //endregion

        runAsynRxJava();
    }


    void runAsynRxJava() {

        Observable<RandomUsers> randomUsersObservable = randomUsersApi.getRandomUsers(1);
        randomUsersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<RandomUsers, List<Result>>() {
                    @Override
                    public List<Result> apply(RandomUsers randomUsers) throws Exception {
                        return Utils.getListResult(randomUsers);
                    }
                })
                .subscribe(getObserver());
    }

    private Observer<List<Result>> getObserver() {
        return new Observer<List<Result>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Result> results) {
                mAdapter.setItems(results);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    void runBasicRxJava() {
        Observable<List<String>> listObservable = Observable.just(getColorList());
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.i(TAG, "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> strings) {

                Log.i(TAG, "onNext: " + strings.size());
            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, "onComplete: ");
            }
        });
    }

    private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        colors.add("chartreuse");
        colors.add("Van Dyke Brown");
        return colors;
    }

}
