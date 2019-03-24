package com.flavio.android.androidroomkotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flavio.android.androidroomkotlin.dao.PessoaDao
import com.flavio.android.androidroomkotlin.model.Pessoa

@Database(entities = [Pessoa::class],version = 1,exportSchema = false)
abstract class DatabaseKotlin : RoomDatabase() {
    abstract fun pessoaDao():PessoaDao
}