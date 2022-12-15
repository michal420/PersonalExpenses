package com.example.personalexpenses

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.icu.util.Currency
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class RecyclerAdapter(private val itemsList: List<Expense>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val currency = Currency.getInstance("EUR")
    val df = DecimalFormat("#.##")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemDate: TextView
        val itemTitle: TextView
        val itemAmount: TextView
        val cardView: CardView

        init {
            itemDate = itemView.findViewById(R.id.date_text_view)
            itemTitle = itemView.findViewById(R.id.title_text_view)
            itemAmount = itemView.findViewById(R.id.amount_text_view)
            cardView = itemView.findViewById(R.id.card_view)
        } // end init

    } // end inner class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate card layout and pass the view object to constructor
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val colors = mapOf(
            "Red" to "#ff4d4d",
            "Green" to "#2d8659",
            "Blue" to "#007acc",
            "Purple" to "#b30086"
        )

        holder.itemTitle.text = itemsList[position].title
        holder.itemAmount.text = currency.symbol + df.format(itemsList[position].amount)
        holder.itemDate.text = itemsList[position].dateTime
        holder.cardView.setCardBackgroundColor(Color.parseColor(colors[ itemsList[position].color.trim()]))
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}