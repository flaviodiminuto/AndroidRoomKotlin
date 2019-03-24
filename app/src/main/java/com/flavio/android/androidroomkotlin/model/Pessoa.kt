package com.flavio.android.androidroomkotlin.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Pessoa {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var idade : Int = 0
    var nome =""
    var profissao = ""

    constructor()

    @Ignore
    constructor(id: Int, idade: Int, nome: String, profissao: String) {
        this.id = id
        this.idade = idade
        this.nome = nome
        this.profissao = profissao
    }
}