package com.aldreduser.gymroutine.mainScreenRecycler

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(val nContext: Context, val sets:ArrayList<Int>, val reps:ArrayList<Int>, val weight:ArrayList<Double>): RecyclerView.Adapter<Adapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.viewHolder {
    }

    override fun getItemCount(): Int {
    }

    override fun onBindViewHolder(holder: Adapter.viewHolder, position: Int) {
    }

    public class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}