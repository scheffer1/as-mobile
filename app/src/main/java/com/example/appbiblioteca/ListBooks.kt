package com.example.appbiblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbiblioteca.domain.Book
import com.example.appbiblioteca.persistance.BookDAO

class ListBooks : AppCompatActivity() {
    private var livrosList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_livros)
        updateAdapter()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        var rvDados = findViewById<RecyclerView>(R.id.rv_livros)
        var txtMsg = findViewById<TextView>(R.id.txtMsg)
        val bookDao = BookDAO(this)
        livrosList.clear() //todo
        livrosList = bookDao.getAll()
        if (livrosList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("Sem dados para exibir")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
        }
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        val rvDados = findViewById<RecyclerView>(R.id.rv_livros)
        Log.v("LOG", "Inicia RecyclerView")
        val adapter2 = BookAdapter(livrosList)
        rvDados.adapter = adapter2
        val layout = GridLayoutManager(this, 1)
        //val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }
}