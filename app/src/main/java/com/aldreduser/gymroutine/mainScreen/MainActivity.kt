package com.aldreduser.gymroutine.mainScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldreduser.gymroutine.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.extra_recycler_main_item.*

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
    private val NUMB_OF_RECYCLER_VIEWS_SPK = "Workout names array"
    private val SET_REPS_SPK = "Reps of set"        //"$position $SET_REPS_SPK $numOfSet"
    private val SET_WEIGHT_SPK = "Weight of set"

    private var workoutNames:ArrayList<String> = ArrayList()  //will be used to load saved data
    private var numOfWorkouts: Int = 0      // will be used to determine count of recycler items and to load saved data

// maybe put items in an arrayList inside another arrayList to make the code cleaner, to avoid so many variables
    //todo: send these to MainAdapter like it was sent to Adapter. Having an arrayList for each one was probably unnecessary
    private var setRepsArray:ArrayList<ArrayList<Int>> = ArrayList()
    private var setWeightArray:ArrayList<ArrayList<Double>> = ArrayList()
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
            numOfWorkouts++
            makeRecycler()
        }
    }

    private fun makeRecycler() {
        //addRecyclerData()
        val adapter = MainAdapter(this, numOfWorkouts, this)
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
    //handle recycler items click events here
    override fun onSaveClick(position: Int, workoutName: String, setReps: ArrayList<String>, setWeight: ArrayList<String>) {
        //all the data to be saved from the adapter is passed to here as parameters. Are saved under the position
        //todo: setReps.size and setWeight.size might be bugs, hopefully it starts at 1 and ends in setReps.size-1
        val numbOfWorkoutsSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(numbOfWorkoutsSP.edit()) {
            putInt(NUMB_OF_WORKOUTS_SPK, numOfWorkouts)
            commit()
        }
        val workoutNameSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(workoutNameSP.edit()) {
            putString("$position $NUMB_OF_RECYCLER_VIEWS_SPK", workoutName)
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
        //todo: numOfWorkouts might be more than the workouts, ask if it exists when iterating to avoid a crash (or try catch block)

        val numbOfWorkoutsSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
        numOfWorkouts =  numbOfWorkoutsSP.getInt(NUMB_OF_WORKOUTS_SPK, 0)

        for (i in 0 until  numOfWorkouts) {
            //todo: maybe put this in try catch

            val workoutNameSP = this.getPreferences(Context.MODE_PRIVATE) ?: return
            workoutNames.add(workoutNameSP.getString("$i $NUMB_OF_RECYCLER_VIEWS_SPK", "")!!)

            //todo: fill the up with saved data and create a recycler item
            setRepsArray.add()
            setWeightArray.add()

        }





//        addSetButton.setOnClickListener { set4Line.visibility = View.VISIBLE }
//        workoutName.add("Incline Dumbbell")
//        set1Reps.add(3)
//        set2Reps.add(3)
//        set3Reps.add(3)
//        set4Reps.add(3)
//        set5Reps.add(3)
//        set6Reps.add(3)
//        set1Weight.add(25.toDouble())
//        set2Weight.add(30.toDouble())
//        set3Weight.add(35.toDouble())
//        set4Weight.add(35.toDouble())
//        set5Weight.add(35.toDouble())
//        set6Weight.add(35.toDouble())


    }
}

//val thisActSharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
//textBoxToSave.setText(thisActSharedPref.getString(prefFileName, ""))
