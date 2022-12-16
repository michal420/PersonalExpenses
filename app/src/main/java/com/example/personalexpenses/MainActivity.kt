package com.example.personalexpenses

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.util.Currency
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalexpenses.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private val currency = Currency.getInstance("EUR")
    private val df = DecimalFormat("#.##")

    // sql lite
    private lateinit var db: DBHelper
    private lateinit var binding: ActivityMainBinding
    private val expensesList = mutableListOf<Expense>()

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        // remove the default bar
//        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this, null)
        // Read from DB
        val adapterList = readFromDB()

        // Recycler View
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter(adapterList)
        recyclerView.adapter = adapter

        // Calculate total spending
        calculateTotalSpending(adapterList)

//        db.deleteAll()

        // FAB
        val fab: View = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            openAddExpenseActivity()
        } // end fab.setOnClickListener

    } // end onCreate

    private fun openAddExpenseActivity() {
        val intent = Intent(this, AddExpenseActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("Range")
    private fun readFromDB(): List<Expense> {
        // Create db object
        val cursor = db.getExpense()

        if (cursor != null && cursor.count > 0) {
            cursor!!.moveToFirst()

            val t = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITLE)) + "\n"
            val a = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AMOUNT)) + "\n"
            val c = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_COLOR)) + "\n"
            val d = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE)) + "\n"

            val e = Expense(t, a.toDouble(), c, d)
            expensesList.add(e)

            // move cursor to next value and append it
            while (cursor.moveToNext()) {

                val t = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITLE)) + "\n"
                val a = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AMOUNT)) + "\n"
                val c = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_COLOR)) + "\n"
                val d = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE)) + "\n"
                val e = Expense(t, a.toDouble(), c, d)
                expensesList.add(e)

            }
        }
        return expensesList
    } // end readFromDB

    private fun calculateTotal(list: List<Expense>): Double {
        return list.sumOf { it.amount }
    }

    private fun calculateTotalSpending(adapterList: List<Expense>) {
        val totalTextView = findViewById<TextView>(R.id.total_text_view)
        val total = calculateTotal(adapterList)
        totalTextView.append(currency.symbol + df.format(total))
    }

}