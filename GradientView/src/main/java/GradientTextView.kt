package com.sreenath.gradientview

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.sreenath.gradientview.R

class GradientTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var gradient: LinearGradient? = null
    private var startColor = context.getColor(R.color.rose_violet_start_color)
    private var centerColor: Int? = null
    private var endColor = context.getColor(R.color.rose_violet_end_color)


    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.GradientTextView, 0, 0).apply {
            try {
                startColor = getColor(R.styleable.GradientTextView_startColor, startColor)
                if (hasValue(R.styleable.GradientTextView_centerColor)) {
                    centerColor = getColor(R.styleable.GradientTextView_centerColor, -1)
                }
                endColor = getColor(R.styleable.GradientTextView_endColor, endColor)
            } finally {
                recycle()
            }
        }
        applyGradient()
    }

    private fun applyGradient() {
        if (width > 0 && height > 0) {
            val colors: IntArray = centerColor?.let {
                intArrayOf(startColor, it, endColor)
            } ?: intArrayOf(startColor, endColor)

            gradient = LinearGradient(
                0f, 0f, width.toFloat(), 0f,
                colors,
                null,
                Shader.TileMode.CLAMP
            )
            paint.shader = gradient
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            applyGradient()
        }
    }


}