package com.aspire.daybreaktodo_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.aspire.daybreaktodo_app.databinding.FragmentAddTaskDialogBinding
import com.google.android.material.textfield.TextInputEditText


class AddTaskDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTaskDialogBinding
    private lateinit var listener : DialogSaveBtnClickListener

    fun setListener(listener : DialogSaveBtnClickListener){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerEvents()

    }

    private fun registerEvents() {

        binding.saveBtnAddTask.setOnClickListener {
            val taskName = binding.taskNameTietAddTask.text.toString()
            //val taskDescription = binding.taskDescriptionTietAddTask.text.toString()
            if(taskName.isNotEmpty()){
                listener.onSaveTask(taskName , binding.taskNameTietAddTask)
            }else{
                Toast.makeText(context,"Empty Field",Toast.LENGTH_SHORT).show()
            }
        }
        binding.closeBtnAddTask.setOnClickListener {
            dismiss()
        }

    }

    interface DialogSaveBtnClickListener{
        fun onSaveTask(task : String, taskNameTIET : TextInputEditText)
    }


}