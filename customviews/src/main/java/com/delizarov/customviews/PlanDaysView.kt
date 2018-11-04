package com.delizarov.customviews

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind

class PlanDaysView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var _maxDays: Int = MAX_DAYS_DEFAULT

    private var _selected: Int = SELECTED_DEFAULT

    var amount: Int = 0
        get() = values[_selected] as Int
        set(value) {

            if (_selected == value)
                return

            field = values[_selected] as Int

            onAmountChangedListener(field)
        }

    var onAmountChangedListener: (Int) -> Unit = {}

    private var values = mutableListOf<Number>() // Int не работает с SortedList из-за каста (int[])

    private val days: RecyclerView by bind(R.id.days)

    private val adapter = object : SortedListAdapter<Number>(Number::class.java, Comparator { o1, o2 ->
        if (o1 is Int && o2 is Int)
            return@Comparator Integer.compare(o1, o2)
        else return@Comparator 0
    }) {
        override fun onBindViewHolder(holder: ViewHolderBase<Number>, position: Int) {

            holder.itemView.setOnClickListener {

                if (_selected == position)
                    return@setOnClickListener

                (holder as DayViewHolder).checked = true
                (days.findViewHolderForAdapterPosition(_selected) as DayViewHolder).checked = false // потенциально вызовет угрозу если если вьюх будет слишком много

                _selected = position
            }

            holder.bind(get(position))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Number> {

            val v = LayoutInflater.from(context).inflate(R.layout.viewholder_day, parent, false)

            return DayViewHolder(v, viewType == TYPE_CHECKED)
        }

        override fun getItemViewType(position: Int): Int = if (position == _selected) TYPE_CHECKED else TYPE_UNCHECKED
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.PlanDaysView, defStyleAttr, 0)

        parseAttributes(a)

        inflateView()

        a.recycle()
    }

    private fun parseAttributes(array: TypedArray) {

        _maxDays = array.getInt(R.styleable.PlanDaysView_pdv_max_plan_days, MAX_DAYS_DEFAULT)
        _selected = array.getInt(R.styleable.PlanDaysView_pdv_max_plan_days, SELECTED_DEFAULT)

        values = (1.._maxDays).toMutableList()
    }

    private fun inflateView() {

        val inflater = LayoutInflater.from(context)

        inflater.inflate(R.layout.plan_days_view, this, true)

        days.layoutManager = GridLayoutManager(context, _maxDays)
        days.adapter = adapter

        adapter.addAll(values)
    }

    companion object {

        private const val TYPE_CHECKED = 1
        private const val TYPE_UNCHECKED = 0

        private const val MAX_DAYS_DEFAULT = 7
        private const val SELECTED_DEFAULT = 3
    }
}

internal class DayViewHolder(itemView: View, checked: Boolean) : ViewHolderBase<Number>(itemView) {

    private var caption: TextView = itemView.findViewById(R.id.caption)

    var checked: Boolean = checked
        set(value) {

            if (field == value)
                return

            field = value

            if (field)
                setChecked()
            else
                setUnchecked()
        }

    override fun bind(item: Number) {

        val caption = itemView.findViewById<TextView>(R.id.caption)

        caption.text = item.toString()

        if (checked)
            setChecked()
        else
            setUnchecked()
    }

    private fun setChecked() {

        val resources = itemView.context.resources

        caption.setBackgroundResource(R.drawable.numbered_radio_button_checked_background)
        caption.setTextColor(resources.getColor(R.color.white))
    }

    private fun setUnchecked() {

        val resources = itemView.context.resources

        caption.setBackgroundResource(R.drawable.numbered_radio_button_unchecked_background)
        caption.setTextColor(resources.getColor(R.color.seafoam_blue))
    }


}