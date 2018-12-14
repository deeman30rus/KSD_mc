package com.delizarov.ksmartdiet.ui.viewholders

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.TextView
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.decodedColor
import com.delizarov.common.x.ui.bind
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Ingredient
import com.delizarov.ksmartdiet.domain.models.Unit
import java.text.DecimalFormat


class IngredientViewHolder(itemView: View) : ViewHolderBase<Ingredient>(itemView) {

    private val info: TextView by bind(R.id.ingredient_info)

    override fun bind(item: Ingredient) {

        val stringBuilder = StringBuilder()

        stringBuilder.append("${item.grocery.name} ")

        if (item.unit != Unit.Optional) {

            val df = DecimalFormat()
            df.maximumFractionDigits = 2
            df.minimumFractionDigits = 0
            df.isGroupingUsed = false

            stringBuilder.append(" - ${df.format(item.amount)}")
        }

        stringBuilder.append(item.unit.toResourceString(itemView.context))

        info.text = stringBuilder.toString()
    }
}

private fun Unit.toResourceString(ctx: Context): String {

    val stringId = mapOf(
            Unit.Kilo to R.string.kilo,
            Unit.Gram to R.string.gram,
            Unit.Liter to R.string.liter,
            Unit.MilliLiter to R.string.milliliter,
            Unit.TeaSpoon to R.string.teaspoon,
            Unit.TableSpoon to R.string.tablespoon,
            Unit.Piece to R.string.piece,
            Unit.Optional to R.string.optional
    )[this]

    return ctx.resources?.getString(stringId ?: R.string.optional) ?: ""
}