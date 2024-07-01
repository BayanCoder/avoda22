package com.example.avoda2;

import com.google.gson.annotations.SerializedName;
class RandomUserRespones {
    @SerializedName("results")
    val results: List<UserResult>
}
data class UserResult(
        @SerializedName("name")
        val name: Name,

        @SerializedName("dob")
        val dob: Dob,

        @SerializedName("email")
        val email: String,

        @SerializedName("location")
        val location: Location,

        @SerializedName("picture")
        val picture: Picture,

        @SerializedName("login")
        val login: Login
)

data class Name(
        @SerializedName("first")
        val first: String,

        @SerializedName("last")
        val last: String
)

 class Dob(
        @SerializedName("age")
        val age: Int
)

class Location(
        @SerializedName("city")
        val city: String,

        @SerializedName("country")
        val country: String
)

 class Picture(
        @SerializedName("large")
        val large: String
)

 class Login(
        @SerializedName("uuid")
        val uuid: String
)
