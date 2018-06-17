package com.delizarov.ksmartdiet.ui.fargments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delizarov.ksmartdiet.NavController
import com.delizarov.ksmartdiet.R

class DietFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_diet, container, false)

        return v
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