package com.myhan.chat_client_practice.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.TextView
import com.myhan.chat_client_practice.R

class BubbleTextView(context: Context, attributeSet: AttributeSet): TextView(context, attributeSet) {
    private var bubbleColor: Int
    private var bubbleRadius: Float

    init {
        val arr = context.obtainStyledAttributes(attributeSet, R.styleable.bubble_textview)
        bubbleColor = arr.getColor(R.styleable.bubble_textview_bubbleColor, 0)
        bubbleRadius = arr.getDimension(R.styleable.bubble_textview_bubbleRadius, 0f)
        arr.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint().apply { color = bubbleColor; }
        val rectf = RectF(0f, 0f, width.toFloat(), height.toFloat())

        canvas?.drawRoundRect(rectf, bubbleRadius, bubbleRadius, paint)
        super.onDraw(canvas)
    }
}