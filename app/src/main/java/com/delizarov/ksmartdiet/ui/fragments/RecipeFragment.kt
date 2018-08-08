package com.delizarov.ksmartdiet.ui.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Direction
import com.delizarov.ksmartdiet.domain.models.Ingredient
import com.delizarov.ksmartdiet.domain.models.Recipe
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.ui.viewholders.IngredientViewHolder
import com.delizarov.ksmartdiet.ui.viewholders.RecipeDirectionViewHolder
import com.delizarov.navigation.ScreenKeyHolder
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.flexbox.FlexboxLayout
import com.squareup.picasso.Picasso
import fisk.chipcloud.ChipCloud
import fisk.chipcloud.ChipCloudConfig
import java.util.*

class RecipeFragment : BaseFragment(), ScreenKeyHolder {

    private lateinit var recipe: Recipe

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override val screenKey = ScreenKeys.RecipeScreenKey

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_recipe, container, false)

        val tabsPager = v.findViewById<ViewPager>(R.id.tabs)
        tabsPager.adapter = RecipeFragmentTabsAdapter(context!!, recipe)

        val tabHeaders = v.findViewById<TabLayout>(R.id.tab_header)
        tabHeaders.setupWithViewPager(tabsPager)

        repeat(RecipeTab.values().size) { pos ->
            tabHeaders.getTabAt(pos)?.setIcon(RecipeTab.values()[pos].iconResId)
        }

        val selectedColor = context?.resources?.getColor(R.color.recipe_selected_tab_color) ?: 0
        val unselectedColor = context?.resources?.getColor(R.color.recipe_unselected_tab_color) ?: 0

        tabHeaders.addOnTabSelectedListener(HighlightTabSelectedListener(tabsPager, selectedColor, unselectedColor))
        tabHeaders.getTabAt(0)?.icon?.setIconColor(selectedColor)

        val recipeImage = v.findViewById<ImageView>(R.id.recipe_image)

        Picasso
                .get()
                .load(recipe.mainPictureURI)
                .error(R.drawable.placeholder_recipe_picture)
                .placeholder(R.drawable.placeholder_recipe_picture)
                .into(recipeImage)

        val toolbar = v.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = recipe.title

        return v
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {

        lateinit var recipe: Recipe

        fun build(): RecipeFragment {

            val fragment = RecipeFragment()

            fragment.recipe = this.recipe

            return fragment
        }
    }
}


enum class RecipeTab(
        @DrawableRes val iconResId: Int,
        @LayoutRes val layoutId: Int,
        val recipeRendrer: (Context, Int, ViewGroup, Recipe) -> ViewGroup
) {

    Details(R.drawable.icon_info_grey_500, R.layout.view_recipe_details, ::recipeInfoRenderer),
    Ingredients(R.drawable.icon_apple_grey_500, R.layout.view_recipe_ingredients, ::recipeIngredientsRenderer),
    Directions(R.drawable.icon_list_grey_500, R.layout.view_recipe_directions, ::recipeDirectionsRenderer),
    VideoInstruction(R.drawable.icon_video_grey_500, R.layout.view_recipe_video_instructions, ::recipeVideoRenderer);
}

internal class RecipeFragmentTabsAdapter(
        private val ctx: Context,
        private val recipe: Recipe
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = RecipeTab.values().size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val tab = RecipeTab.values()[position]

        val layout = tab.recipeRendrer(ctx, tab.layoutId, container, recipe)

        container.addView(layout)

        return layout
    }

    override fun getPageTitle(position: Int): CharSequence? = null

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)
}


internal class HighlightTabSelectedListener(
        pager: ViewPager,
        private val selectedColor: Int,
        private val unselectedColor: Int

) : TabLayout.ViewPagerOnTabSelectedListener(pager) {

    override fun onTabSelected(tab: TabLayout.Tab?) {
        super.onTabSelected(tab)

        tab?.icon?.setIconColor(selectedColor)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        super.onTabUnselected(tab)

        tab?.icon?.setIconColor(unselectedColor)
    }
}

internal fun Drawable.setIconColor(color: Int) = setColorFilter(color, PorterDuff.Mode.SRC_IN)

