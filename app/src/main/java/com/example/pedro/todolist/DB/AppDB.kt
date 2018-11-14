package com.example.pedro.todolist.DB

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(com.example.pedro.todolist.CenarioToDoActivity.TODO::class), version = 1)
public abstract class AppDB: RoomDatabase() {

    companion object {

        private val DB_NAME = "ToDo.db"
        private var instance: AppDB? = null

        private fun create(context: Context): AppDB? {
            return Room.databaseBuilder(context, AppDB::class.java, DB_NAME).build()

        }

        public fun getInstance(context: Context): AppDB {
            if (instance == null) {
                instance = create(context)
            }

            return instance!!
        }
    }
    public abstract fun todoDao(): ToDoDAO
}