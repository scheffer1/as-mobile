package com.example.appbiblioteca.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper (context: Context) :
    SQLiteOpenHelper(context, DbConstants.DATABASE_NAME , null, DbConstants.DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val sql="CREATE TABLE ${DbConstants.TABLE_LIVROS} (${DbConstants.LIVRO_ID}  INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, ${DbConstants.LIVRO_TITULO} TEXT,${DbConstants.LIVRO_TIPO} TEXT," +
                " ${DbConstants.LIVRO_AUTOR} TEXT, ${DbConstants.LIVRO_N_PAGINAS} INTEGER, " +
                "${DbConstants.ULTIMA_PAGINA_LIDA} INTEGER, ${DbConstants.LIVRO_LIDO} TINYINT)"
        db.execSQL(sql)
        Log.e("LOG","Criando Tabela livros")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.DATABASE_NAME)
        onCreate(db)
    }

}