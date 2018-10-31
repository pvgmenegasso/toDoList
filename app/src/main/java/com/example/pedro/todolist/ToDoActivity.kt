package com.example.pedro.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.*

class ToDoActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1 //para executar o cadastro de contatinho
        private const val LISTA = "ToDoList" //para salvar e restaurar a lista quando necessário
    }

    var toDoList: MutableList<String> = mutableListOf();

    var indexToDoClicado = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddToDo.setOnClickListener(){
            val cadastrarToDo = Intent(this,CadastroToDoActivity::class.java)
            startActivityForResult(cadastrarToDo, REQUEST_CADASTRO)
        }

    }

    override fun onResume() {
        super.onResume()
        carregaLista()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CADASTRO && resultCode == Activity.RESULT_OK){
            val todo: String? = data?.getStringExtra(CadastroToDoActivity.TODO)
            //caso algum item tenha sido clicado seus dados são alterados, caso não adiciona um novo
            if (todo != null) {
                if(indexToDoClicado >= 0){
                    toDoList.set(indexToDoClicado, todo)
                    indexToDoClicado = -1
                }else {
                    toDoList.add(todo)
                }
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putStringArrayList(LISTA, toDoList as ArrayList<String>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState != null)
            toDoList = savedInstanceState.getStringArrayList(LISTA) as MutableList<String>
    }

    fun carregaLista() {
        val adapter = ToDoAdapter(this, toDoList)

        //configura o clique em cada item do RecyclerView
        adapter.setOnItemClickListener { todo, indexToDoClicado ->
            this.indexToDoClicado = indexToDoClicado
            val editaToDo = Intent(this, CadastroToDoActivity::class.java)
            editaToDo.putExtra(CadastroToDoActivity.TODO, todo)
            this.startActivityForResult(editaToDo, REQUEST_CADASTRO)
        }

        adapter.setOnItemClickListenerDone { indexToDoClicado ->
            if(!toDoList[indexToDoClicado].isEmpty()){
                toDoList.removeAt(indexToDoClicado)
            }
            carregaLista()
        }

        val layoutManager = LinearLayoutManager(this)

        rvToDo.adapter = adapter
        rvToDo.layoutManager = layoutManager
    }

}
