package com.example.projectzero.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectzero.R;
import com.example.projectzero.databinding.FragmentRegisterBinding;
import com.example.projectzero.db.model.User;
import com.example.projectzero.db.viewModel.UserViewModel;


public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    UserViewModel userViewModel;

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

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btRegister.setOnClickListener(v -> {
            String name = binding.editName.getText().toString();
            String address =binding.editaddress.getText().toString();
            String email =binding.editEmail.getText().toString();
            String password =binding.editPassword.toString();

            User user = new User(name,address,email,password);
            userViewModel.insertUser(user);

            Toast.makeText(getContext(), "Registration Success", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment2);
        });


        return binding.getRoot();
    }
}