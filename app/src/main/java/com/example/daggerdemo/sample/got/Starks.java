package com.example.daggerdemo.sample.got;

import javax.inject.Inject;

/**
 * Created by Rajat Sangrame on 7/5/20.
 * http://github.com/rajatsangrame
 */
public class Starks implements House {

    @Inject
    public Starks() {
    }

    @Override
    public void prefareForWar() {

        System.out.println(this.getClass().getSimpleName() + " Prepare");
    }

    @Override
    public void attendWar() {
        System.out.println(this.getClass().getSimpleName() + " Attend");
    }
}
