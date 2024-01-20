package com.aspire.daybreaktodo_app

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.aspire.daybreaktodo_app.databinding.ActivityMainBinding
import com.aspire.daybreaktodo_app.utils.setupDialog

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        val close_btn_addTaskDialog = addTaskDialog.findViewById<ImageView>(R.id.close_btn_addTaskDialog)
        val update_btn_updateTaskDialog = updateTaskDialog.findViewById<ImageView>(R.id.close_btn_updateTaskDialog)

        close_btn_addTaskDialog.setOnClickListener{addTaskDialog.dismiss()}
        update_btn_updateTaskDialog.setOnClickListener { updateTaskDialog.dismiss()}

        mainBinding.addTaskFAB.setOnClickListener {
            addTaskDialog.show()
        }
    }
}