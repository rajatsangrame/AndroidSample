package com.example.daggerdemo.component;

import com.example.daggerdemo.interfaces.RandomUserApplicationScope;
import com.example.daggerdemo.interfaces.RandomUsersApi;
import com.example.daggerdemo.module.PicassoModule;
import com.example.daggerdemo.module.RandomUsersModule;
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
