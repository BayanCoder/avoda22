package com.example.avoda2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.avoda2.model.User;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private MainActivity binding;
    private UserActivity userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivity.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserActivity.class);

        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    updateUI(user);
                } else {
                    showError();
                }
            }
        });

        binding.seeNextUserButton.setOnClickListener(v -> {
            userViewModel.fetchRandomUser();
        });

        binding.seeNextUserButton.setOnClickListener(v -> {
            User user = userViewModel.getUser().getValue();
            if (user != null && !"error".equals(user.getId())) {
                userViewModel.addUser(user);
                Toast.makeText(this, "User added to collection", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cannot add user, current user is invalid", Toast.LENGTH_SHORT).show();
            }
        });

        binding.viewCollectionButton.setOnClickListener(v -> {
            startActivity(new Intent(this, UserActivity.class));
        });

        userViewModel.fetchRandomUser();
    }

    private static MainActivity inflate(LayoutInflater layoutInflater) {
        return null;
    }

    private void updateUI(User user) {
        binding.firstNameTextView.setText(user.getFirstName());
        binding.lastNameTextView.setText(user.getLastName());
        binding.ageTextView.setText(String.valueOf(user.getAge()));
        binding.emailTextView.setText(user.getEmail());
        binding.cityTextView.setText(user.getCity());
        binding.countryTextView.setText(user.getCountry());
        Picasso.get().load(user.getPicture()).into(binding.userImageView);
    }

    private void showError() {
        binding.firstNameTextView.setText("error");
        binding.lastNameTextView.setText("error");
        binding.ageTextView.setText("error");
        binding.emailTextView.setText("error");
        binding.cityTextView.setText("error");
        binding.countryTextView.setText("error");
        binding.userImageView.setImageResource(R.drawable.ic_error);
    }
}
