package com.example.personalexpenses

class ExpensesModel (private var title: String, private var amount: Double, private var color: String) {

    // Getter and Setter
    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getAmount(): Double {
        return amount
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    fun getColor(): String {
        return color
    }

    fun setColor(color: String) {
        this.color = color
    }
}
