package com.aldreduser.gymroutine.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldreduser.gymroutine.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.extra_recycler_main_item.*

/** todo:
 * soon:
 * - user can add workouts (might not work yet)
 * - store user input in boxes
 * - have a navigation bar to the left (says which workout day, nutrition, maxes)
 * - play with top navigation bar
 * - user will chose the set of workouts that are displayed (ie. legs, back, chest, etc)
 * - delete workout
 *
 * mid:
 * - have simple undo functionality after user input
 * - history of reps and weight (and in which set), maxes
 * - cardio section
 *
 * later:
 * - nutrition tracking section
 * - input body weight, calculate each nutrient needed
 *
 * maybe:
 * -maybe work with the calendar
 * -maybe have 2 columns
 * -save user input dynamically
 */

//sets 4 and up are 'gone' by default (should be shown if the user has more than 3 sets in their workout)

class MainActivity : AppCompatActivity() {
    // maybe put items in an arraylist inside another arraylist to make the code cleaner, to avoid so many variables
    private var workoutName:ArrayList<String> = ArrayList()
    private var set1Reps:ArrayList<Int> = ArrayList(); private var set1Weight:ArrayList<Double> = ArrayList()
    private var set2Reps:ArrayList<Int> = ArrayList(); private var set2Weight:ArrayList<Double> = ArrayList()
    private var set3Reps:ArrayList<Int> = ArrayList(); private var set3Weight:ArrayList<Double> = ArrayList()
    private var set4Reps:ArrayList<Int> = ArrayList(); private var set4Weight:ArrayList<Double> = ArrayList()
    private var set5Reps:ArrayList<Int> = ArrayList(); private var set5Weight:ArrayList<Double> = ArrayList()
    private var set6Reps:ArrayList<Int> = ArrayList(); private var set6Weight:ArrayList<Double> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeRecycler()
        addWorkoutButton.setOnClickListener {
            makeRecycler()
        }
    }

    private fun makeRecycler() {
        addRecyclerData()

        //might need to add some of these below and in a different order. Otherwise user input might not go in the right place
        val adapter = Adapter(this, workoutName, set1Reps, set1Weight, set2Reps, set2Weight,
            set3Reps, set3Weight, set4Reps, set4Weight, set5Reps, set5Weight, set6Reps, set6Weight,
            object: Adapter.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    // list item was clicked
                }
            }
        )
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = adapter
    }

    //todo: make data be added by user input
    private fun addRecyclerData() {
        addSetButton.setOnClickListener { set4Line.visibility = View.VISIBLE }
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
