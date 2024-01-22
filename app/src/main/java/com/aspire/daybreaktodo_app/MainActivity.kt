package com.aspire.daybreaktodo_app

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.aspire.daybreaktodo_app.databinding.ActivityMainBinding
import com.aspire.daybreaktodo_app.utils.setupDialog
import com.aspire.daybreaktodo_app.utils.validateEditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val mainBinding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val addTaskDialog : Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.add_task_dialog)
        }
    }
    private val updateTaskDialog : Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.update_task_dialog)
        }
    }

    private val loadingDialog : Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.loading_dialog)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        //Adding Task and Description...
        val close_btn_addTaskDialog = addTaskDialog.findViewById<ImageView>(R.id.close_btn_addTaskDialog)
        close_btn_addTaskDialog.setOnClickListener{addTaskDialog.dismiss()}


        val add_TaskName_tiet = addTaskDialog.findViewById<TextInputEditText>(R.id.taskName_tiet_addTaskDialog)
        val add_TaskName_til = addTaskDialog.findViewById<TextInputLayout>(R.id.taskName_til_addTaskDialog)

        add_TaskName_tiet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                validateEditText(add_TaskName_tiet , add_TaskName_til)
            }
        })

        val add_Taskdescription_tiet = addTaskDialog.findViewById<TextInputEditText>(R.id.taskDescription_tiet_addTaskDialog)
        val add_Taskdescription_til = addTaskDialog.findViewById<TextInputLayout>(R.id.taskDescription_til_addTaskDialog)

        add_TaskName_tiet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                validateEditText(add_Taskdescription_tiet , add_Taskdescription_til)
            }
        })


        mainBinding.addTaskFAB.setOnClickListener {
            addTaskDialog.show()
        }

        val saveTaskBtn = addTaskDialog.findViewById<Button>(R.id.save_btn_addTaskDialog)
        saveTaskBtn.setOnClickListener {
            if(validateEditText(add_TaskName_tiet , add_TaskName_til) && validateEditText(add_Taskdescription_tiet , add_Taskdescription_til) ){
                addTaskDialog.dismiss()
                Toast.makeText(this,"Validated!!",Toast.LENGTH_SHORT).show()
                loadingDialog.show()

            }
        }



        //Updating Task and Description...
        val update_btn_updateTaskDialog = updateTaskDialog.findViewById<ImageView>(R.id.close_btn_updateTaskDialog)
        update_btn_updateTaskDialog.setOnClickListener { updateTaskDialog.dismiss()}

        val update_TaskName_tiet = updateTaskDialog.findViewById<TextInputEditText>(R.id.taskName_tiet_updateTaskDialog)
        val update_TaskName_til = updateTaskDialog.findViewById<TextInputLayout>(R.id.taskName_til_updateTaskDialog)

        update_TaskName_tiet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                validateEditText(update_TaskName_tiet , update_TaskName_til)
            }
        })

        val update_Taskdescription_tiet = updateTaskDialog.findViewById<TextInputEditText>(R.id.taskDescription_tiet_updateTaskDialog)
        val update_Taskdescription_til = updateTaskDialog.findViewById<TextInputLayout>(R.id.taskDescription_til_updateTaskDialog)

        update_TaskName_tiet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                validateEditText(update_Taskdescription_tiet , update_Taskdescription_til)
            }
        })

        val updateTaskbtn = updateTaskDialog.findViewById<Button>(R.id.update_btn_updateTaskDialog)
        updateTaskbtn.setOnClickListener {
            if( validateEditText(update_TaskName_tiet , update_TaskName_til) && validateEditText(update_Taskdescription_tiet , update_Taskdescription_til)){
                updateTaskDialog.dismiss()
                Toast.makeText(this,"Validated!!",Toast.LENGTH_SHORT).show()
                loadingDialog.show()
            }
        }


    }
}