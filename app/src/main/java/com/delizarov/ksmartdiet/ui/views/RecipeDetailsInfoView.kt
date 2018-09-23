package com.delizarov.ksmartdiet.ui.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Direction
import com.delizarov.ksmartdiet.domain.models.Ingredient
import com.delizarov.ksmartdiet.domain.models.Recipe
import com.delizarov.ksmartdiet.ui.viewholders.IngredientViewHolder
import com.delizarov.ksmartdiet.ui.viewholders.RecipeDirectionViewHolder
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.flexbox.FlexboxLayout
import com.squareup.picasso.Picasso
import fisk.chipcloud.ChipCloud
import fisk.chipcloud.ChipCloudConfig
import java.util.*

sealed class RecipeDetailsView(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    var recipe: Recipe? = null
        set(value) {

            if (value == field)
                return

            field = value

            render()
        }

    protected abstract fun render()
}

class RecipeDetailsInfoView(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : RecipeDetailsView(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    private val recipePicture: ImageView by bind(R.id.recipe_picture)
    private val title: TextView by bind(R.id.title)
    private val tags: FlexboxLayout by bind(R.id.tags)
    private val chart: PieChart by bind(R.id.chart)

    init {

        inflate(ctx, R.layout.view_recipe_details_info, this)

        render()
    }

    override fun render() {

        title.text = recipe?.title ?: ""

        Picasso
                .get()
                .load(recipe?.mainPictureURI)
                .error(R.drawable.placeholder_recipe_picture)
                .placeholder(R.drawable.placeholder_recipe_picture)
                .into(recipePicture)

        if (recipe == null)
            return

        // tags
        val config = ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.none)
                .uncheckedChipColor(context.resources.getColor(R.color.colorPrimary))
                .uncheckedTextColor(context.resources.getColor(R.color.white))
                .useInsetPadding(true)

        val chipCloud = ChipCloud(context, tags, config)

        for (tag in recipe?.tags ?: emptyList<String>())
            chipCloud.addChip(tag)

        //pie chart

        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(recipe!!.proteins.toFloat(), context.resources?.getString(R.string.proteins)))
        entries.add(PieEntry(recipe!!.triglycerides.toFloat(), context.resources?.getString(R.string.triglycerides)))
        entries.add(PieEntry(recipe!!.carbohydrates.toFloat(), context.resources?.getString(R.string.carbohydrates)))

        val set = PieDataSet(entries, context.resources?.getString(R.string.food_energy))

        set.colors = listOf(
                context.resources?.getColor(R.color.recipe_chart_proteins_color),
                context.resources?.getColor(R.color.recipe_chart_triglycerides_color),
                context.resources?.getColor(R.color.recipe_chart_carbohydrates_color)
        )
        set.valueTextSize = 18f
        set.valueTextColor = -0x1

        val data = PieData(set)

        chart.data = data
        chart.centerText = context.resources?.getString(R.string.calories, recipe!!.calories)
        chart.setCenterTextSize(24f)
        chart.description = null
        chart.setCenterTextColor(context.resources.getColor(R.color.dark_plum))
        chart.isHighlightPerTapEnabled = false
        chart.legend.isEnabled = false

        chart.invalidate() // refresh
    }
}

class RecipeDetailsIngredients(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : RecipeDetailsView(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    private val ingredients: RecyclerView by bind(R.id.ingredients)

    private val adapter = IngredientAdapter()

    init {

        inflate()

        render()
    }

    private fun inflate() {
        inflate(context, R.layout.view_recipe_details_ingredients, this)
        ingredients.adapter = adapter
    }

    override fun render() {

        adapter.clear()
        adapter.addAll(recipe?.ingredients ?: emptyList())
    }
}

private class IngredientAdapter : SortedListAdapter<Ingredient>(Ingredient::class.java, kotlin.Comparator { i1, i2 -> i1.grocery.name.compareTo(i2.grocery.name, false) }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Ingredient> {

        val inflater = LayoutInflater.from(parent.context)

        return IngredientViewHolder(inflater.inflate(R.layout.viewholder_ingredient, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderBase<Ingredient>, position: Int) = holder.bind(get(position))
}

private class DirectionsAdapter : SortedListAdapter<Direction>(Direction::class.java, kotlin.Comparator { d1, d2 -> d1.ordinal.compareTo(d2.ordinal) }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Direction> {

        val inflater = LayoutInflater.from(parent.context)

        return RecipeDirectionViewHolder(inflater.inflate(R.layout.viewholder_direction, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolderBase<Direction>, position: Int) = holder.bind(get(position))

}

class RecipeDetailsDirections(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : RecipeDetailsView(ctx, attrs, defStyleAttr) {

    private val directions: RecyclerView by bind(R.id.directions)
    private val description: TextView by bind(R.id.description)

    private val adapter = DirectionsAdapter()

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    init {

        inflate(context, R.layout.view_recipe_details_directions, this)

        directions.adapter = adapter

        render()
    }

    override fun render() {

        description.text = recipe?.description

        adapter.clear()
        adapter.addAll(recipe?.directions ?: emptyList())
    }
}