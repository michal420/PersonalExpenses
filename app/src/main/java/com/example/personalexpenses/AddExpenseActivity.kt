package com.example.personalexpenses

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.personalexpenses.databinding.ActivityAddExpenseBinding
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = Intent(this, MainActivity::class.java)
        val key = "expenses"
        var expenses: ArrayList<String> = ArrayList()
        val formatter = DateTimeFormatter.ofPattern("E - dd MMM yyyy - HH:mm")
        val currentDateTime: LocalDateTime

        // remove the default bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_expense)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Input from title text field
        val titleText = findViewById<TextInputLayout>(R.id.title_input)
        // Input from amount text field
        val amountText = findViewById<TextInputLayout>(R.id.amount_input)
        // Color selected from the Color Menu
        val selectedValue = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

//        val colors = resources.getStringArray(R.array.colors_array)
//        val adapter = ArrayAdapter(this, R.layout.dropdown_item, colors)
//        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
//        // set adapter to the autocomplete tv to the arrayAdapter
//        autocompleteTV.setAdapter(adapter)

//        val selectedValue: String = (autoCompleteTextView.getEditText() as AutoCompleteTextView).text
        /*       val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                   override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                       Log.d("myTag", "$p2")
                   }

                   override fun onNothingSelected(p0: AdapterView<*>?) {
                       Log.d("myTag", "Nothing selected")
                   }
               }
               */

//        selectedValue.setOnItemClickListener { adapterView, view, i, l ->
//            Log.d("myTag", "$selectedValue[i].text")
//        }

        val fab: View = findViewById(R.id.fab_ok)
        fab.setOnClickListener {
            val title = titleText.editText?.text.toString()
            val amount = amountText.editText?.text.toString().toDouble()
            val color = selectedValue.text.toString()
            val currentData = LocalDateTime.now().format(formatter)
//            val expenses = arrayOf(title, amount, color)
//            expenses.add(title)
//            expenses.add(amount)
//            expenses.add(color)
//            intent.putExtra(key, expenses)

            val expense = Expenses(title, amount, color, currentData)
            intent.putExtra(key, expense)
            startActivity(intent)

//            backToMain(intent)
//            Log.d("myTag", "Expenses: ${expenses[0]}, ${expenses[1]}, ${expenses[2]}")
        }

    }

    private fun backToMain(intent: Intent) {
//        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}