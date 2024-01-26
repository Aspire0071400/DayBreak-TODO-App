package com.aspire.daybreaktodo_app.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aspire.daybreaktodo_app.databinding.ViewTaskListBinding

class TaskAdapter(private val list:MutableList<TaskModel>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    private var listener : TaskClickListener? = null
    fun setListener(listener: TaskClickListener){
        this.listener = listener
    }
    inner class TaskViewHolder(val binding : ViewTaskListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ViewTaskListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.taskNameView.text = this.task
                binding.taskDeleteView.setOnClickListener {
                    listener?.onDeleteTask(this)
                }
                binding.taskEditView.setOnClickListener {
                    listener?.onEditTask(this)
                }

            }
        }
    }

    interface TaskClickListener{
        fun onDeleteTask(taskModel: TaskModel)
        fun onEditTask(taskModel: TaskModel)
    }

}