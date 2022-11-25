package com.example.appbiblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.appbiblioteca.domain.Book
import com.example.appbiblioteca.persistance.BookDAO

class EditBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_livro)
        val bookDAO = BookDAO(this)
        val deleteButton = findViewById<Button>(R.id.edit_book2)
        val editButton = findViewById<Button>(R.id.edit_book)

        deleteButton.setOnClickListener {
          deleteBook(findViewById<EditText>(R.id.edt_id).text.toString().toInt(), bookDAO)
        }

        editButton.setOnClickListener {
            val book = getValuesFromEdit()
            updateBook(book, bookDAO)
        }
    }

    private fun getValuesFromEdit(): Book {
        val bookId = findViewById<EditText>(R.id.edt_id).text.toString().toInt()
        val bookAuthor = findViewById<EditText>(R.id.edt_autor).text.toString()
        val bookTittle = findViewById<EditText>(R.id.edt_titulo).text.toString()
        val bookType = findViewById<EditText>(R.id.edt_tipo).text.toString()
        val bookNumberOfPages = findViewById<EditText>(R.id.edt_paginas).text.toString().toInt()
        val bookLastPageRead =
            findViewById<EditText>(R.id.edt_ultima_pagina).text.toString().toInt()
        val isBookRead = findViewById<EditText>(R.id.edt_lido).text.toString().toInt()

        return Book(
            bookId,
            bookTittle,
            bookType,
            bookAuthor,
            bookNumberOfPages,
            bookLastPageRead,
            isBookRead
        )
    }

    private fun updateBook(book: Book, bookDAO: BookDAO) {
        bookDAO.update(book)
        val intent = Intent(this, ListBooks::class.java)
        startActivity(intent)
    }

    private fun deleteBook(id: Int, bookDAO: BookDAO) {
        bookDAO.remove(id)
        val intent = Intent(this, ListBooks::class.java)
        startActivity(intent)
    }
}