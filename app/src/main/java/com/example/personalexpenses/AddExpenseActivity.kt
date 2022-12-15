package com.example.personalexpenses

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalexpenses.databinding.ActivityAddExpenseBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding

    private lateinit var titleText: TextInputLayout
    private lateinit var amountText: TextInputLayout

    var title = ""
    var amount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        // sql lite
        val db = DBHelper(this, null)
        val formatter = DateTimeFormatter.ofPattern("E - dd MMM yyyy - HH:mm")

        // remove the default bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_expense)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Top app bar
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar.setNavigationOnClickListener {
            backToMain()
        }

        // Input from title text field
        titleText = findViewById(R.id.title_input)
        // Input from amount text field
        amountText = findViewById(R.id.amount_input)
        // Color selected from the Color Menu
        val selectedValue = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val fab: View = findViewById(R.id.fab_ok)
        fab.setOnClickListener {
            title = titleText.editText?.text.toString()
            amount = amountText.editText?.text.toString().toDouble()
            val color = selectedValue.text.toString()
            val currentData = LocalDateTime.now().format(formatter)

            db.addExpense(title, amount.toString(), color, currentData)

            backToMain()
        } // end fab.setOnClickListener

    } // end onCreate()

    private fun backToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        title = titleText.editText?.text.toString()
        outState.putString(Companion.TITLE_KEY, title)
        amount = amountText.editText?.text.toString().toDouble()
        outState.putDouble(Companion.AMOUNT_KEY, amount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        title = savedInstanceState?.getString(TITLE_KEY).toString()
        val titleTextInputLayout = findViewById<TextInputLayout>(R.id.title_input)
        titleTextInputLayout.editText?.setText(title)

        amount = savedInstanceState.getDouble(AMOUNT_KEY)
        val amountTextInputLayout = findViewById<TextInputLayout>(R.id.amount_input)
        amountTextInputLayout.editText?.setText(amount.toString())
    }

    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val AMOUNT_KEY = "AMOUNT_KEY"
    }

}