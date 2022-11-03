package com.example.projectzero.db.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.projectzero.db.AppDatabase;
import com.example.projectzero.db.dao.UserDao;
import com.example.projectzero.db.model.User;

public class UserViewModel extends AndroidViewModel {
    private UserDao userDao;
    private AppDatabase database;

    public UserViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        userDao = database.userDao();
    }

    public void insertUser(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    private class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        UserDao userDao;
        public InsertAsyncTask(UserDao dao){
            this.userDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
           userDao.insertUser(users[0]);
           return null;
        }
    }
}
