package com.aldreduser.gymroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//have a navigation bar to the left (says which workout day)
//recyclerview says what to display in the main screen

/*
each item:
-name of workout
-history of reps and weight (and in which set)
-history of maxes
-current reps and weight (and in which set)

nutrition tracking section
-input body weight, calculate each nutrient

maybe work with the calendar

user can add more sets

have simple undo functionality after user input
 */

// sets 4 and up are 'gone' by default (should be shown if the user has more than 3 sets in their workout)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
