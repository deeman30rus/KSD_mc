package com.delizarov.ksmartdiet.ui.viewholders

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

    private val marker: AppCompatImageView by bind(R.id.marker)
    private val name: TextView by bind(R.id.ingredient_name)
    private val amount: TextView by bind(R.id.ingredient_amount)
    private val units: TextView by bind(R.id.ingredient_units)

    override fun bind(item: Ingredient) {

        val background = marker.background

        if (background is ShapeDrawable) {
            background.paint.color = item.grocery.name.decodedColor
        } else if (background is GradientDrawable) {
            background.setColor(item.grocery.name.decodedColor)
        }

        name.text = item.grocery.name

        if (item.unit === Unit.Optional) {
            amount.text = ""
        } else {

            val df = DecimalFormat()
            df.maximumFractionDigits = 2
            df.minimumFractionDigits = 0
            df.isGroupingUsed = false

            amount.text = df.format(item.amount)
        }

        displayUnits(item.unit) // переделать на extension функцию
    }

    private fun displayUnits(unit: com.delizarov.ksmartdiet.domain.models.Unit) {

        val unitStr = when {
            unit === Unit.Kilo -> "кг."
            unit === Unit.Gram -> "г."
            unit === Unit.Liter -> "л."
            unit === Unit.MilliLiter -> "мл."
            unit === Unit.TeaSpoon -> "ч. л."
            unit === Unit.TableSpoon -> "ст. л."
            unit === Unit.Piece -> "шт."
            else -> "по вусу"
        }

        units.text = unitStr
    }
}