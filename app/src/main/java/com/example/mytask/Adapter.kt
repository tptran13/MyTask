package com.example.mytask

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    //list consist of todo objects (items)
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    //class TodoViewHolder inherits from RecyclerView
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //Instantiates the item layout file and view holder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo:Todo)
    {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteTodos()
    {
        todos.removeAll { todo->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

   private fun toggleStrikeThrough(tvTodoItem: TextView, isChecked:Boolean)
   {
       if(isChecked)
       {
           tvTodoItem.paintFlags=tvTodoItem.paintFlags or STRIKE_THRU_TEXT_FLAG
       }
       else
       {
           tvTodoItem.paintFlags=tvTodoItem.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
       }
   }

    // Loads the data at the specified position into the views
    // whose references are stored in the given view holder.
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo=todos[position]
        holder.itemView.apply {
           tvTodoItem.text=currentTodo.title
           cbDone.isChecked=currentTodo.isChecked
           toggleStrikeThrough(tvTodoItem, currentTodo.isChecked)
           cbDone.setOnCheckedChangeListener { _, isChecked ->
               toggleStrikeThrough(tvTodoItem, isChecked)
               currentTodo.isChecked=!currentTodo.isChecked
           }
       }
    }

    //Returns the number of items in the data source
    override fun getItemCount(): Int {
        return todos.size
    }
}