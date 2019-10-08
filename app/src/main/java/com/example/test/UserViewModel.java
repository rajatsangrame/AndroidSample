package com.example.test;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private LiveData<List<User>> mAllUsers;


    public UserViewModel(Application application) {
        super(application);

        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUser();
    }

    LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public void insert(User user) {
        mRepository.insert(user);
    }
}
