package com.example.projectzero.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.projectzero.utils.Constants;

@Entity(tableName = Constants.TABLE_USER)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "Password")
    private String password;

    public User(String email,String name, String password) {
        this.email = email;
        this.Name = name;
        this.password = password;
    }

    public User(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
