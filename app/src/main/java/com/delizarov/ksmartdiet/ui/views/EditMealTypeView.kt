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
import com.delizarov.ksmartdiet.presentation.MealTypesPresenter
import com.delizarov.ksmartdiet.presentation.MealTypesView


class EditMealTypesView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr), MealTypesView {

    private val presenter = MealTypesPresenter()

    var mealTypes: List<MealType>
        get() = presenter.model
        set(value) {
            presenter.model = value.toMutableList()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val mealTypesList: RecyclerView by bind(R.id.meal_types_list)

    private val newMealTypeButton: View by bind(R.id.new_meal_type)

    private val adapter = object : SortedListAdapter<MealType>(MealType::class.java, Comparator { s1, s2 -> Integer.compare(s1.order, s2.order) }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<MealType> {

            val inflater = LayoutInflater.from(context)

            return MealTypeViewHolder(inflater.inflate(R.layout.viewholder_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolderBase<MealType>, position: Int) {

            val mealType = get(position)

            holder.bind(mealType)
            (holder as MealTypeViewHolder).onDismissClickListener = { _, _ ->

                presenter.onRemoveMealTypeClicked(mealType)
            }

            holder.onMealTypeNameChangedListener = { _, newName ->

                presenter.onMealTypeNameChanged(mealType, newName)
            }
        }

        override fun onViewDetachedFromWindow(holder: ViewHolderBase<MealType>) {

            (holder as MealTypeViewHolder).onDismissClickListener = { _, _ -> }
            holder.onMealTypeNameChangedListener = { _, _ -> }
        }
    }

    var onMealTypesChanged: (List<MealType>) -> Unit
        set(value) {
            presenter.onModelChanged = value
        }
        get() = presenter.onModelChanged

    init {
        inflateView()
    }

    override fun addMealType(mealType: MealType) {
        adapter.add(mealType)
    }

    override fun addAllMealTypes(mealTypes: List<MealType>) = adapter.addAll(mealTypes)

    override fun removeMealType(mealType: MealType) {

        adapter.remove(mealType)
    }

    override fun clearMealTypes() = adapter.clear()

    private fun inflateView() {

        val inflater = LayoutInflater.from(context)

        inflater.inflate(R.layout.view_edit_meal_type, this, true)

        newMealTypeButton.setOnClickListener {

            presenter.onAddNewMealTypeClick()
        }

        mealTypesList.adapter = adapter

        presenter.attachView(this)
        presenter.onViewCreated()
    }
}

internal class MealTypeViewHolder(itemView: View) : ViewHolderBase<MealType>(itemView) {
    override fun bind(item: MealType) {

        val editText = itemView.findViewById<EditText>(R.id.meal_type_name)

        editText.setText(item.name)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                onMealTypeNameChangedListener(this@MealTypeViewHolder, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        itemView.findViewById<ImageView>(R.id.remove_item).setOnClickListener {
            onDismissClickListener(this, item)
        }

    }

    var onDismissClickListener: (MealTypeViewHolder, MealType) -> Unit = { _, _ -> }

    var onMealTypeNameChangedListener: (MealTypeViewHolder, String) -> Unit = { _, _ -> }
}