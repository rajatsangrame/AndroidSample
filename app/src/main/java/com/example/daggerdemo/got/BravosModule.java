package com.example.daggerdemo.got;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 8/5/20.
 * http://github.com/rajatsangrame
 */
@Module
public class BravosModule {

    Cash cash;
    Soldiers soldiers;

    public BravosModule(Cash cash, Soldiers soldiers) {
        this.cash = cash;
        this.soldiers = soldiers;
    }

    @Provides
    Cash provideCash() {
        return cash;
    }

    @Provides
    Soldiers provideSoldiers() {
        return soldiers;
    }
}
