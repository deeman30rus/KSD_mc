package com.delizarov.ksmartdiet.ui.viewholders

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.delizarov.common.transformations.CircleTransform
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.presentation.DietPresenter
import com.github.clans.fab.FloatingActionButton
import com.google.android.flexbox.FlexboxLayout
import com.squareup.picasso.Picasso
import fisk.chipcloud.ChipCloud
import fisk.chipcloud.ChipCloudConfig


class MealViewHolder(
        itemView: View,
        private val presenter: DietPresenter,
        val onItemClickListener: (Meal) -> Unit
) : ViewHolderBase<Meal>(itemView) {

    private val mealType: TextView by bind(R.id.meal_type)
    private val recipeTitle: TextView by bind(R.id.recipe_title)
    private val recipeTags: FlexboxLayout by bind(R.id.tags)
    private val recipPicture: ImageView by bind(R.id.recipe_picture)

    private val recipeCookTime: TextView by bind(R.id.cook_time)
    private val recipeCalories: TextView by bind(R.id.calories)

    private val suggest: FloatingActionButton by bind(R.id.suggest)
    private val pickManually: FloatingActionButton by bind(R.id.pick_meal)


    override fun bind(meal: Meal) {

        mealType.text = meal.type.name
        recipeTitle.text = meal.recipe.title

        val config = ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.none)
                .uncheckedChipColor(Color.parseColor("#FFFF9800"))
                .uncheckedTextColor(Color.parseColor("#ffffff"))
                .useInsetPadding(true)

        val chipCloud = ChipCloud(itemView.context, recipeTags, config)
        for (tag in meal.recipe.tags)
            chipCloud.addChip(tag)

        suggest.setOnClickListener { presenter.onSuggestButtonClicked(meal) }
        pickManually.setOnClickListener { presenter.onPickManuallyButtonClicked() }

        recipeCookTime.text = "${meal.recipe.cookTime} мин."
        recipeCalories.text = "${meal.recipe.calories} ккал"

        Picasso
                .get()
                .load(meal.recipe.mainPictureURI)
                .error(R.drawable.placeholder_recipe_picture)
                .placeholder(R.drawable.placeholder_recipe_picture)
                .into(recipPicture)

        itemView.setOnClickListener {
            onItemClickListener(meal)
        }
    }
}