package com.delizarov.customviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.delizarov.common.x.ui.bind
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


class DateStepper(ctx: Context?, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context?, attrs: AttributeSet?) : this(ctx, attrs, 0)
    constructor(ctx: Context?) : this(ctx, null, 0)

    private val back: ImageView by bind(R.id.back)
    private val month: TextView by bind(R.id.date_month)
    private val day: TextView by bind(R.id.date_day)
    private val forward: ImageView by bind(R.id.forward)

    private var startDate = today()

    var maxDays = MAX_DAYS_DEFAULT
        set(value) {

            if (value == field)
                return

            field = value
            maxDate = startDate.plusDays(value)

            selectedDate = SOME_DATE
            selectedDate = startDate
        }

    var onSelectedDateChangedListener: (DateTime?) -> Unit = {

    }

    var selectedDate: DateTime = SOME_DATE // любая дата в далёком прошлом, чтобы при первой установке инициировать вызов selected month changed
        private set(value) {
            if (value == field)
                return

            field = value

            if (value == SOME_DATE)
                return

            month.text = value.toString(DATE_MONTH_FORMAT) ?: ""
            day.text = value.toString(DATE_DAY_FORMAT) ?: ""

            if (value == startDate)
                back.visibility = View.INVISIBLE
            else
                back.visibility = View.VISIBLE

            if (value == maxDate.minusDays(1))
                forward.visibility = View.INVISIBLE
            else
                forward.visibility = View.VISIBLE

            onSelectedDateChangedListener(value)
        }

    private var maxDate = startDate.plusDays(MAX_DAYS_DEFAULT)

    init {
        val inflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.date_stepper, this, true)

        forward.setOnClickListener {

            val nextDate = selectedDate.plusDays(1)
            if (nextDate >= maxDate)
                return@setOnClickListener

            selectedDate = nextDate
        }

        back.setOnClickListener {
            val nextDate = selectedDate.minusDays(1)

            if (nextDate < startDate)
                return@setOnClickListener

            selectedDate = nextDate
        }

        val ta = ctx.obtainStyledAttributes(attrs, R.styleable.DateStepper)

        maxDays = ta.getInt(R.styleable.DateStepper_max_days, MAX_DAYS_DEFAULT)
        val startDateStr = ta.getString(R.styleable.DateStepper_start_date)

        startDate = if (startDateStr == "now")
            today()
        else {
            val formatter = DateTimeFormat.forPattern(DATE_MONTH_FORMAT)
            formatter.parseDateTime(startDateStr).withTimeAtStartOfDay()
        }

        ta.recycle()

        selectedDate = startDate
    }

    private fun today() = DateTime().withTimeAtStartOfDay()

    private companion object {

        const val DATE_MONTH_FORMAT = "MMMMM"
        const val DATE_DAY_FORMAT = "dd"

        const val MAX_DAYS_DEFAULT = 4

        val SOME_DATE = DateTime("2004-12-13T21:39:45.618-08:00") // служит для обновления даты
    }
}