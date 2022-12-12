package com.example.personalexpenses

data class Expenses(
    val title: String,
    val amount: Double,
    val color: String,
    val dateTime: String
) : java.io.Serializable {
    val t = title
    val a = amount
    val c = color
    val dt = dateTime
}