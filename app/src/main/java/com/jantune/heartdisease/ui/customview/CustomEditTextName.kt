package com.jantune.heartdisease.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.jantune.heartdisease.R


class CustomEditTextName : AppCompatEditText {
    private lateinit var showNameImg: Drawable
    private lateinit var trueBackground: Drawable
    private lateinit var falseBackground: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    @SuppressLint("ResourceAsColor")
    private fun init() {
        showNameImg =
            ContextCompat.getDrawable(
                context, R.drawable.user_solid
            ) as Drawable
        val color = Color.parseColor("#9E9E9E")
        showNameImg.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        trueBackground =
            ContextCompat.getDrawable(
                context, R.drawable.custom_input
            ) as Drawable
        falseBackground =
            ContextCompat.getDrawable(
                context, R.drawable.custom_input_false
            ) as Drawable

        background = trueBackground

        setPadding(30, 50, 30, 50)

        //show icon/img btn
        setButtonDrawables()

        addTextChangedListener(onTextChanged = { s, _, _, _ ->
            if (s.toString().length >= 4) {
                background = trueBackground
                error = null
            } else {
                background = falseBackground
                error = context.getString(R.string.error_name)
            }
        })
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable = showNameImg,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }
}