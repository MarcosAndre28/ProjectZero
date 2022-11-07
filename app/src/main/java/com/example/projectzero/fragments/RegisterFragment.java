package com.example.projectzero.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentRegisterBinding;
import com.example.projectzero.db.model.User;
import com.example.projectzero.db.viewModel.SignUpViewModel;
import com.example.projectzero.utils.Utilites;


public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private SignUpViewModel signUpViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        init();

        return binding.getRoot();
    }
    private void init(){
        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    registerUser();
                    Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void registerUser() throws Exception {
        signUpViewModel.isExist(binding.editEmail.getText(),toString().trim()).observe(this,aBoolean -> {
            if (aBoolean){
                Utilites.initializeToast(getContext(), "Email ja esta cadastrado").show();
            }else {
                if (    !binding.editEmail.getText().toString().trim().isEmpty() &&
                        !binding.editName.getText().toString().trim().isEmpty() &&
                        !binding.editPassword.getText().toString().trim().isEmpty()){
                    User user = new User(
                            binding.editEmail.getText().toString().trim(),
                            binding.editName.getText().toString().trim(),
                            binding.editPassword.getText().toString().trim());
                    try {
                        signUpViewModel.registerUser(user);
                        Utilites.initializeToast(getContext(), getString(R.string.registration_success));
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}