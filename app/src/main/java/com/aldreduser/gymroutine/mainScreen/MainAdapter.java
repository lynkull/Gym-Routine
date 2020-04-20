package com.aldreduser.gymroutine.mainScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aldreduser.gymroutine.R;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private static final String TAG = "MainAdapter";
    private ArrayList<String> mSpecificWorkout;
    private Context activityContext;

    public MainAdapter (Context activityContext, ArrayList<String> individualWorkout) {
        this.activityContext = activityContext;
        this.mSpecificWorkout = individualWorkout;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_recycler_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        // what happens to each widget
        Log.d(TAG, "onBindViewHolder: called.");
    }

    @Override
    public int getItemCount() {
        // how many list items are in the recyclerView
        return mSpecificWorkout.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ViewHolder holds the widgets in memory of each entry

        //all the widgets are declared here
        TextView specificWorkoutText;
        EditText set1RepsText, set2RepsText, set3RepsText, set1WeightText, set2WeightText, set3WeightText;
        EditText set4RepsText, set5RepsText, set6RepsText, set4WeightText, set5WeightText, set6WeightText;
        LinearLayout set4Line, set5Line, set6Line;
        Button addSetButton, saveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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

            //todo: onClickListeners go here
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
                        //todo: the toast might not work bc of the context
                        Toast toast = Toast.makeText(activityContext, "Maximum sets reached.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
            //todo: get the data in each input box and save them in shared preference in MainActivity, then in SQLite
        }

        @Override
        public void onClick(View v) {
        }
    }
}
