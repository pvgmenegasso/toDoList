package com.example.pedro.todolist.DB

import android.arch.persistence.room.*
import com.example.pedro.todolist.CenarioToDoActivity.TODO

@Dao
interface ToDoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: TODO)

    @Query("SELECT * FROM todo")
    fun getAll(): List<TODO>

   /* @Update
    fun update(todo: CadastroToDoActivity.Companion.TODO)
    */

    @Delete
    fun delete(todo: TODO)

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: Int): TODO


}