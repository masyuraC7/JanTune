package com.jantune.heartdisease.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.jantune.heartdisease.R

class CustomEditTextPassword : AppCompatEditText, View.OnTouchListener {
    private lateinit var showVisibleOnImg: Drawable
    private lateinit var showVisibleOffImg: Drawable
    private lateinit var showLockImg: Drawable
    private lateinit var trueBackground: Drawable
    private lateinit var falseBackground: Drawable
    var isVisibleOnTrue = false

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
        // Menginisialisasi gambar button
        showVisibleOnImg =
            ContextCompat.getDrawable(
                context, R.drawable.baseline_visibility_24
            ) as Drawable
        showVisibleOnImg.setColorFilter(R.color.onBgInput, PorterDuff.Mode.MULTIPLY)

        showVisibleOffImg =
            ContextCompat.getDrawable(
                context, R.drawable.baseline_visibility_off_24
            ) as Drawable
        showVisibleOffImg.setColorFilter(R.color.onBgInput, PorterDuff.Mode.MULTIPLY)

        showLockImg =
            ContextCompat.getDrawable(
                context, R.drawable.baseline_lock_24
            ) as Drawable
        showLockImg.setColorFilter(R.color.onBgInput, PorterDuff.Mode.MULTIPLY)

        trueBackground =
            ContextCompat.getDrawable(
                context, R.drawable.custom_input
            ) as Drawable
        falseBackground =
            ContextCompat.getDrawable(
                context, R.drawable.custom_input_false
            ) as Drawable

        background = trueBackground
        setPadding(30, 45, 30, 45)

        setButtonDrawables()
        setOnTouchListener(this)

        addTextChangedListener(onTextChanged = {s, _, _, _ ->
            if (s.toString().length < 8) {
                background = falseBackground
                setError(context.getString(R.string.error_password), null)
            } else {
                background = trueBackground
                error = null
            }
        })
    }

    private fun showVisibleOnButton(){
        setButtonDrawables(endOfTheText = showVisibleOnImg)
    }
    private fun showVisibleOffButton(){
        setButtonDrawables(endOfTheText = showVisibleOffImg)
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable = showLockImg,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable = showVisibleOffImg,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val visibleButtonStart: Float
            val visibleButtonEnd: Float
            var isVisibleButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                visibleButtonEnd = (showVisibleOnImg.intrinsicWidth + paddingStart).toFloat()

                when {
                    event.x < visibleButtonEnd -> isVisibleButtonClicked = true
                }
            } else {
                visibleButtonStart =
                    (width - paddingEnd - showVisibleOnImg.intrinsicWidth).toFloat()

                when {
                    event.x > visibleButtonStart -> isVisibleButtonClicked = true
                }
            }

            if (isVisibleButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        if (isVisibleOnTrue){
                            showVisibleOffButton()
                            transformationMethod = PasswordTransformationMethod.getInstance()
                            setHint(R.string.fake_password_hide)
                            isVisibleOnTrue = false
                        }else{
                            showVisibleOnButton()
                            transformationMethod = null
                            setHint(R.string.fake_password_show)
                            isVisibleOnTrue = true
                        }

                        return true
                    }

                    else -> return false
                }
            } else return false
        }
        return false
    }
}