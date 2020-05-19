package com.example.daggerdemo.sample.component;

import com.example.daggerdemo.sample.interfaces.RandomUserApplicationScope;
import com.example.daggerdemo.sample.interfaces.RandomUsersApi;
import com.example.daggerdemo.sample.module.PicassoModule;
import com.example.daggerdemo.sample.module.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {

    RandomUsersApi getRandomUserService();

    Picasso getPicasso();
}
