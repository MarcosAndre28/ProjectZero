package com.example.projectzero.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentRegisterBinding;
import com.example.projectzero.db.model.User;
import com.example.projectzero.db.viewModel.AuthViewModel;
import com.example.projectzero.utils.Utils;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SignUpFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private com.example.projectzero.db.viewModel.AuthViewModel authViewModel;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btRegister.setOnClickListener(view1 -> {
            String email = binding.editEmail.getText().toString();
            String password = binding.editPassword.getText().toString();
            String name = binding.editName.getText().toString();

            if (name.isEmpty() && email.isEmpty() && password.isEmpty()) {
                Toast.makeText(getContext(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
            } else {
                authViewModel.userExists(email)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MaybeObserver<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onSuccess(Boolean exists) {
                                Log.d("TAG", "Valor de exists: " + exists);
                                if (exists) {
                                    Utils.showShort(getContext(), "Usuario ja cadastrado");
                                } else {
                                    try {
                                        String passwordSh = Utils.hashPassword(password);
                                        User user = new User(name, email, passwordSh);
                                        authViewModel.registerUser(user)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new CompletableObserver() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {
                                                    }

                                                    @Override
                                                    public void onComplete() {
                                                        Utils.showShort(getContext(), getString(R.string.registration_success));
                                                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment2);
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("USER_EXISTS", "onError called with error = " + e.getMessage());
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.d("USER_EXISTS", "onComplete called");
                            }
                        });
            }
        });

        return view;
    }

}