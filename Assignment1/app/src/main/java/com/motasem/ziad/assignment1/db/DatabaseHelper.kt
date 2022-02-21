package com.motasem.ziad.assignment1.db

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.motasem.ziad.assignment1.model.User

class DatabaseHelper(activity: Activity) :
    SQLiteOpenHelper(activity, DATABASE_NAME, null, DATABASE_VERSION) {
    private val db: SQLiteDatabase = this.writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(User.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists ${User.TABLE_NAME}")
    }

    companion object {
        val DATABASE_NAME = "Database"
        val DATABASE_VERSION = 1
    }

    fun insertUser(name: String, email: String, password: String, phone: String): Boolean {
        val cv = ContentValues()
        cv.put(User.COL_NAME, name)
        cv.put(User.COL_EMAIL, email)
        cv.put(User.COL_PASSWORD, password)
        cv.put(User.COL_PHONE, phone)
        return db.insert(User.TABLE_NAME, null, cv) > 0
    }

     fun getAllUsers(): ArrayList<User> {
          val data = ArrayList<User>()
          val c =
              db.rawQuery(
                  "select * from ${User.TABLE_NAME} order by ${User.COL_ID} desc",
                  null
              )
          c.moveToFirst()
          while (!c.isAfterLast) {
              val user =
                  User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4))
              data.add(user)
              c.moveToNext()
          }
          c.close()
          return data
      }





}