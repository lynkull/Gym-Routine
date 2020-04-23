package com.aldreduser.gymroutine.mainScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldreduser.gymroutine.R
import kotlinx.android.synthetic.main.activity_main.*

//everything will be saved in sharedPreferences under the position in the recyclerview

/** todo:
 * soon:
 * - when a new view is created, user has to input the name of the workout in a popup
 * - user can add workouts (might not work yet)
 * - store user input in boxes
 * - have a navigation bar to the left (says which workout day, nutrition, maxes)
 * - play with top navigation bar
 * - user will chose the set of workouts that are displayed (ie. legs, back, chest, etc)
 * - delete workout
 * - probably have a table layout in each item
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

class MainActivity : AppCompatActivity(), MainAdapter.OnSaveButListener {

    //Shared Preferences Keys ("$position $key")
    private val NUMB_OF_RECYCLER_VIEWS_SPK = "Number of recycler views"
    private val SET_REPS_SPK = "Reps of set"        //"$position $SET_REPS_SPK $numOfSet"
    private val SET_WEIGHT_SPK = "Weight of set"

    private var workoutNames:ArrayList<String> = ArrayList()

// maybe put items in an arrayList inside another arrayList to make the code cleaner, to avoid so many variables
//    private var set1Reps:ArrayList<Int> = ArrayList(); private var set1Weight:ArrayList<Double> = ArrayList()
//    private var set2Reps:ArrayList<Int> = ArrayList(); private var set2Weight:ArrayList<Double> = ArrayList()
//    private var set3Reps:ArrayList<Int> = ArrayList(); private var set3Weight:ArrayList<Double> = ArrayList()
//    private var set4Reps:ArrayList<Int> = ArrayList(); private var set4Weight:ArrayList<Double> = ArrayList()
//    private var set5Reps:ArrayList<Int> = ArrayList(); private var set5Weight:ArrayList<Double> = ArrayList()
//    private var set6Reps:ArrayList<Int> = ArrayList(); private var set6Weight:ArrayList<Double> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeRecycler()
        addWorkoutButton.setOnClickListener {
            makeRecycler()
        }
    }

    private fun makeRecycler() {
        //addRecyclerData()
        val adapter = MainAdapter(this, workoutNames, this)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = adapter

//        val adapter = Adapter(this, workoutName, set1Reps, set1Weight, set2Reps, set2Weight,
//            set3Reps, set3Weight, set4Reps, set4Weight, set5Reps, set5Weight, set6Reps, set6Weight,
//            object: Adapter.OnItemClickListener {
//                override fun onItemClick(view: View?, position: Int) {
//                    // list item was clicked
//                }
//            }
//        )
//        mainRecyclerView.layoutManager = LinearLayoutManager(this)
//        mainRecyclerView.adapter = adapter
    }

    //todo: handle save data onLick events from MainAdapter here (look at best practice way click listener youtube video)
    override fun onSaveClick(position: Int, setReps: ArrayList<String>, setWeight: ArrayList<String>) {
        //todo: handle click events here
        //pass all the data to be saved from the adapter to here as parameters. Save the under the position

        //todo: setReps.size and setWeight.size might be bugs, hopefully it starts at 0 and ends in setReps.size-1
        for (i in 0..setReps.size) {
            //save
            val numOfSet = i+1
            val repsSharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with(repsSharedPref.edit()) {
                putString("$position $SET_REPS_SPK $numOfSet", setReps[i])
                commit()
            }
        }
        for (i in 0..setWeight.size) {
            //save
            val numOfSet = i+1
            val weightSharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with(weightSharedPref.edit()) {
                putString("$position $SET_WEIGHT_SPK $numOfSet", setWeight[i])
                commit()
            }
        }

        //addRecyclerData()   //display the date from memory (maybe)
    }


    //todo: load the saved data like this
//    // make data be added by user input
//    private fun addRecyclerData() {
//        addSetButton.setOnClickListener { set4Line.visibility = View.VISIBLE }
//        workoutName.add("Incline Dumbbell")
//        set1Reps.add(3)
//        set1Weight.add(25.toDouble())
//        set2Reps.add(3)
//        set2Weight.add(30.toDouble())
//        set3Reps.add(3)
//        set3Weight.add(35.toDouble())
//        set4Reps.add(3)
//        set4Weight.add(35.toDouble())
//        set5Reps.add(3)
//        set5Weight.add(35.toDouble())
//        set6Reps.add(3)
//        set6Weight.add(35.toDouble())
//    }
}

//val thisActSharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
//textBoxToSave.setText(thisActSharedPref.getString(prefFileName, ""))
