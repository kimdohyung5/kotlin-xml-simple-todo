package com.kimdo.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimdo.mytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        todoAdapter = TodoAdapter( mutableListOf() )

        binding.recyclerview.adapter = todoAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.addTodo.setOnClickListener {
            val todoTitle = binding.editTodo.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.editTodo.text.clear()
            }
        }

        binding.deleteDone.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}