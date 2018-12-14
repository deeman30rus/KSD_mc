package com.delizarov.customviews

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
import android.widget.TextView
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind
import com.delizarov.customviews.models.OrderedEntry

class ExtendableList(ctx: Context?, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context?, attrs: AttributeSet?) : this(ctx, attrs, 0)
    constructor(ctx: Context?) : this(ctx, null, 0)

    var title: String
    set(value) {
        titleLabel.text = value
    }
    get() = titleLabel.text.toString()

    var error: String
    get() = errorLabel.text.toString()
    set(value) {

        if (value.isEmpty()) {
            errorLabel.text = ""
            errorLabel.visibility = View.GONE
            errorLabelDecor.visibility = View.GONE
        } else {
            errorLabel.text = value
            errorLabel.visibility = View.VISIBLE
            errorLabelDecor.visibility = View.VISIBLE
        }
    }

    private val entries: RecyclerView by bind(R.id.entries)
    private val newLine: View by bind(R.id.clickable)
    private val titleLabel: TextView by bind(R.id.view_label)
    private val errorLabelDecor : View by bind(R.id.error_label_decor)
    private val errorLabel: TextView by bind(R.id.error_label)

    private val adapter = ExtendableListAdapter()

    init {

        val inflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.extendable_list, this, true)

        val ta = ctx.obtainStyledAttributes(attrs, R.styleable.ExtendableList)
        val titleText = ta.getString(R.styleable.ExtendableList_el_label_text)

        ta.recycle()

        title = titleText

        entries.adapter = adapter
        adapter.addNext()
        clearErrror()

        newLine.setOnClickListener {
            adapter.addNext()
            clearErrror()
        }
    }

    fun getEntries(): List<OrderedEntry> {

        val list = ArrayList<OrderedEntry>()

        for (i in 0 until adapter.itemCount)
            list.add(adapter.get(i).copy())

        return list
    }

    fun clearErrror() { error = "" }
}

internal class ExtendableListAdapter : SortedListAdapter<OrderedEntry>(OrderedEntry::class.java, Comparator { i1, i2 -> Integer.compare(i1.order, i2.order) }) {

    private var order = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vh = ViewHolder(R.layout.viewholder_line, parent)

        vh.onRemoveListener = View.OnClickListener {
            remove(get(vh.adapterPosition))
        }

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolderBase<OrderedEntry>, position: Int) {

        holder.bind(get(position))
    }

    fun addNext() {

        add(OrderedEntry("", order++))
    }
}

internal class ViewHolder(layoutId: Int, parent: ViewGroup) : ViewHolderBase<OrderedEntry>(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    lateinit var onRemoveListener: View.OnClickListener
    private val input: EditText by bind(R.id.input)
    private val remove: ImageView by bind(R.id.icon_remove)

    override fun bind(item: OrderedEntry) {

        input.setText(item.value)

        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                item.value = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        remove.setOnClickListener(onRemoveListener)
    }
}