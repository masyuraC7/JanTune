package com.jantune.heartdisease.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateFormatter {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: String): String? {
        val originalFormatDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val changeFormatDate = SimpleDateFormat("dd/MM/yyyy")

        return originalFormatDate.parse(date)?.let { changeFormatDate.format(it) }
    }
}