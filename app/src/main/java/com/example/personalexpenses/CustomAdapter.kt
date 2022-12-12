package com.example.personalexpenses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class CustomAdapter (private val itemsList: List<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTextView: TextView = view.findViewById<TextView>(R.id.itemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.expense_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}

//class CustomAdapter(private val itemsList: List<String>) :
//    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
//    // Provide a direct reference to each of the views within a data item
//    // Used to cache the views within the item layout for fast access
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val textView: TextView
//
//        init {
//            textView = view.findViewById(R.id.itemTextView)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        // Inflate the custom layout
//        val contactView = inflater.inflate(R.layout.expense_item, parent, false)
//        // Return a new holder instance
//        return ViewHolder(contactView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = itemsList[position]
//        holder.textView.text = item
//    }
//
//    override fun getItemCount(): Int {
//        return itemsList.size
//    }
//
//
//}