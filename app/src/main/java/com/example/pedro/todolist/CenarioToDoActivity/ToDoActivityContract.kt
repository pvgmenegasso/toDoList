package com.example.pedro.todolist.CenarioToDoActivity

import android.content.Context

interface ToDoActivityContract {

    interface View{
        fun exibeLista(lista: MutableList<TODO>)
    }

    interface Presenter{

        fun onAtualizaLista(context: Context)
        fun onDeletaToDo(context: Context, toDo: TODO)
    }
}