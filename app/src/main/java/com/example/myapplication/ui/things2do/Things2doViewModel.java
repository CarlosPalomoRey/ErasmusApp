package com.example.myapplication.ui.things2do;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Things2doViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Things2doViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}