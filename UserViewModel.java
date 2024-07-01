package com.example.avoda2;

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.avoda2.api.RandomUserApi
import com.example.avoda2.UserDatabase
import com.example.avoda2.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

private val userDao = UserDatabase.getDatabase(application).userDao()
        val allUsers: LiveData<List<User>> = userDao.getAllUsers()

private val _user = MutableLiveData<User>()
        val user: LiveData<User> = _user

        fun fetchRandomUser() {
        viewModelScope.launch(Dispatchers.IO) {
        try {
        val response = RandomUserApi.retrofitService.getRandomUser()
        if (response.isSuccessful) {
        response.body()?.results?.get(0)?.let { userResult ->
        val user = User(
        id = userResult.login.uuid,
        firstName = userResult.name.first,
        lastName = userResult.name.last,
        age = userResult.dob.age,
        email = userResult.email,
        city = userResult.location.city,
        country = userResult.location.country,
        picture = userResult.picture.large
        )
        _user.postValue(user)
        }
        } else {
        _user.postValue(null)
        }
        } catch (e: Exception) {
        _user.postValue(null)
        }
        }
        }

        fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
        if (userDao.getUserById(user.id) == null) {
        userDao.insert(user)
        }
        }
        }
        }
