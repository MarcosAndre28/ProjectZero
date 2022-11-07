package com.example.projectzero.db.viewModel;

import android.app.Application;
import android.text.Editable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzero.db.model.User;
import com.example.projectzero.db.repository.AuthRepository;

public class SignUpViewModel extends AndroidViewModel {

    private AuthRepository authRepository;
    private MutableLiveData<Boolean> isAlreadyExist = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
    }

    public void registerUser(User user) throws Exception {
        authRepository.registerUser(user);
    }


    public LiveData<Boolean> isExist(String email) throws Exception {

        isAlreadyExist.setValue(authRepository.CheckUser(email));

        return isAlreadyExist;
    }

}

