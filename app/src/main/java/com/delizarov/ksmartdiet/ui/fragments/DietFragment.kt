package com.delizarov.ksmartdiet.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.delizarov.common.transformations.CircleTransform
import com.delizarov.common.ui.adapters.SortedListAdapter
import com.delizarov.common.ui.viewholders.ViewHolderBase
import com.delizarov.customviews.DateStepper
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.Meal
import com.delizarov.ksmartdiet.domain.models.UserInfo
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.presentation.DietPresenter
import com.delizarov.ksmartdiet.presentation.DietView
import com.delizarov.ksmartdiet.ui.viewholders.MealViewHolder
import com.delizarov.navigation.ScreenKey
import com.delizarov.navigation.ScreenKeyHolder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DietFragment : BaseFragment(), DietView, ScreenKeyHolder {

    override val screenKey = ScreenKeys.DailyDietScreenKey

    override fun injectComponents() {
        appComponent.inject(this)
    }

    @Inject
    lateinit var presenter: DietPresenter

    private lateinit var currentDate: DateStepper
    private lateinit var profilePic: ImageView
    private lateinit var userName: TextView
    private lateinit var meals: RecyclerView

    private val adapter = object : SortedListAdapter<Meal>(Meal::class.java, Comparator { o1, o2 -> Integer.compare(o1.type.order, o2.type.order) }) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBase<Meal> {

            return MealViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_meal, parent, false), presenter)
        }

        override fun onBindViewHolder(holder: ViewHolderBase<Meal>, position: Int) {

            holder.bind(get(position))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_diet, container, false)

        currentDate = v.findViewById(R.id.current_date)
        profilePic = v.findViewById(R.id.profile_pic)
        userName = v.findViewById(R.id.user_name)
        meals = v.findViewById(R.id.meals)

        currentDate.onSelectedDateChangedListener = {

            if (it != null)
                presenter.onSelectedDateChanged(it)
        }

        return v
    }

    override fun onResume() {
        super.onResume()

        meals.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(meals)

        presenter.attachView(this)
        presenter.onViewCreated()
    }

    override fun renderUserInfo(it: UserInfo) {

        userName.text = it.displayName

        // todo placeholder if url null

        Picasso
                .get()
                .load(it.photoUrl)
                .transform(CircleTransform())
                .into(profilePic)

    }

    override fun displaySettingsScreen() = navController.setRoot(ScreenKeys.SettingsScreenKey, true)


    override fun close() {
        activity!!.finish()
    }

    override fun showDailyMeals(meals: List<Meal>) {

        adapter.clear()
        adapter.addAll(meals)
    }

    override fun showPlanDaysMenu(daysAmount: Int) {

        currentDate.maxDays = daysAmount
    }

    override fun switchCurrentMealTo(oldMeal: Meal, newMeal: Meal) {

        adapter.remove(oldMeal)
        adapter.add(newMeal)
    }

    override fun displayFeatureNotImplementedYet() {

        Toast.makeText(context, "Feature is not implemented yet", Toast.LENGTH_LONG).show()
    }
}