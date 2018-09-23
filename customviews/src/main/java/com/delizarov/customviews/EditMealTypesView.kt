package com.delizarov.customviews

import android.content.Context
import android.content.res.TypedArray
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

class EditMealTypesView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(context, attrs, defStyleAttr) {

    val values : List<String>
    get() {
        val r = ArrayList<String>()

        for (i in 0 until adapter.itemCount - 1)
            r.add(adapter.get(i).value)

        return r
    }

    var onDataSetChangedListener: (EditMealTypesView, Boolean) -> Unit = {_, _ -> }

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

    private val adapter = object : SortedListAdapter<Item>(Item::class.java, Comparator { s1, s2 -> Integer.compare(s1.order, s2.order) }) {

        private var index = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Item> {

            val inflater = LayoutInflater.from(context)

            return if (viewType == TYPE_ITEM)
                ItemViewHolder(inflater.inflate(R.layout.viewholder_item, parent, false))
            else
                NewItemButtonViewHolder(inflater.inflate(R.layout.viewholder_new_item_button, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolderBase<Item>, position: Int) {

            if (position == itemCount - 1) {

                holder.itemView.setOnClickListener {
                    add(Item(index++, ""))
                    updateDataSetCorrectness()
                }

                return
            }

            holder.bind(get(position))
            (holder as ItemViewHolder).onDismissClickListener = { _, item ->

                remove(item)
                updateDataSetCorrectness()
            }
            holder.onMealTypeNameChangedListener = { _, _ -> updateDataSetCorrectness()}
        }

        override fun getItemViewType(position: Int): Int = if (position == itemCount - 1) TYPE_BUTTON_NEW else TYPE_ITEM
    }

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.EditMealTypesView, defStyleAttr, 0)

        try {

            parseAttributes(a)

            inflateView()

        } finally {

            a.recycle()
        }
    }

    private fun parseAttributes(array: TypedArray) {}

    private fun inflateView() {

        val inflater = LayoutInflater.from(context)

        inflater.inflate(R.layout.edit_meal_type_view, this, true)

        adapter.add(BUTTON_ITEM)

        mealTypes.adapter = adapter
    }

    private fun updateDataSetCorrectness() {

        var isCorrect = adapter.itemCount > 1

        for (i in 0 until adapter.itemCount - 1) {
            if (adapter.get(i).value.isNotEmpty())
                continue

            isCorrect = false
            break
        }

        isDataSetCorrect = isCorrect
    }

    companion object {

        private const val TYPE_ITEM = 0
        private const val TYPE_BUTTON_NEW = 1

        private val BUTTON_ITEM = Item(Int.MAX_VALUE, "")
    }
}

internal class ItemViewHolder(itemView: View) : ViewHolderBase<Item>(itemView) {
    override fun bind(item: Item) {

        val editText = itemView.findViewById<EditText>(R.id.meal_type_name)

        editText.setText(item.value)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                item.value = (s ?: "").toString()

                onMealTypeNameChangedListener(this@ItemViewHolder, item)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })

        itemView.findViewById<ImageView>(R.id.remove_item).setOnClickListener {
            onDismissClickListener(this, item)
        }

    }

    var onDismissClickListener: (ItemViewHolder, Item) -> Unit = { _, _ -> }

    var onMealTypeNameChangedListener: (ItemViewHolder, Item) -> Unit = { _, _ -> }
}

internal class NewItemButtonViewHolder(itemView: View) : ViewHolderBase<Item>(itemView) {

    override fun bind(item: Item) {}
}

internal data class Item(
        val order: Int,
        var value: String
)