package com.example.appbiblioteca.persistance

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.appbiblioteca.database.DbConstants
import com.example.appbiblioteca.database.DbHelper
import com.example.appbiblioteca.domain.Book

class BookDAO(context: Context) {
    var banco = DbHelper(context)

    fun insert(book: Book): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        // val sql= "Insert into table...."
        // db.execSQL(sql)
        contentValues.put(DbConstants.LIVRO_TITULO, book.titulo)
        contentValues.put(DbConstants.LIVRO_TIPO, book.tipo)
        contentValues.put(DbConstants.LIVRO_AUTOR, book.autor)
        contentValues.put(DbConstants.LIVRO_N_PAGINAS, book.numeroDePaginas)
        contentValues.put(DbConstants.ULTIMA_PAGINA_LIDA, book.ultimaPaginaLida)
        contentValues.put(DbConstants.LIVRO_LIDO, book.lido)
        var resp_id = db.insert(DbConstants.TABLE_LIVROS, null, contentValues)
        val msg = if (resp_id != -1L) {
            "Inserido com sucesso"
        } else {
            "Erro ao inserir"
        }
        db.close()
        return msg
    }

    fun update(book: Book):
            Boolean {
        val db = banco.writableDatabase
        val isBookRead = if(book.ultimaPaginaLida==book.numeroDePaginas) 1 else 0
        val contentValues = ContentValues()
        contentValues.put(DbConstants.LIVRO_ID, book.id)
        contentValues.put(DbConstants.LIVRO_TITULO, book.titulo)
        contentValues.put(DbConstants.LIVRO_TIPO, book.tipo)
        contentValues.put(DbConstants.LIVRO_AUTOR, book.autor)
        contentValues.put(DbConstants.LIVRO_N_PAGINAS, book.numeroDePaginas)
        contentValues.put(DbConstants.ULTIMA_PAGINA_LIDA, book.ultimaPaginaLida)
        contentValues.put(DbConstants.LIVRO_LIDO, isBookRead)
        //db.update()
        db.insertWithOnConflict(
            DbConstants.TABLE_LIVROS,
            null,
            contentValues,
            SQLiteDatabase.CONFLICT_REPLACE
        )
        db.close()

        return true
    }

    fun remove(id: Int): Int {
        val db = banco.writableDatabase
        return db.delete(DbConstants.TABLE_LIVROS, "ID =?", arrayOf(id.toString()))
    }

    fun getAll(): ArrayList<Book> {
        Log.v("LOG", "GetAll")
        val db = banco.writableDatabase
        val sql = "SELECT *  from " + DbConstants.TABLE_LIVROS
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()

        while (cursor.moveToNext()) {
            val livro = clienteFromCursor(cursor)
            books.add(livro)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${books.size}")
        return books
    }


    fun getByName(titulo: String): ArrayList<Book> {
        Log.v("LOG", "Get By Titulo")
        val db = banco.writableDatabase
        val sql =
            "SELECT *" +
            "  from " + DbConstants.TABLE_LIVROS + "" +
            " where ${DbConstants.LIVRO_TITULO}" +
            " like '%$titulo%'"
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()
        while (cursor.moveToNext()) {
            val cliente = clienteFromCursor(cursor)
            books.add(cliente)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${books.size}")
        return books
    }

    private fun clienteFromCursor(cursor: Cursor): Book {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.LIVRO_ID))
        val titulo = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.LIVRO_TITULO))
        val tipo = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.LIVRO_TIPO))
        val autor = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.LIVRO_AUTOR))
        val numeroDePaginas =
            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.LIVRO_N_PAGINAS))
        val ultimaPaginaLida =
            cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.ULTIMA_PAGINA_LIDA))
        val lido = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.LIVRO_LIDO))

        return Book(id, titulo, tipo, autor, numeroDePaginas, ultimaPaginaLida, lido)
    }
}