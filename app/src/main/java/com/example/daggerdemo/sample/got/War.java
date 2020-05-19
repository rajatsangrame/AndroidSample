package com.example.daggerdemo.sample.got;

import javax.inject.Inject;

/**
 * Created by Rajat Sangrame on 7/5/20.
 * http://github.com/rajatsangrame
 */
public class War {

    private Starks starks;
    private Boltons boltons;

    @Inject
    public War(Starks starks, Boltons boltons) {
        this.starks = starks;
        this.boltons = boltons;
    }

    public void prepare() {
        starks.prefareForWar();
        boltons.prefareForWar();
    }

    public void attend() {
        starks.attendWar();
        boltons.attendWar();
    }
}
