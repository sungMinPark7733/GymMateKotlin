package com.example.gymmatekotlin.data

import com.example.gymmatekotlin.screen.DaysOfWeek

data class User(
    var userEmail: String,
    var userName: String,
    var userGender: String,
    var userAge: Int,
    var userHeight: Int,
    var userWeight: Int,
    var userGoal: String,
    var userDays: List<DaysOfWeek>,
)