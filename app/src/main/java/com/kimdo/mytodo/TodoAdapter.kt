package com.kimdo.mytodo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimdo.mytodo.databinding.ItemTodoBinding

class TodoAdapter (private val todos: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder( val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflator = LayoutInflater.from( parent.context)
        return TodoViewHolder( ItemTodoBinding.inflate( inflator, parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[ position ]
        holder.binding.tvTodoTitle.text = todo.title
        holder.binding.cbDone.isChecked = todo.isChecked

        holder.binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.binding.tvTodoTitle , isChecked)
            todo.isChecked = !todo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean ) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    fun addTodo( todo: Todo ) {
        todos.add( todo )
        notifyDataSetChanged()
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
}