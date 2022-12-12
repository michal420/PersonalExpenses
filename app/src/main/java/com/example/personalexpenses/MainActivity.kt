package com.example.personalexpenses

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalexpenses.databinding.ActivityAddExpenseBinding
import com.example.personalexpenses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Room library (DB/sql lite) vs Shared Preferences (txt)

    private lateinit var binding: ActivityMainBinding
    private val key = "expenses"
    private val expensesList = ArrayList<String>()

    //    private val expensesList = ArrayList<String>()
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        // remove the default bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val expense = intent.getSerializableExtra(key) as Expenses?
        val newColor: String = expense?.color.toString()

        if (expense != null) {
            expensesList.add(expense.title + " " + expense.amount + " " + expense.color + " " + expense.dateTime)
        }
//        Log.d("myTag", "!!!! EXP: ${expense?.title} / ${expense?.amount} / ${expense?.color}")

        // Recycler View
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        adapter = CustomAdapter(expensesList)

//        recyclerView.setBackgroundColor(ContextCompat.getColor(this, R.color.newColor)
//        recyclerView.setBackgroundColor(Color.parseColor(newColor))


        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()


//        val card: CardView = findViewById(R.id.cardView)
//        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.myColor))
//        card.setCardBackgroundColor((Color.parseColor(color)))

        // FAB
        val fab: View = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            openActivity()
        } // end fab.setOnClickListener

    } // end onCreate

    private fun openActivity() {
        val intent = Intent(this, AddExpenseActivity::class.java)
        startActivity(intent)
    }

}