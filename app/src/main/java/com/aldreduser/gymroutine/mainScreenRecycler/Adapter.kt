package com.aldreduser.gymroutine.mainScreenRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldreduser.gymroutine.R

class Adapter(val nContext: Context,
              val specificWorkout:ArrayList<String>,






              val reps:ArrayList<Int>,
              val weight:ArrayList<Double>): RecyclerView.Adapter<Adapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val callForRow = inflater.inflate(R.layout.extra_recycler_main_item, parent, false)
        return viewHolder(callForRow)
    }

    override fun getItemCount(): Int {
        return specificWorkout.size
    }

    override fun onBindViewHolder(holder: Adapter.viewHolder, position: Int) {

    }

    public class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}