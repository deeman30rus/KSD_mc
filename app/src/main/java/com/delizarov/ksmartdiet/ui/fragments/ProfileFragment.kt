package com.delizarov.ksmartdiet.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.delizarov.common.transformations.CircleTransform
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.domain.models.UserInfo
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.presentation.ProfilePresenter
import com.delizarov.ksmartdiet.presentation.ProfileView
import com.delizarov.ksmartdiet.ui.activities.StartActivity
import com.delizarov.navigation.ScreenKey
import com.delizarov.navigation.ScreenKeyHolder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ScreenKeyHolder, ProfileView {

    @Inject
    lateinit var presenter: ProfilePresenter

    private lateinit var userInfo: UserInfo

    override val screenKey: ScreenKey
        get() = ScreenKeys.ProfileScreenKey

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        val userName = v.findViewById<TextView>(R.id.user_name)
        val userEmail = v.findViewById<TextView>(R.id.user_email)
        val profilePic = v.findViewById<ImageView>(R.id.profile_pic)

        userName.text = userInfo.displayName
        Picasso
                .get()
                .load(userInfo.photoUrl)
                .transform(CircleTransform())
                .into(profilePic)

        userName.setOnClickListener { presenter.onUserNameClicked() }
        userEmail.setOnClickListener { presenter.onUserEmailClicked() }
        v.findViewById<View>(R.id.icon_down).setOnClickListener { presenter.onIconDownClicked() }

        return v
    }

    override fun displayLogOutDialog() {

        val dialog = AlertDialog.Builder(context)
                .setTitle(R.string.dialog_logout_title)
                .setMessage(R.string.dialog_logout_message)
                .setNegativeButton(R.string.dialog_logout_cancel) { dialog, _ -> dialog.dismiss() }
                .setPositiveButton(R.string.dialog_logout_ok) { dialog, _ ->
                    presenter.onLogOutButtonClicked()
                    dialog.dismiss()
                }
                .create()

        dialog.show()
    }

    override fun displaySplashScreen() {

        val intent = Intent(context, StartActivity::class.java)

        context?.startActivity(intent)
        activity?.finish()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this@ProfileFragment)
    }

    override fun injectComponents() {
        appComponent.inject(this)
    }

    companion object {

        fun newInstance(userInfo: UserInfo): ProfileFragment {

            val fragment = ProfileFragment()

            fragment.userInfo = userInfo

            return fragment
        }
    }
}