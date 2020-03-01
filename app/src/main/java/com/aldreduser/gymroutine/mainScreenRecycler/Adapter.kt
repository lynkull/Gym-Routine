package com.aldreduser.gymroutine.mainScreenRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aldreduser.gymroutine.R


// https://stackoverflow.com/questions/24471109/recyclerview-onclick  (for multiple buttons)   'This was so hard for me to have on item click listener in the activity and '


class Adapter(val nContext: Context,
              val specificWorkout:ArrayList<String>,
              val set1Reps:ArrayList<Int>, val set1Weight:ArrayList<Double>,
              val set2Reps:ArrayList<Int>, val set2Weight:ArrayList<Double>,
              val set3Reps:ArrayList<Int>, val set3Weight:ArrayList<Double>,
              val set4Reps:ArrayList<Int>, val set4Weight:ArrayList<Double>,
              val set5Reps:ArrayList<Int>, val set5Weight:ArrayList<Double>,
              val set6Reps:ArrayList<Int>, val set6Weight:ArrayList<Double>,

              val onItemClickListener: OnItemClickListener

              ): RecyclerView.Adapter<Adapter.viewHolder>() {



    // click listener starts here
    // i think mData is the button
    private var mData: ArrayList<String>? = null
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // delete itemsData. this is the example data being passed to the recyclerview
    fun MyRecyclerAdapter(itemsData: ArrayList<String?>?) {
        mOnItemClickListener = onItemClickListener
        mData = itemsData
    }

    // click listener ends here










    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.viewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val callForRow = inflater.inflate(R.layout.extra_recycler_main_item, parent, false)



        // click listener starts here too
        // this might not work because other things were done before the button and listener items, might need to combine them

        val viewHolder = Adapter.viewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.extra_recycler_main_item, parent, false))

        viewHolder.container.setOnClickListener(object : View.OnClickListener {  //container will be efined in MyViewHolder
            override fun onClick(v: View?) {
                mOnItemClickListener!!.onItemClick(v, viewHolder.adapterPosition)
            }
        })
        viewHolder.button.setOnClickListener(object : View.OnClickListener { //button will be efined in MyViewHolder
            override fun onClick(v: View?) {
                //do button click work here with
                // mData.get( viewHolder.getAdapterPosition() );  mdata is the demo data passed
            }
        })

        // up to here ^^^^^^^



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

    class MyViewHolder(var container: View) : ViewHolder(container) {
        var button: Button

        init {
            button = container.findViewById<View>(R.id.button) as Button
        }
    }
}
