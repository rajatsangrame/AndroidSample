package com.example.test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> txtViewValue = new MutableLiveData<>();

    public MutableLiveData<String> getTxtViewValue() {
        return txtViewValue;
    }

    public void setTxtViewValue(String txtViewValue) {
        this.txtViewValue.postValue(txtViewValue);
    }
}
