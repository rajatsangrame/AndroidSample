package com.example.daggerdemo.got;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 7/5/20.
 * http://github.com/rajatsangrame
 */
@Component(modules = BravosModule.class)
public interface BattleComponents {

    War getWar();

    Cash getCash();

    Soldiers getSoldiers();
}
