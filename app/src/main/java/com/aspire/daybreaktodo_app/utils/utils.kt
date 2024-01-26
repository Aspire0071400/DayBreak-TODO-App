package com.aspire.daybreaktodo_app.utils

import android.app.Dialog
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}
fun Dialog.setupDialog(layoutResId: Int){
    setContentView(layoutResId)
    window!!.setLayout(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    setCancelable(false)
}

fun validateEditText(editText : EditText, textInputLayout : TextInputLayout) : Boolean {
    return when{
        editText.text.toString().trim().isEmpty() -> {
            editText.error = "Required"
            false
        }else -> {
            textInputLayout.error = null
            true
        }
    }
}