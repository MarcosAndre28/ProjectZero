package com.example.projectzero.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentLoginBinding;
import com.example.projectzero.fireBase.viewModel.AuthViewModel;
import com.example.projectzero.utils.Utilites;


public class SignInFragment extends Fragment {

    private AuthViewModel authViewModel;
    private FragmentLoginBinding binding;

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

            if (!email.isEmpty() && !password.isEmpty()){
                authViewModel.signIn(email,password);
                Navigation.findNavController(view1).navigate(R.id.action_loginFragment_to_homeFragment);
            }
            else {
                Utilites.initializeToast(getContext(), getString(R.string.invalid_info)).show();
            }
        });

        binding.goRegister.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment));

        return view;
    }

}