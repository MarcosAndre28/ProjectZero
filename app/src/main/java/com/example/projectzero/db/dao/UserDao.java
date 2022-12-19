package com.example.projectzero.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectzero.db.model.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface UserDao {

    @Insert
    Completable insert(User user);

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    Maybe<User> getUserByEmail(String email);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    Maybe<User> getUserByEmailAndPassword(String email, String password);
}

// definir as operações de CRUD para tabela de user
