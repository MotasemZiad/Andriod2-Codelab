package com.motasem.ziad.assignment1.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    var id: Int,
    var name: String?,
    var email: String?,
    var password: String?,
    var phone: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

        val COL_ID = "id"
        val COL_NAME = "name"
        val COL_EMAIL = "email"
        val COL_PASSWORD = "password"
        val COL_PHONE = "phone"

        val TABLE_NAME = "User"
        val TABLE_CREATE = "create table $TABLE_NAME ($COL_ID integer primary key autoincrement," +
                "$COL_NAME text not null, $COL_EMAIL text , $COL_PASSWORD text, $COL_PHONE text)"
    }
}