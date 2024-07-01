package com.example.avoda2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import androidx.activity.avoda2
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avoda2.databinding.ActivityUsersBinding
import com.example.avoda2.model.User

public class UserActivity extends AppCompatActivity {
    private lateinit var binding: ActivityUsersBinding
    private val userViewModel: UserViewModel by viewModels()

    @Override    
    protected void onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter()
        binding.usersRecyclerView.adapter = adapter

        userViewModel.allUsers.observe(this, Observer { users ->
                users?.let { adapter.submitList(it) }
        })
    }

    public void fetchRandomUser() {
        
    }
}


