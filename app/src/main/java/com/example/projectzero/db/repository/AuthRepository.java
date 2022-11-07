package com.example.projectzero.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectzero.db.AppDatabase;
import com.example.projectzero.db.dao.UserDao;
import com.example.projectzero.db.model.User;

public class AuthRepository {

    private UserDao userDao;

    public AuthRepository(Application application) {
        AppDatabase userDatabase = AppDatabase.getInstance(application);
        userDao = userDatabase.userDao();

    }

    public void registerUser(User user) throws Exception {
        new InsertUser(userDao).execute(user).get();
    }

    public User loginUser(String email, String pass) throws Exception {
        return new FetchUser(userDao).execute(email, pass).get();
    }

    public Boolean CheckUser(String email) throws Exception {
        return new CheckUser(userDao).execute(email).get();
    }

    private static class InsertUser extends AsyncTask<User, Void, Void> {
        private final UserDao userDao;

        public InsertUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class FetchUser extends AsyncTask<String, Void, User> {
        private final UserDao userDao;

        public FetchUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.findUser(strings[0], strings[1]);
        }
    }

    private static class CheckUser extends AsyncTask<String, Void, Boolean> {
        private final UserDao userDao;

        public CheckUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            User user = userDao.findUserByEmail(strings[0]);
            return user != null;
        }
    }
}
