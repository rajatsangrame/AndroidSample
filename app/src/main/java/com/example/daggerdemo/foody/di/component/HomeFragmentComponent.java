package com.example.daggerdemo.foody.di.component;

import com.example.daggerdemo.foody.di.module.HomeFragmentModule;
import com.example.daggerdemo.foody.di.module.MainActivityModule;
import com.example.daggerdemo.foody.di.scope.MainActivityScope;
import com.example.daggerdemo.foody.ui.fragment.HomeFragment;
import com.example.daggerdemo.foody.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Component(modules = HomeFragmentModule.class, dependencies = FoodyComponent.class)
@MainActivityScope
public interface HomeFragmentComponent {

    void Fragment(HomeFragment homeFragment);

}
