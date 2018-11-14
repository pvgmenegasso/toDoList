package com.example.pedro.todolist.Cenarios.CenarioCadastro

import android.content.Context
import com.example.pedro.todolist.CenarioToDoActivity.TODO

interface CadastroToDoContract {

    interface View{
        fun cadastradoComSucesso()

    }
    interface Presenter{
        fun OnCadastraToDo(context: Context, todo: TODO)
    }
}