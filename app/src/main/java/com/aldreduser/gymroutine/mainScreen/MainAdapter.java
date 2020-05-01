package com.aldreduser.gymroutine.mainScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aldreduser.gymroutine.R;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private OnSaveButListener mOnSaveButListener;
    private static final String TAG = "MainAdapter";
    private Integer numOfWorkouts;
    private Context activityContext;
    private String workoutName;
    private ArrayList<String> setReps = new ArrayList<>(), setWeight = new ArrayList<>(); //this will be used to send data to the Activity to save it
    private ArrayList<ArrayList<String>> setRepsArray, setWeightArray;
    private ArrayList<String>workoutNames; //this is used to load the names

    public MainAdapter (Context activityContext, Integer numOfWorkouts, OnSaveButListener onSaveButListener,
                        ArrayList<ArrayList<String>> setRepsArray, ArrayList<ArrayList<String>> setWeightArray, ArrayList<String> workoutNames) {
        this.activityContext = activityContext;
        this.numOfWorkouts = numOfWorkouts;
        this.mOnSaveButListener = onSaveButListener;
        this.setRepsArray = setRepsArray;
        this.setWeightArray = setWeightArray;
        this.workoutNames = workoutNames;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_recycler_main_item, parent, false);
        return new ViewHolder(view, mOnSaveButListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        // what happens to each widget
        Log.d(TAG, "onBindViewHolder: called.");

        //I could probably load items from storage in viewHolder but its probably more resource efficient here)
        //Title, reps and weights widgets, to write and save data
        EditText specificWorkoutText = holder.itemView.findViewById(R.id.specificWorkoutText),
        set1RepsText = holder.itemView.findViewById(R.id.set1RepsText),
        set2RepsText = holder.itemView.findViewById(R.id.set2RepsText),
        set3RepsText = holder.itemView.findViewById(R.id.set3RepsText),
        set4RepsText = holder.itemView.findViewById(R.id.set4RepsText),
        set5RepsText = holder.itemView.findViewById(R.id.set5RepsText),
        set6RepsText = holder.itemView.findViewById(R.id.set6RepsText),
        set1WeightText = holder.itemView.findViewById(R.id.set1WeightText),
        set2WeightText = holder.itemView.findViewById(R.id.set2WeightText),
        set3WeightText = holder.itemView.findViewById(R.id.set3WeightText),
        set4WeightText = holder.itemView.findViewById(R.id.set4WeightText),
        set5WeightText = holder.itemView.findViewById(R.id.set5WeightText),
        set6WeightText = holder.itemView.findViewById(R.id.set6WeightText);
        try {
            //fill the title
            specificWorkoutText.setText(workoutNames.get(position));
            //fill the reps
            //todo: i think the problem is here (with the nested arrayLists)
            // use position
            set1RepsText.setText(setRepsArray.get(position).get(0));
            set2RepsText.setText(setRepsArray.get(position).get(1));
            set3RepsText.setText(setRepsArray.get(position).get(2));
            set4RepsText.setText(setRepsArray.get(position).get(3));
            set5RepsText.setText(setRepsArray.get(position).get(4));
            set6RepsText.setText(setRepsArray.get(position).get(5));
            //fill the weight
            set1WeightText.setText(setWeightArray.get(position).get(0));
            set2WeightText.setText(setWeightArray.get(position).get(1));
            set3WeightText.setText(setWeightArray.get(position).get(2));
            set4WeightText.setText(setWeightArray.get(position).get(3));
            set5WeightText.setText(setWeightArray.get(position).get(4));
            set6WeightText.setText(setWeightArray.get(position).get(5));
        } catch (IndexOutOfBoundsException e) {Log.d(TAG, "error: Probably no data to load.");}
    }

    @Override
    public int getItemCount() {
        // how many list items are in the recyclerView
        return numOfWorkouts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ViewHolder holds the widgets in memory of each entry

        //click listener to senc to activity that will handle this event
        OnSaveButListener onSaveButListener;
        //all the widgets are declared here
        EditText specificWorkoutText;
        EditText set1RepsText, set2RepsText, set3RepsText, set4RepsText, set5RepsText, set6RepsText;
        EditText set1WeightText, set2WeightText, set3WeightText, set4WeightText, set5WeightText, set6WeightText;
        LinearLayout set4Line, set5Line, set6Line;
        Button addSetButton, saveButton;

        public ViewHolder(@NonNull View itemView, OnSaveButListener onSaveButListener) {
            super(itemView);
            this.onSaveButListener = onSaveButListener;

            specificWorkoutText = itemView.findViewById(R.id.specificWorkoutText);
            //reps and weights widgets, to write and save data
            set1RepsText = itemView.findViewById(R.id.set1RepsText);
            set2RepsText = itemView.findViewById(R.id.set2RepsText);
            set3RepsText = itemView.findViewById(R.id.set3RepsText);
            set4RepsText = itemView.findViewById(R.id.set4RepsText);
            set5RepsText = itemView.findViewById(R.id.set5RepsText);
            set6RepsText = itemView.findViewById(R.id.set6RepsText);
            set1WeightText = itemView.findViewById(R.id.set1WeightText);
            set2WeightText = itemView.findViewById(R.id.set2WeightText);
            set3WeightText = itemView.findViewById(R.id.set3WeightText);
            set4WeightText = itemView.findViewById(R.id.set4WeightText);
            set5WeightText = itemView.findViewById(R.id.set5WeightText);
            set6WeightText = itemView.findViewById(R.id.set6WeightText);
            //extra sets. To make them show when user clicks on button. They are hidden by default.
            set4Line = itemView.findViewById(R.id.set4Line);
            set5Line = itemView.findViewById(R.id.set5Line);
            set6Line = itemView.findViewById(R.id.set6Line);
            // add set button. To add more set widgets
            addSetButton = itemView.findViewById(R.id.addSetButton);
            saveButton = itemView.findViewById(R.id.saveButton);

            //onClickListeners go here
            //if this view is 'gone', make it 'visible'
            addSetButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (set4Line.getVisibility() == View.GONE){
                        set4Line.setVisibility(View.VISIBLE);
                    } else if (set5Line.getVisibility() == View.GONE){
                        set5Line.setVisibility(View.VISIBLE);
                    } else if (set6Line.getVisibility() == View.GONE){
                        set6Line.setVisibility(View.VISIBLE);
                    } else {
                        //todo: possible bug: the toast might not work bc of the context being passed
                        Toast toast = Toast.makeText(activityContext, "Maximum sets reached.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });

            //gets the data in each input box and saves it in shared preference in MainActivity, then in SQLite
            saveButton.setOnClickListener(this);
        }

        private void putUserInputInArrays() {
            workoutName = specificWorkoutText.getText().toString();
            setReps.add(set1RepsText.getText().toString());
            setReps.add(set2RepsText.getText().toString());
            setReps.add(set3RepsText.getText().toString());
            setReps.add(set4RepsText.getText().toString());
            setReps.add(set5RepsText.getText().toString());
            setReps.add(set6RepsText.getText().toString());
            setWeight.add(set1WeightText.getText().toString());
            setWeight.add(set2WeightText.getText().toString());
            setWeight.add(set3WeightText.getText().toString());
            setWeight.add(set4WeightText.getText().toString());
            setWeight.add(set5WeightText.getText().toString());
            setWeight.add(set6WeightText.getText().toString());
        }

        @Override
        public void onClick(View v) {
            putUserInputInArrays();
            onSaveButListener.onSaveClick(getAdapterPosition(), workoutName, setReps, setWeight);
        }
    }
    public interface OnSaveButListener {
        //this interface was made so that the activity class can handle onClickListeners from here
        void onSaveClick(int position, String workoutName, ArrayList<String> setReps, ArrayList<String> setWeight);
    }
}