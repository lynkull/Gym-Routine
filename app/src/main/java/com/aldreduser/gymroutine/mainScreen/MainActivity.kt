package com.aldreduser.gymroutine.mainScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldreduser.gymroutine.R
import kotlinx.android.synthetic.main.activity_main.*

//everything will be saved in sharedPreferences under the position in the recyclerview

/** todo:
 * soon:
 * - when a new view is created, user has to input the name of the workout in a popup
 * - user can add workouts (might not work yet)
 * - store user input in boxes
 * - delete workout
 * - user will chose the set of workouts that are displayed (ie. legs, back, chest, etc)
 * - have a navigation bar to the left (says which workout day, nutrition, maxes)
 * - play with top navigation bar
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
 * - probably have a table layout in each item
 * -maybe work with the calendar
 * -save user input dynamically
 */

//sets 4 and up are 'gone' by default (should be shown if the user has more than 3 sets in their workout)

class MainActivity : AppCompatActivity(), MainAdapter.OnSaveButListener {

    //Shared Preferences Keys ("$position $key")
    private val NUMB_OF_WORKOUTS_SPK = "Number of workouts"
    private val NAMES_OF_WORKOUTS_SPK = "Workout names"
    private val WORKOUT_SETS_SPK = "Workout Sets"   //to iterate through the sets when loading from storage
    private val SET_REPS_SPK = "Reps of set"        //"$position $SET_REPS_SPK $numOfSet"
    private val SET_WEIGHT_SPK = "Weight of set"

    private var workoutNames:ArrayList<String> = ArrayList()  //will be used to load saved data
    private var numOfWorkouts: Int = 0      // will be used to determine count of recycler items and to load saved data

// maybe put items in an arrayList inside another arrayList to make the code cleaner, to avoid so many variables
    //todo: send these to MainAdapter like it was sent to Adapter. Having an arrayList for each one was probably unnecessary
    private var setRepsArray:ArrayList<ArrayList<String>> = ArrayList()
    private var setWeightArray:ArrayList<ArrayList<String>> = ArrayList()
//    private var set1Reps:ArrayList<Int> = ArrayList(); private var set1Weight:ArrayList<Double> = ArrayList()
//    private var set2Reps:ArrayList<Int> = ArrayList(); private var set2Weight:ArrayList<Double> = ArrayList()
//    private var set3Reps:ArrayList<Int> = ArrayList(); private var set3Weight:ArrayList<Double> = ArrayList()
//    private var set4Reps:ArrayList<Int> = ArrayList(); private var set4Weight:ArrayList<Double> = ArrayList()
//    private var set5Reps:ArrayList<Int> = ArrayList(); private var set5Weight:ArrayList<Double> = ArrayList()
//    private var set6Reps:ArrayList<Int> = ArrayList(); private var set6Weight:ArrayList<Double> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRecyclersData()
        makeRecycler()
        // setRepsArray.clear(); setWeightArray.clear() //todo: check to see if this will not break the code
        addWorkoutButton.setOnClickListener {
            numOfWorkouts++
            makeRecycler()
        }
    }

    private fun makeRecycler() {
        //addRecyclerData()
        val adapter = MainAdapter(this, numOfWorkouts, this, setRepsArray, setWeightArray)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = adapter
    }
    //handle recycler items click events here
    override fun onSaveClick(position: Int, workoutName: String, setReps: ArrayList<String>, setWeight: ArrayList<String>) {
        //all the data to be saved from the adapter is passed to here as parameters. Are saved under the position
        //todo: setReps.size and setWeight.size might be bugs, hopefully it starts at 1 and ends in setReps.size-1
        //todo: pop up ask if user is sure they want to overrride the data
        val numbOfWorkoutsSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(numbOfWorkoutsSP.edit()) {
            putInt(NUMB_OF_WORKOUTS_SPK, numOfWorkouts)
            commit()
        }
        val workoutNameSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(workoutNameSP.edit()) {
            putString("$position $NAMES_OF_WORKOUTS_SPK", workoutName)
            commit()
        }
        val workoutSets = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(workoutSets.edit()) {
            putInt("$position $WORKOUT_SETS_SPK", setReps.size)
            commit()
        }
        for (i in 0 until setReps.size) {
            //save
            val numOfSet = i+1
            val repsSharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with(repsSharedPref.edit()) {
                putString("$position $SET_REPS_SPK $numOfSet", setReps[i])
                commit()
            }
        }
        for (i in 0 until setWeight.size) {
            //save
            val numOfSet = i+1
            val weightSharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with(weightSharedPref.edit()) {
                putString("$position $SET_WEIGHT_SPK $numOfSet", setWeight[i])
                commit()
            }
        }
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
    }

    //load the saved data like this
    private fun loadRecyclersData() {
        //todo: possible bug: numOfWorkouts might be more than the workouts, ask if it exists when iterating to avoid a crash (or try catch block)

        val numbOfWorkoutsSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
        numOfWorkouts =  numbOfWorkoutsSP.getInt(NUMB_OF_WORKOUTS_SPK, 0)

        for (i in 0 until numOfWorkouts) {
            //maybe put this in try catch

            val nestedSetRepsArray: ArrayList<String> = ArrayList()
            val nestedSetWeightArray: ArrayList<String> = ArrayList()

            //get the saved name of the specific workout
            val workoutNameSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
            val tempWorkoutName =  workoutNameSP.getString("$i $NAMES_OF_WORKOUTS_SPK", "")
            if (tempWorkoutName!!.isNotEmpty()){ workoutNames.add(tempWorkoutName) }
            //make a loop of the sets in that specific workout
            val workoutSetsSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
            val tempWorkoutSets =  workoutSetsSP.getInt("$i $WORKOUT_SETS_SPK", 0)
            for (set in 0 until tempWorkoutSets) {
                //add the set reps from the specific workout to nestedSetRepsArray
                val workoutRepsSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
                val tempWorkoutReps =  workoutRepsSP.getString("$i $SET_REPS_SPK $set", "")
                if(tempWorkoutReps!!.isNotEmpty()){ nestedSetRepsArray.add(tempWorkoutReps) }

                //add the set weight from the specific workout to nestedSetWeightArray
                val workoutWeightSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
                val tempWorkoutWeight =  workoutWeightSP.getString("$i $SET_REPS_SPK $set", "")
                if(tempWorkoutWeight!!.isNotEmpty()){ nestedSetWeightArray.add(tempWorkoutWeight) }
            }
            //add the reps and weight of sets in each workout to the array where the rest of the workouts are
            setRepsArray.add(nestedSetRepsArray)
            setWeightArray.add(nestedSetWeightArray)

            //todo: send the arrays to the MainAdapter
        }
    }
}
