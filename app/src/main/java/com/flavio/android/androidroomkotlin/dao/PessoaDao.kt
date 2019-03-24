package com.flavio.android.androidroomkotlin.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.flavio.android.androidroomkotlin.model.Pessoa

@Dao
interface PessoaDao {
    @Insert
    fun salvar(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa")
    fun todos(): List<Pessoa>

    @Delete
    fun delete (pessoa : Pessoa)
}