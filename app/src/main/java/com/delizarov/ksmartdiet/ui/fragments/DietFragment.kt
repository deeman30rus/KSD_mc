package com.delizarov.ksmartdiet.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delizarov.ksmartdiet.navigation.NavController
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.presentation.DietPresenter
import com.delizarov.ksmartdiet.presentation.DietView
import com.delizarov.ksmartdiet.ui.dialogs.DietSettingsDialog
import javax.inject.Inject

class DietFragment : BaseFragment(), DietView {

    @Inject
    lateinit var presenter: DietPresenter

    private val dialog: DietSettingsDialog by lazy {

        DietSettingsDialog(activity as Context) { settings -> presenter.onSettingsSaveClicked(settings) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_diet, container, false)

        return v
    }

    override fun onResume() {
        super.onResume()

        presenter.attachView(this)
        presenter.onViewCreated()
    }

    override fun showDietSettingsDialog() {

        dialog.show()
    }

    override fun dismissDietSettingsDialog() {

        dialog.dismiss()
    }

    companion object {

        fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {

        lateinit var navController: NavController

        fun build(): DietFragment {

            val fragment = DietFragment()
            fragment.navController = navController

            return fragment
        }
    }

}