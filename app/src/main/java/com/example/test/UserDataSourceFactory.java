package com.example.test;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Created by Rajat Sangrame on 19/10/19.
 * http://github.com/rajatsangrame
 */
public class UserDataSourceFactory extends DataSource.Factory<Long, User> {

    public MutableLiveData<UserDataSource> userLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Long, User> create() {
        UserDataSource userDataSource = new UserDataSource();
        userLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }
}
