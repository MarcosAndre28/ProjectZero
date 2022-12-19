package com.example.projectzero.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentLoginBinding;
import com.example.projectzero.db.model.User;
import com.example.projectzero.db.viewModel.AuthViewModel;
import com.example.projectzero.utils.Utilites;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SignInFragment extends Fragment {

    private FragmentLoginBinding binding;
    private com.example.projectzero.db.viewModel.AuthViewModel authViewModel;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.btLogin.setOnClickListener(view1 -> {
            String email = binding.inputEmail.getText().toString();
            String password = binding.inputPassword.getText().toString();

            if (email.isEmpty() && password.isEmpty()){
                Utilites.showShort(getContext(), getString(R.string.invalid_info));

            }
            else {
                try {
                    authViewModel.login(email,password)
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
                                        Utilites.showShort(getContext(),getString(R.string.login));
                                        Navigation.findNavController(view1).navigate(R.id.action_loginFragment_to_homeFragment);

                                    } else {
                                        // Usuário não existe no banco de dados
                                        Utilites.showShort(getContext(), "Usuario não esta cadastrado");
                                        try {

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.goRegister.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment));

        return view;
    }

}