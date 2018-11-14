package com.example.pedro.todolist.Cenarios.CenarioCadastro

import android.content.Context
import com.example.pedro.todolist.DB.AppDB
import com.example.pedro.todolist.CenarioToDoActivity.TODO
import com.example.pedro.todolist.DB.ToDoDAO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroToDoPresenter (val view: CadastroToDoContract.View) : CadastroToDoContract.Presenter
{

    override fun OnCadastraToDo(context: Context, tofazer: TODO){
        val toDoDAO: ToDoDAO = AppDB.getInstance(context).todoDao()
        doAsync {
            toDoDAO.insert(tofazer)
            uiThread {
                view.cadastradoComSucesso()
            }
        }
    }
}