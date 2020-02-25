package com.aldreduser.gymroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldreduser.gymroutine.mainScreenRecycler.Adapter
import kotlinx.android.synthetic.main.activity_main.*

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
    private var workoutName:ArrayList<String> = ArrayList()
    private var set1Reps:ArrayList<Int> = ArrayList()
    private var set1Weight:ArrayList<Double> = ArrayList()
    private var set2Reps:ArrayList<Int> = ArrayList()
    private var set2Weight:ArrayList<Double> = ArrayList()
    private var set3Reps:ArrayList<Int> = ArrayList()
    private var set3Weight:ArrayList<Double> = ArrayList()
    private var set4Reps:ArrayList<Int> = ArrayList()
    private var set4Weight:ArrayList<Double> = ArrayList()
    private var set5Reps:ArrayList<Int> = ArrayList()
    private var set5Weight:ArrayList<Double> = ArrayList()
    private var set6Reps:ArrayList<Int> = ArrayList()
    private var set6Weight:ArrayList<Double> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        // todo: maybe put items in an arraylist inside another arraylist to make the code cleaner, to avoid so many variables
        val adapter = Adapter(this) // need to send the rest of the arraylists

        mainRecycler.layoutManager = LinearLayoutManager(this)
        mainRecycler.adapter = adapter
    }

    private fun loadData() {
        itemsToLoad()
        itemsToLoad()
        itemsToLoad()
        itemsToLoad()
        itemsToLoad()
    }

    // make data added by user input
    private fun itemsToLoad() {
        workoutName.add("Incline Dumbell")
        set1Reps.add(3)
        set1Weight.add(25.toDouble())
        set2Reps.add(3)
        set2Weight.add(30.toDouble())
        set3Reps.add(3)
        set3Weight.add(35.toDouble())
        set4Reps.add(3)
        set4Weight.add(35.toDouble())
        set5Reps.add(3)
        set5Weight.add(35.toDouble())
        set6Reps.add(3)
        set6Weight.add(35.toDouble())
    }
}
