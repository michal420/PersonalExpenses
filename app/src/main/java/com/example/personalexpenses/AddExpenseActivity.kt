package com.example.personalexpenses

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.personalexpenses.databinding.ActivityAddExpenseBinding
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.Delegates

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenseBinding

    private lateinit var titleText: TextInputLayout
    private lateinit var amountText: TextInputLayout
    private lateinit var colorText: AutoCompleteTextView

    private lateinit var expenseTitle: String
    private var expenseAmount by Delegates.notNull<Double>()
    private lateinit var expenseColor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        // sql lite
        val db = DBHelper(this, null)
        val formatter = DateTimeFormatter.ofPattern("E - dd MMM yyyy - HH:mm")

        // remove the default bar
//        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_expense)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Action bar back button
        // call the action bar
        var actionBar = supportActionBar
        // change default title
        actionBar?.title = "Add expense"
        if (actionBar != null) {
            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        val fab: View = findViewById(R.id.fab_ok)
        fab.setOnClickListener {
            titleText = findViewById(R.id.title_input)
            amountText = findViewById(R.id.amount_input)
            colorText = findViewById(R.id.color_AutoCompleteTextView)
            val currentData = LocalDateTime.now().format(formatter)

            expenseTitle = titleText.editText?.text.toString()
            expenseAmount = amountText.editText?.text.toString().toDoubleOrNull() ?: 0.0
            expenseColor = colorText.text.toString()

            db.addExpense(expenseTitle, expenseAmount, expenseColor, currentData)

            backToMain()
        } // end fab.setOnClickListener

        if (savedInstanceState != null) {
            titleText = findViewById(R.id.title_input)
            val expenseTitle = savedInstanceState?.getString(TITLE_KEY)
            titleText.editText?.setText(expenseTitle)

            amountText = findViewById(R.id.amount_input)
            val expenseAmount = savedInstanceState?.getString(AMOUNT_KEY)
            amountText.editText?.setText(expenseAmount.toString())

            val colorText = findViewById<TextInputLayout>(R.id.color_input)
            val expenseColor = savedInstanceState?.getString(COLOR_KEY)
            colorText.editText?.setText(expenseColor)
        } // end if(savedInstanceState)

    } // end onCreate()

    private fun backToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // Save instance when UI changes (screen rotated)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        titleText = findViewById(R.id.title_input)
        val expenseTitle = titleText.editText?.text.toString()
        outState.putString(Companion.TITLE_KEY, expenseTitle)

        amountText = findViewById(R.id.amount_input)
        val expenseAmount = amountText.editText?.text.toString()
        outState.putString(Companion.AMOUNT_KEY, expenseAmount)

        colorText = findViewById(R.id.color_AutoCompleteTextView)
        val expenseColor = colorText.text.toString()
        outState.putString(COLOR_KEY, expenseColor)
    }

    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val AMOUNT_KEY = "AMOUNT_KEY"
        const val COLOR_KEY = "COLOR_KEY"
    }

    // Press back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}