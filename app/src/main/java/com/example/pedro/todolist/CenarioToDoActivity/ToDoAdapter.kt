package com.example.pedro.todolist.CenarioToDoActivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pedro.todolist.R
import kotlinx.android.synthetic.main.todo_item.view.*


class ToDoAdapter(val context: Context, val todo: List<TODO>)
    : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    var clickListener: ((contatinho: TODO, index: Int) -> Unit)? = null
    var clickListenerDone: ((index: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, todo[position], clickListener, clickListenerDone)
    }

    fun setOnItemClickListener(clique: ((todo: TODO, index: Int) -> Unit)){
        this.clickListener = clique
    }

    fun setOnItemClickListenerDone(cliqueDone: ((index: Int) -> Unit)){
        this.clickListenerDone = cliqueDone
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(context:Context, todo: TODO, clickListener: ((todo: TODO, index: Int) -> Unit)?, clickListenerDone: ((index: Int) -> Unit)?) {
            itemView.tvToDo.text = todo.text

            if(clickListener != null) {
                itemView.setOnClickListener {
                    clickListener.invoke(todo, adapterPosition)
                }
            }
            if(clickListenerDone !=null) {
                itemView.btnDone.setOnClickListener {
                    clickListenerDone.invoke(adapterPosition)
                }
            }

        }

    }



}