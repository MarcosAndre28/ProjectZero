package com.example.projectzero.db.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.projectzero.db.AppDatabase;
import com.example.projectzero.db.dao.UserDao;
import com.example.projectzero.db.model.User;
import com.example.projectzero.utils.Utilites;

import io.reactivex.Completable;
import io.reactivex.Maybe;


public class UserRepository{

    private UserDao userDao;

    public UserRepository(Application application) {
        userDao = AppDatabase.getInstance(application).userDao();
    }

    public Completable  insertUser(User user){
       return userDao.insert(user);
    }

    public Maybe<Boolean> userExists(String email) {
        return userDao.getUserByEmail(email)
                .doOnSuccess(user -> Log.d("TAG", "Consulta realizada com sucesso: " + user))
                .map(user -> true)
                .defaultIfEmpty(false);
    }

    private String encryptPassword(String password) throws Exception {
        return Utilites.hashPassword(password);
    }

    public Maybe<Boolean> loginExists(String email, String password) throws Exception {
        String encryptedPassword = encryptPassword(password);
        return userDao.getUserByEmailAndPassword(email,encryptedPassword)
                .map(user -> true)
                .defaultIfEmpty(false);
    }
}
