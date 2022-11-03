package com.example.projectzero.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectzero.db.model.User;

import java.util.List;
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> all();

    @Insert
    long insertUser(User user);

    @Query("SELECT * FROM user WHERE email = (:email) and password = (:password)")
    User loginInfo(String email, String password);
}
// A anotação @Dao indica que esta interface deve ser usada como um objeto de acesso a dados
// A @query marca os métodos contidos nas classes anotadas do DAO como métodos de consulta
// A @Insert marca os métodos como sendo capazes de inserir objetos nas tableas do Db
// A @Delete anotação, com exceção da exclusão de objetos especificos das tabelas do banco de dados
