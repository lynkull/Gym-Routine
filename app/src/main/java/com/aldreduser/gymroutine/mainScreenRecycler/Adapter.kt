package com.aldreduser.gymroutine.mainScreenRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldreduser.gymroutine.R
import kotlinx.android.synthetic.main.extra_recycler_main_item.view.*
import kotlinx.android.synthetic.main.extra_recycler_main_item.*

class Adapter(val nContext: Context,
              val specificWorkout:ArrayList<String>,
              val set1Reps:ArrayList<Int>, val set1Weight:ArrayList<Double>,
              val set2Reps:ArrayList<Int>, val set2Weight:ArrayList<Double>,
              val set3Reps:ArrayList<Int>, val set3Weight:ArrayList<Double>,
              val set4Reps:ArrayList<Int>, val set4Weight:ArrayList<Double>,
              val set5Reps:ArrayList<Int>, val set5Weight:ArrayList<Double>,
              val set6Reps:ArrayList<Int>, val set6Weight:ArrayList<Double>): RecyclerView.Adapter<Adapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val callForRow = inflater.inflate(R.layout.extra_recycler_main_item, parent, false)
        return viewHolder(callForRow)
    }

    override fun getItemCount(): Int {
        return specificWorkout.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.itemView.specificWorkoutText.text = specificWorkout.get(position)
        holder.itemView.set1RepsText.setText(set1Reps.get(position).toString())
        holder.itemView.set1WeightText.setText(set1Weight.get(position).toString()) //make widgets take decimals in the layout
        holder.itemView.set2RepsText.setText(set2Reps.get(position).toString())
        holder.itemView.set2WeightText.setText(set2Weight.get(position).toString())
        holder.itemView.set3RepsText.setText(set3Reps.get(position).toString())
        holder.itemView.set3WeightText.setText(set3Weight.get(position).toString())
        holder.itemView.set4RepsText.setText(set4Reps.get(position).toString())
        holder.itemView.set4WeightText.setText(set4Weight.get(position).toString())
        holder.itemView.set5RepsText.setText(set5Reps.get(position).toString())
        holder.itemView.set5WeightText.setText(set5Weight.get(position).toString())
        holder.itemView.set6RepsText.setText(set6Reps.get(position).toString())
        holder.itemView.set6WeightText.setText(set6Weight.get(position).toString())
    }

    public class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        viewHolder
    }
}