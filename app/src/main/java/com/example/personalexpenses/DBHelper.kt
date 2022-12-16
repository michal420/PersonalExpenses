package com.example.personalexpenses

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // create a database
    override fun onCreate(db: SQLiteDatabase) {
        // create columns
        val query = (
                "CREATE TABLE " + TABLE_NAME + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_TITLE + " TEXT," +
                        COLUMN_AMOUNT + " DOUBLE," +
                        COLUMN_COLOR + " TEXT," +
                        COLUMN_DATE + " TEXT" + ")"
                )
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // adding data in database
    fun addExpense(title: String, amount: Double, color: String, date: String) {
        val values = ContentValues()

        // insert values
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_AMOUNT, amount)
        values.put(COLUMN_COLOR, color)
        values.put(COLUMN_DATE, date)

        // create a writable variable of db for inserting values
        val db = writableDatabase
        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // get all data from database
    fun getExpense(): Cursor? {
        // create a readable variable of db to read value from it
        val db = readableDatabase

        // return a cursor to read data from the db
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    } // end getExpense()

    fun deleteAll() {
        val db = writableDatabase
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    companion object {
        // define variables for db
        private const val DATABASE_NAME = "Expenses_DB"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "expenses_table"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "name"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_COLOR = "color"
        const val COLUMN_DATE = "date"
    }
}
