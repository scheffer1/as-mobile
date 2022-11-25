package com.example.appbiblioteca.domain

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class Book(
    val id: Int?,
    val titulo: String?,
    val tipo: String?,
    val autor: String?,
    val numeroDePaginas: Int,
    val ultimaPaginaLida: Int?,
    val lido: Int
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readValue(Int::class.java.classLoader) as Int
    ) {
    }

    override fun describeContents(): Int {
     return 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        if (id != null) {
            parcel?.writeInt(id)
        }
        parcel?.writeString(titulo)
        parcel?.writeString(tipo)
        parcel?.writeString(autor)
        parcel?.writeInt(numeroDePaginas)
        if (ultimaPaginaLida != null) {
            parcel?.writeInt(ultimaPaginaLida)
        }
        parcel?.writeInt(lido)
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}