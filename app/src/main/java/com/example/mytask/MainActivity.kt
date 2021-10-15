package com.example.mytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        //attach the Adapter to RecyclerView by referencing rvMyTask
        rvMyTask.adapter = todoAdapter
        //arrange list in linearlayout
        rvMyTask.layoutManager = LinearLayoutManager(this)

        //define what happen when the add button is clicked
        btnAdd.setOnClickListener{
            val todoTitle = etTitle.text.toString()
            if(todoTitle.isNotEmpty())
            {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo((todo))
                etTitle.text.clear()
            }
        }

        btnDelete.setOnClickListener()
        {
            todoAdapter.deleteTodos()
        }
    }
}