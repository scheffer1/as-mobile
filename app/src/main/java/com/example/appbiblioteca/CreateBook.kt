package com.example.appbiblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.appbiblioteca.domain.Book
import com.example.appbiblioteca.persistance.BookDAO

class CreateBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_livro)
        val saveButton=findViewById<Button>(R.id.save_book)
        val bookDAO = BookDAO(this)

        saveButton.setOnClickListener{
            bookDAO.insert(getValuesFromForms())
            val intent = Intent(this, ListBooks::class.java)
            startActivity(intent)
        }
    }

    private fun getValuesFromForms() :Book{
        val livroTitulo= findViewById<EditText>(R.id.edt_titulo_add)
        val livroAutor= findViewById<EditText>(R.id.edt_autor_add)
        val livroTipo= findViewById<EditText>(R.id.edt_tipo_add)
        val livroNumeroDePaginas=findViewById<EditText>(R.id.edt_paginas_add)

       return  Book(
            null,
            livroTitulo.text.toString(),
            livroAutor.text.toString(),
            livroTipo.text.toString(),
            livroNumeroDePaginas.text.toString().toInt(),
            null,
            0,)
    }
}