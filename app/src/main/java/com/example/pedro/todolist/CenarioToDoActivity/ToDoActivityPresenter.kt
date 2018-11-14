package com.example.pedro.todolist.CenarioToDoActivity

import android.content.Context
import com.example.pedro.todolist.DB.AppDB
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ToDoActivityPresenter (val view: ToDoActivity): ToDoActivityContract.Presenter {

    override fun onAtualizaLista(context: Context) {
        val todoDao = AppDB.getInstance(context = context).todoDao()
        doAsync {
            val toDoList = todoDao.getAll() as MutableList<TODO>
            uiThread {
                view.exibeLista(toDoList)
            }
        }

        //view.exibeLista(toDoList)
    }

    override fun onDeletaToDo(context: Context, toDo: TODO){
        val todoDao = AppDB.getInstance(context = context).todoDao()
        doAsync {
            todoDao.delete(toDo)
            uiThread {
                onAtualizaLista(context)
            }
        }
    }
}