package com.example.projectzero.db.viewModel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzero.db.model.User;
import com.example.projectzero.db.repository.AuthRepository;
import com.example.projectzero.utils.SharedPreference;

public class SignInViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final SharedPreference sharedPreferences;
    private final MutableLiveData<Boolean> checkSignIn = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();

    public SignInViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
        sharedPreferences = new SharedPreference(getApplication().getApplicationContext());
    }

    public void signInUser(String email, String pass) {

        try {
            User user = authRepository.loginUser(email, pass);

            if (user != null) {
                sharedPreferences.saveLoggedInUser(user);
                checkSignIn.setValue(true);
            } else
                checkSignIn.setValue(false);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LiveData<Boolean> checkSignIn() {
        return checkSignIn;
    }


    public LiveData<Boolean> isLoggedIn() {

        User user = sharedPreferences.retrieveLoggedInUser();

        if (user != null)
            isLoggedIn.setValue(true);
        else
            isLoggedIn.setValue(false);


        return isLoggedIn;
    }
}
