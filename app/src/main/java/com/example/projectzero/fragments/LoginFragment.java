package com.example.projectzero.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentLoginBinding;
import com.example.projectzero.db.viewModel.SignInViewModel;
import com.example.projectzero.utils.Utilites;


public class LoginFragment extends Fragment {

    private SignInViewModel signInViewModel;

    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        init();

        binding.goRegister.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment));

        return binding.getRoot();
    }

    private void init() {
        binding.btLogin.setOnClickListener(v -> {
            String email = binding.inputEmail.getText().toString();
            String password = binding.inputPassword.getText().toString();

            signInViewModel.signInUser(email,password);

            signInViewModel.checkSignIn().observe(getViewLifecycleOwner(), aBoolean -> {
                if (aBoolean){
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);
                }else {
                    Utilites.initializeToast(getContext(), getString(R.string.invalid_info)).show();
                }
            });
        });
    }
}