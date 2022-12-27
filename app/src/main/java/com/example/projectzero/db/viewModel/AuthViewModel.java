package com.example.projectzero.db.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.projectzero.db.model.User;
import com.example.projectzero.db.repository.UserRepository;

import io.reactivex.Completable;
import io.reactivex.Maybe;


public class AuthViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public Completable registerUser(User user){
        return userRepository.insertUser(user);
    }

    public Maybe<Boolean> userExists(String email) {
        return userRepository.userExists(email);
    }

    public Maybe<Boolean> login(String email,String password) throws Exception {
        return userRepository.login(email,password);
    }
}
