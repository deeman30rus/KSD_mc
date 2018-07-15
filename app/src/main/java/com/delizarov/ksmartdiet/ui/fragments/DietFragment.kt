package com.delizarov.ksmartdiet.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.common.x.ui.bind
import com.delizarov.customviews.SelectNearDateView
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.presentation.DietPresenter
import com.delizarov.ksmartdiet.presentation.DietView
import com.delizarov.ksmartdiet.ui.dialogs.DietSettingsDialog
import com.delizarov.ksmartdiet.ui.viewholders.MealViewHolder
import org.joda.time.DateTime
import javax.inject.Inject

class DietFragment : BaseFragment(), DietView {

    override fun injectComponents() {
        appComponent.inject(this)
    }

    @Inject
    lateinit var presenter: DietPresenter

    private val currentDate: SelectNearDateView by bind(R.id.current_date)
    private val meals: RecyclerView by bind(R.id.meals)

    private val dialog: DietSettingsDialog by lazy {

        DietSettingsDialog(activity as Context) { settings -> presenter.onSettingsSaveClicked(settings) }
    }

    private val adapter = object : SortedListAdapter<Meal>(Meal::class.java, Comparator { o1, o2 -> Integer.compare(o1.type.order, o2.type.order) }) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Meal> {

            return MealViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_meal, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolderBase<Meal>, position: Int) {

            holder.bind(get(position))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_diet, container, false)
    }

    override fun onResume() {
        super.onResume()

        currentDate.onSelectedDateChangeListener = {

            if (it != null)
                presenter.onSelectedDateChanged(it)
        }

        meals.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(meals)

        presenter.attachView(this)
        presenter.onViewCreated()
    }

    override fun showDailyMeals(meals: List<Meal>) {

        adapter.clear()
        adapter.addAll(meals)
    }

    override fun showDietSettingsDialog() {

        dialog.show()
    }

    override fun dismissDietSettingsDialog() {

        dialog.dismiss()
    }

    override fun showPlanDaysMenu(days: List<DateTime>) {

        currentDate.entries = days
    }
}