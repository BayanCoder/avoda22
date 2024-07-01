package com.example.avoda2;

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "users")
public class User {
    @PrimaryKey val id: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String,
    val city: String,
    val country: String,
    val picture: String
}
