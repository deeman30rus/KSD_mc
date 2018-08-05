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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Recipe
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.presentation.RecipePresenter
import com.delizarov.ksmartdiet.presentation.RecipeView
import com.delizarov.navigation.ScreenKeyHolder
import javax.inject.Inject

class RecipeFragment : BaseFragment(), ScreenKeyHolder, RecipeView {

    @Inject
    lateinit var presenter: RecipePresenter

    private var recipeId: Long = -1L

    override fun injectComponents() {
        appComponent.inject(this)
    }

    override val screenKey = ScreenKeys.RecipeScreenKey

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_recipe, container, false)

        val tabsPager = v.findViewById<ViewPager>(R.id.tabs)
        tabsPager.adapter = RecipeFragmentTabsAdapter(context!!)

        val tabHeaders = v.findViewById<TabLayout>(R.id.tab_header)
        tabHeaders.setupWithViewPager(tabsPager)

        repeat(RecipeTab.values().size) { pos ->
            tabHeaders.getTabAt(pos)?.setIcon(RecipeTab.values()[pos].iconResId)
        }

        val selectedColor = context?.resources?.getColor(R.color.recipe_selected_tab_color) ?: 0
        val unselectedColor = context?.resources?.getColor(R.color.recipe_unselected_tab_color) ?: 0

        tabHeaders.addOnTabSelectedListener(HighlightTabSelectedListener(tabsPager, selectedColor, unselectedColor))
        tabHeaders.getTabAt(0)?.icon?.setIconColor(selectedColor)

        presenter.attachView(this)

        return v
    }

    override fun renderRecipe(recipe: Recipe) {

        Toast.makeText(context, recipe.title, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()

        presenter.onViewCreated(recipeId)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {

        var recipeId: Long = -1

        fun build(): RecipeFragment {

            val fragment = RecipeFragment()

            fragment.recipeId = this.recipeId

            return fragment
        }
    }
}


enum class RecipeTab(
        @DrawableRes val iconResId: Int,
        @LayoutRes val layoutId: Int
) {

    Details(R.drawable.icon_info_grey_500, R.layout.view_recipe_details),
    Ingredients(R.drawable.icon_apple_grey_500, R.layout.view_recipe_ingredients),
    Directions(R.drawable.icon_list_grey_500, R.layout.view_recipe_directions),
    VideoInstruction(R.drawable.icon_video_grey_500, R.layout.view_recipe_video_instructions);
}

internal class RecipeFragmentTabsAdapter(val ctx: Context) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = RecipeTab.values().size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val tab = RecipeTab.values()[position]

        val inflater = LayoutInflater.from(ctx)
        val layout = inflater.inflate(tab.layoutId, container, false)

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