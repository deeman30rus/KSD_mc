package com.delizarov.ksmartdiet.ui.viewholders

import android.view.View
import android.widget.TextView
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Direction

class RecipeDirectionViewHolder(itemView: View) : ViewHolderBase<Direction>(itemView) {

    private val order: TextView by bind(R.id.direction_order)

    private val action: TextView by bind(R.id.direction_action)

    override fun bind(item: Direction) {

        order.text = itemView.context.resources?.getString(R.string.recipe_direction_order_text, item.ordinal)

        action.text = item.action
    }
}