internal fun recipeInfoRenderer(ctx: Context, @LayoutRes layoutId: Int, container: ViewGroup, recipe: Recipe): ViewGroup {

    val inflater = LayoutInflater.from(ctx)
    val layout = inflater.inflate(layoutId, container, false)

    val description = layout.findViewById<TextView>(R.id.description)
    description.text = recipe.description

    // tags
    val tags = layout.findViewById<FlexboxLayout>(R.id.tags)

    val config = ChipCloudConfig()
            .selectMode(ChipCloud.SelectMode.none)
            .uncheckedChipColor(ctx.resources.getColor(R.color.colorPrimary))
            .uncheckedTextColor(ctx.resources.getColor(R.color.white))
            .useInsetPadding(true)

    val chipCloud = ChipCloud(ctx, tags, config)

    for (tag in recipe.tags)
        chipCloud.addChip(tag)

    //pie chart
    val pieChart = layout.findViewById<PieChart>(R.id.chart)
    val entries = ArrayList<PieEntry>()

    entries.add(PieEntry(recipe.proteins.toFloat(), ctx.resources?.getString(R.string.proteins)))
    entries.add(PieEntry(recipe.triglycerides.toFloat(), ctx.resources?.getString(R.string.triglycerides)))
    entries.add(PieEntry(recipe.carbohydrates.toFloat(), ctx.resources?.getString(R.string.carbohydrates)))

    val set = PieDataSet(entries, ctx.resources?.getString(R.string.food_energy))

    set.colors = listOf(
            ctx.resources?.getColor(R.color.recipe_chart_proteins_color),
            ctx.resources?.getColor(R.color.recipe_chart_triglycerides_color),
            ctx.resources?.getColor(R.color.recipe_chart_carbohydrates_color)
    )
    set.valueTextSize = 18f
    set.valueTextColor = -0x1

    val data = PieData(set)

    pieChart.data = data
    pieChart.centerText = ctx.resources?.getString(R.string.calories, recipe.calories)
    pieChart.setCenterTextSize(24f)
    pieChart.description = null
    pieChart.isHighlightPerTapEnabled = false
    pieChart.legend.isEnabled = false

    pieChart.invalidate() // refresh

    return layout as ViewGroup
}

internal fun recipeIngredientsRenderer(ctx: Context, @LayoutRes layoutId: Int, container: ViewGroup, recipe: Recipe): ViewGroup {

    val inflater = LayoutInflater.from(ctx)
    val layout = inflater.inflate(layoutId, container, false)

    val ingredients = layout.findViewById<RecyclerView>(R.id.ingredients)

    val adapter = IngredientAdapter()
    ingredients.adapter = adapter
    adapter.addAll(recipe.ingredients)

    return layout as ViewGroup
}

internal fun recipeDirectionsRenderer(ctx: Context, @LayoutRes layoutId: Int, container: ViewGroup, recipe: Recipe): ViewGroup {
    val inflater = LayoutInflater.from(ctx)
    val layout = inflater.inflate(layoutId, container, false)

    val directions = layout.findViewById<RecyclerView>(R.id.directions)

    val adapter = DirectionsAdapter()
    directions.adapter = adapter
    adapter.addAll(recipe.directions)

    return layout as ViewGroup
}

internal fun recipeVideoRenderer(ctx: Context, @LayoutRes layoutId: Int, container: ViewGroup, recipe: Recipe): ViewGroup {

    val inflater = LayoutInflater.from(ctx)
    val layout = inflater.inflate(layoutId, container, false)

    return layout as ViewGroup
}

internal class IngredientAdapter : SortedListAdapter<Ingredient>(Ingredient::class.java, kotlin.Comparator { i1, i2 -> i1.grocery.name.compareTo(i2.grocery.name, false) }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Ingredient> {

        val inflater = LayoutInflater.from(parent.context)

        return IngredientViewHolder(inflater.inflate(R.layout.viewholder_ingredient, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderBase<Ingredient>, position: Int) = holder.bind(get(position))

}

internal class DirectionsAdapter : SortedListAdapter<Direction>(Direction::class.java, kotlin.Comparator { d1, d2 -> d1.ordinal.compareTo(d2.ordinal) }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Direction> {

        val inflater = LayoutInflater.from(parent.context)

        return RecipeDirectionViewHolder(inflater.inflate(R.layout.viewholder_direction, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolderBase<Direction>, position: Int) = holder.bind(get(position))

}