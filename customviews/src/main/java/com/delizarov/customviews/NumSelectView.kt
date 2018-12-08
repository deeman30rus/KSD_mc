package com.delizarov.customviews

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class NumSelectView(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    private val views = mutableListOf<TextView>()

    private var selected: Int = 0
        set(value) {

            if (field == value)
                return

            views[field].setUnselected()
            field = value
            views[field].setSelected()
        }

    private var isMarginsMeasured = false

    var amount: Int = 0
        set(value) {

            field = value

            isMarginsMeasured = false

            render()

            requestLayout()
        }

    var selectedNum: Int
        get() = if (amount == 0) -1 else Integer.valueOf(views[selected].text.toString())
        set(value) {

            val pos = views.indexOfFirst { it.text == value.toString() }
            selected = pos
        }

    var onNumberSelected: (Int) -> Unit = {}

    init {

        parseAttrs(attrs)

        inflate()

        render()
    }

    private fun inflate() {
        val inflater = LayoutInflater.from(context)

        inflater.inflate(R.layout.view_num_select, this, true)
    }

    private fun render() {

        if (amount == 0)
            return

        val inflater = LayoutInflater.from(context)

        for (i in 0 until amount) {

            val tv = inflater.inflate(R.layout.view_day, this, false) as TextView

            tv.id = i
            tv.text = (i + 1).toString()

            tv.setUnselected()

            tv.setOnClickListener {

                selected = i
                onNumberSelected.invoke(i + 1)
            }

            views.add(tv)
            addView(tv)
        }

        views[selected].setSelected()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (views.isEmpty())
            return

        val first = views.first()
        val last = views.last()

        (first.layoutParams as MarginLayoutParams).marginStart = 16
        (last.layoutParams as MarginLayoutParams).marginEnd = 16

        if (views.size == 1)
            return

        val marginBetween = width - (first.width * views.size + 32) / (views.size - 1)

        repeat(views.size - 1) { i ->
            (views[i].layoutParams as MarginLayoutParams).marginEnd = marginBetween
        }

        if (!isMarginsMeasured) {
            isMarginsMeasured = true
            requestLayout()
        }
    }

    private fun parseAttrs(attrs: AttributeSet?) {

        if (attrs == null)
            return

        val ta = context.obtainStyledAttributes(attrs, R.styleable.NumSelectView)

        amount = ta.getInt(R.styleable.NumSelectView_nsv_amount, 0)

        ta.recycle()
    }
}

private fun TextView.setSelected() {

    setTextColor(ContextCompat.getColor(context, R.color.white))
    background = ContextCompat.getDrawable(context, R.drawable.numbered_radio_button_checked_background)
}

private fun TextView.setUnselected() {

    setTextColor(ContextCompat.getColor(context, R.color.seafoam_blue))
    background = ContextCompat.getDrawable(context, R.drawable.numbered_radio_button_unchecked_background)
}