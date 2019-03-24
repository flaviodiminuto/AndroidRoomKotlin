package com.flavio.android.androidroomkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.flavio.android.androidroomkotlin.database.DatabaseKotlin
import com.flavio.android.androidroomkotlin.model.Pessoa

class MainActivity : AppCompatActivity() {

    private var pessoas = mutableListOf<Pessoa>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flavio = Pessoa(0,30,"Flavio","Desevolvedor")
        val lucas = Pessoa(0, 20, "Lucas", "Estudante")
        val natalia = Pessoa(0, 18, "Natalia", "Estudante")
        val paloma = Pessoa(0, 25, "Paloma", "CEO")
        val kotlin = Pessoa(0,5,"Kotlin","Ferramenta")
        pessoas.add ( flavio )
        pessoas.add ( lucas )
        pessoas.add ( natalia )
        pessoas.add ( paloma )
        pessoas.add ( kotlin)

        salvarPessoas()

    }
    private fun salvarPessoas(){
        val database = Room
            .databaseBuilder(this, DatabaseKotlin::class.java, "database.db")
            .allowMainThreadQueries()
            .build()

        val dao = database.pessoaDao()
        for (pessoa in pessoas){
            dao.salvar(pessoa)
        }
        Toast.makeText(this,"Pessoas Salvas em Kotlin ORM", Toast.LENGTH_LONG).show()
    }
}
