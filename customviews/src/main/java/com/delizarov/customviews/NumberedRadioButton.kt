package com.delizarov.customviews

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.delizarov.common.x.ui.bind

class NumberedRadioButton(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr), RadioCheckable {

    private val captionView: TextView by bind(R.id.caption)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.NumberedRadioButton, defStyleAttr, 0)

        try {
            parseAttributes(a)
            inflateView()
        } finally {
            a.recycle()
        }
    }

    private val onCheckedChangeListeners = mutableListOf<OnCheckedChangeListener>()

    override fun addOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        onCheckedChangeListeners.add(listener)
    }

    override fun removeCheckedChangeListener(listener: OnCheckedChangeListener) {
        onCheckedChangeListeners.remove(listener)
    }

    private var _caption: String = ""
    var caption: String
        get() = _caption
        set(value) {
            _caption = value
            captionView.text = _caption

            render()
        }

    private var _checked: Boolean = false
    override var checked: Boolean
        get() = _checked
        set(value) {

            if (_checked == value)
                return

            _checked = value

            render()

            for (l in onCheckedChangeListeners)
                l.onCheckedChange(this, _checked)
        }

    override fun toggle() {

        checked = !checked
    }

    private fun parseAttributes(array: TypedArray) {

        _checked = array.getBoolean(R.styleable.NumberedRadioButton_nrb_checked, false)
        _caption = array.getString(R.styleable.NumberedRadioButton_nrb_caption)
    }

    private fun inflateView() {

        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.numbered_radio_button, this, true)

        setOnClickListener {
            toggle()
        }

        caption = _caption
        checked = _checked
    }

    private fun render() {

        if (checked) {
            captionView.setBackgroundResource(R.drawable.numbered_radio_button_checked_background)
            captionView.setTextColor(context.resources.getColor(R.color.white))
        } else {
            captionView.setBackgroundResource(R.drawable.numbered_radio_button_unchecked_background)
            captionView.setTextColor(context.resources.getColor(R.color.seafoam_blue))
        }
    }
}

interface OnCheckedChangeListener {

    fun onCheckedChange(sender: View, isChecked: Boolean)
}

interface RadioCheckable : Checkable {

    fun addOnCheckedChangeListener(listener: OnCheckedChangeListener)

    fun removeCheckedChangeListener(listener: OnCheckedChangeListener)
}

interface Checkable {

    var checked: Boolean

    fun toggle()
}