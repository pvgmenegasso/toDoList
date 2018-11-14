package com.example.pedro.todolist.Cenarios.CenarioCadastro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pedro.todolist.R
import com.example.pedro.todolist.CenarioToDoActivity.TODO
import com.example.pedro.todolist.CenarioToDoActivity.ToDoActivity.Companion.idput
import kotlinx.android.synthetic.main.activity_cadastro_to_do.*

class CadastroToDoActivity : AppCompatActivity(), CadastroToDoContract.View {



    var tofazer: TODO? = null

    val presenter: CadastroToDoContract.Presenter = CadastroToDoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_to_do)
        tofazer = intent.getSerializableExtra(idput) as TODO?
        if(tofazer != null){
            carregaDados()
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

        if(tofazer == null)
        {
            tofazer = TODO(edtToDo.text.toString())
        }else
        {
            tofazer?.text = edtToDo.text.toString()
        }
/*
        val abreLista = Intent(this, ToDoActivity::class.java)
        abreLista.putExtra(parafazer.text, tofazer)
        setResult(Activity.RESULT_OK, abreLista)
        finish()
        */
        tofazer?.let { tofazer ->
            presenter.OnCadastraToDo(this, tofazer)
        }

    }
    override fun cadastradoComSucesso()
    {
        Toast.makeText(this, "cadastrado com sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }
    private fun carregaDados() {
        edtToDo.setText(tofazer?.text)
    }

}
