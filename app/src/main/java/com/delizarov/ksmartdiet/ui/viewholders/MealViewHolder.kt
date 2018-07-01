package com.delizarov.ksmartdiet.ui.viewholders

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.ui.x.bind
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Meal
import com.google.android.flexbox.FlexboxLayout
import fisk.chipcloud.ChipCloud
import fisk.chipcloud.ChipCloudConfig

class MealViewHolder(itemView: View) : ViewHolderBase<Meal>(itemView) {

    private val mealType: TextView by bind(R.id.meal_type)
    private val recipeTitle: TextView by bind(R.id.recipe_title)
    private val recipeTags: FlexboxLayout by bind(R.id.tags)

    private val recipeCookTime: TextView by bind(R.id.cook_time)
    private val recipeCalories: TextView by bind(R.id.calories)

    override fun bind(meal: Meal) {

        mealType.text = meal.type.name
        recipeTitle.text = meal.recipe.title

        val config = ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.none)
                .uncheckedChipColor(Color.parseColor("#FFFF9800"))
                .uncheckedTextColor(Color.parseColor("#ffffff"))
                .useInsetPadding(true)

        val chipCloud = ChipCloud(itemView.context , recipeTags, config)
        for (tag in meal.recipe.tags)
            chipCloud.addChip(tag)

        recipeCookTime.text = "${meal.recipe.cookTime} мин."
        recipeCalories.text = "${meal.recipe.calories} ккал"
    }
}