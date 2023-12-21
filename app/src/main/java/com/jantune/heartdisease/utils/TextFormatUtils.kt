package com.jantune.heartdisease.utils

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan

object TextFormatUtils {
    fun spannableBoldFormat(
        text: String,
        endSpan: Int,
        startSpan: Int = 0
    ): SpannableStringBuilder {

        val ss = SpannableStringBuilder(text)
        ss.setSpan(
            StyleSpan(Typeface.BOLD),
            startSpan,
            endSpan,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return ss
    }
}