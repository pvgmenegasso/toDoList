package com.example.pedro.todolist.CenarioToDoActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.pedro.todolist.Cenarios.CenarioCadastro.CadastroToDoActivity
import com.example.pedro.todolist.R
import kotlinx.android.synthetic.main.activity_main.*

class ToDoActivity : AppCompatActivity(), ToDoActivityContract.View {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1 //para executar o cadastro de contatinho
        private const val LISTA = "ToDoList" //para salvar e restaurar a lista quando necessário
        public const val idput: String = "123"
    }

    val presenter: ToDoActivityContract.Presenter = ToDoActivityPresenter(this)
    var toDoList: MutableList<TODO> = mutableListOf()

    var indexToDoClicado = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddToDo.setOnClickListener(){
            val cadastrarToDo = Intent(this, CadastroToDoActivity::class.java)
            startActivityForResult(cadastrarToDo, REQUEST_CADASTRO)
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.onAtualizaLista(this)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putStringArrayList(LISTA, toDoList as ArrayList<String>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState != null)
            toDoList = savedInstanceState.getStringArrayList(LISTA) as MutableList<TODO>
    }

    override fun exibeLista(lista: MutableList<TODO>) {

                toDoList = lista

                val adapter = ToDoAdapter(this, toDoList)
                //configura o clique em cada item do RecyclerView
                adapter.setOnItemClickListener { todo, indexToDoClicado ->
                    val editaToDo = Intent(this, CadastroToDoActivity::class.java)
                    editaToDo.putExtra(idput, todo)
                    startActivity(editaToDo)
                }

                adapter.setOnItemClickListenerDone { indexToDoClicado ->

                presenter.onDeletaToDo(this, toDoList.get(indexToDoClicado))
                }

                val layoutManager = LinearLayoutManager(this)

                rvToDo.adapter = adapter
                rvToDo.layoutManager = layoutManager
            }






    }


