package com.example.pedro.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_to_do.*

class CadastroToDoActivity : AppCompatActivity() {

    companion object {
        public const val TODO: String = "ToDo" //para putExtra entre activities
        private const val REQUEST_PESMISSOES: Int = 3 //para solicitar permissão de ligação em tempo real
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_to_do)

        val todo: String? = intent.getStringExtra(TODO)
        if(todo != null){
            carregaDados(todo)
        }

        btnSalvar.setOnClickListener(){
            salvaToDo()
        }

    }


    private fun salvaToDo() {
        if(edtToDo.text.isEmpty()){
            edtToDo.requestFocus()
            edtToDo.setError(getString(R.string.campo_obrigatorio))
            return
        }

        val todo = edtToDo.text.toString()

        val abreLista = Intent(this, ToDoActivity::class.java)
        abreLista.putExtra(TODO, todo)
        setResult(Activity.RESULT_OK, abreLista)
        finish()
    }

    private fun carregaDados(todo: String) {
        edtToDo.setText(todo)
    }
}
