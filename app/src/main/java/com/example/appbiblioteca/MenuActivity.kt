package com.example.appbiblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    private lateinit var addBook :Button
    private lateinit var updateBook:Button
    private lateinit var listBooks:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        addBook=findViewById(R.id.button_add_livro)
        updateBook=findViewById(R.id.button_update_livro)
        listBooks=findViewById(R.id.button_list_livro)


        addBook.setOnClickListener {
            val intent = Intent(this, CreateBook::class.java)
            startActivity(intent)
        }

        updateBook.setOnClickListener {
            val intent = Intent(this, EditBook::class.java)
            startActivity(intent)
        }

        listBooks.setOnClickListener {
            val intent = Intent(this, ListBooks::class.java)
            startActivity(intent)
        }
    }

}