package com.jantune.heartdisease.ui.view.main.identification.form.spinner

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast

class JenisNyeriDadaSpinner(contexts: Context, items: List<String>) : AdapterView.OnItemSelectedListener {
    val item = items
    val context = contexts

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            if (parent.getItemAtPosition(position).toString() == item[0]){
                (view as TextView).setTextColor(Color.GRAY)
            }
        }

        if (position != 0){
            Toast.makeText(context, item[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}