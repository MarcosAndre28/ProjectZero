package com.example.projectzero.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentRegisterBinding;
import com.example.projectzero.fireBase.viewModel.AuthViewModel;
import com.example.projectzero.utils.Utilites;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private AuthViewModel authViewModel;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

    /*    authViewModel.getUserData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                navController.navigate(R.id.action_registerFragment_to_loginFragment2);
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View  view = binding.getRoot();



        binding.btRegister.setOnClickListener(view1 -> {
            String email = binding.editEmail.getText().toString();
            String password = binding.editPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()){
                authViewModel.register(email,password);
                Utilites.initializeToast(getContext(), getString(R.string.registration_success)).show();
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment2);
            }
        });


        return view;
    }

}