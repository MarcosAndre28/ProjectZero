package com.example.projectzero.db.repository;

import android.app.Application;
import android.util.Log;

import com.example.projectzero.db.AppDatabase;
import com.example.projectzero.db.dao.UserDao;
import com.example.projectzero.db.model.User;
import com.example.projectzero.utils.Utils;

import org.mindrot.jbcrypt.BCrypt;

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

    public Maybe<Boolean> login(String email, String password) {
        return userDao.getUserByEmail(email)
                .flatMap(user -> {
                    if (BCrypt.checkpw(password, user.getPassword())) {
                        return Maybe.just(true);
                    } else {
                        return Maybe.just(false);
                    }
                })
                .defaultIfEmpty(false);
    }

}
