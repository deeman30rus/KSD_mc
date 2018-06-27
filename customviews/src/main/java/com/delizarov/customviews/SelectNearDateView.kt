package com.delizarov.customviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.PopupMenu
import android.widget.TextView
import com.delizarov.common.ui.x.bind
import org.joda.time.DateTime

class SelectNearDateView(ctx: Context?, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context?, attrs: AttributeSet?) : this(ctx, attrs, 0)
    constructor(ctx: Context?) : this(ctx, null, 0)

    private val caption: TextView by bind(R.id.caption)

    var onSelectedDateChangeListener: (DateTime?) -> Unit = {

    }

    private var selectedDate: DateTime? = null
        set(value) {
            if (value == field)
                return

            field = value
            caption.text = value?.toString(DATE_FORMAT)
            onSelectedDateChangeListener(value)
        }

    var entries: List<DateTime> = listOf()
        set(value) {

            if (value.isEmpty())
                caption.text = ""
            else {
                field = value
                selectedDate = value[0]
            }
        }

    init {

        val inflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.select_near_date_view, this, true)

        val ta = ctx.obtainStyledAttributes(attrs, R.styleable.SelectNearDateView)

        val backgroundColor = ta.getColor(R.styleable.SelectNearDateView_snd_background, ctx.resources.getColor(R.color.m_amber_500))

        ta.recycle()

        setBackgroundColor(backgroundColor)

        setOnClickListener {

            if (entries.isEmpty())
                return@setOnClickListener

            val popupMenu = PopupMenu(ctx, this)

            val group = 0
            var index = 0

            for (entry in entries)
                popupMenu.menu.add(group, index, index++, entry.toString(DATE_FORMAT))

            popupMenu.setOnMenuItemClickListener {

                selectedDate = entries[it.itemId]
                true
            }

            popupMenu.show()
        }
    }

    private companion object {

        const val DATE_FORMAT = "dd MMMMM"
    }
}