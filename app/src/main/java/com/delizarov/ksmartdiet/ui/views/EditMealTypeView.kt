package com.delizarov.ksmartdiet.ui.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.MealType


class EditMealTypesView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {

    var values: List<MealType> = emptyList()
        set(value) {

            field = value
            adapter.clear()
            adapter.addAll(field)
        }

    var onDataSetChangedListener: (EditMealTypesView, Boolean) -> Unit = { _, _ -> }

    var isDataSetCorrect: Boolean = false
        private set(value) {

            if (field == value)
                return

            field = value
            onDataSetChangedListener(this, field)
        }


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val mealTypes: RecyclerView by bind(R.id.meal_types_list)

    private val newMealTypeButton: View by bind(R.id.new_meal_type)

    private val adapter = object : SortedListAdapter<MealType>(MealType::class.java, Comparator { s1, s2 -> Integer.compare(s1.order, s2.order) }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<MealType> {

            val inflater = LayoutInflater.from(context)

            return MealTypeViewHolder(inflater.inflate(R.layout.viewholder_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolderBase<MealType>, position: Int) {

            holder.bind(get(position))
            (holder as MealTypeViewHolder).onDismissClickListener = { _, item ->

                remove(item)
                updateDataSetCorrectness()
            }
            holder.onMealTypeNameChangedListener = { _, _ -> updateDataSetCorrectness() }
        }
    }

    init {

        inflateView()
    }

    private fun inflateView() {

        val inflater = LayoutInflater.from(context)

        inflater.inflate(R.layout.edit_meal_type_view, this, true)

        newMealTypeButton.setOnClickListener {

            val order = if (adapter.itemCount == 0) 0 else {
                adapter.get(adapter.itemCount - 1).order + 1
            }

            adapter.add(MealType("", order))

            updateDataSetCorrectness()
        }

        mealTypes.adapter = adapter
    }

    private fun updateDataSetCorrectness() {

        var isCorrect = adapter.itemCount > 1

        for (i in 0 until adapter.itemCount - 1) {
            if (adapter.get(i).name.isNotEmpty())
                continue

            isCorrect = false
            break
        }

        isDataSetCorrect = isCorrect
    }
}

internal class MealTypeViewHolder(itemView: View) : ViewHolderBase<MealType>(itemView) {
    override fun bind(item: MealType) {

        val editText = itemView.findViewById<EditText>(R.id.meal_type_name)

        editText.setText(item.name)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                item.name = (s ?: "").toString()

                onMealTypeNameChangedListener(this@MealTypeViewHolder, item)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        itemView.findViewById<ImageView>(R.id.remove_item).setOnClickListener {
            onDismissClickListener(this, item)
        }

    }

    var onDismissClickListener: (MealTypeViewHolder, MealType) -> Unit = { _, _ -> }

    var onMealTypeNameChangedListener: (MealTypeViewHolder, MealType) -> Unit = { _, _ -> }
}