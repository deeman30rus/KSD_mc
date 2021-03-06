package com.delizarov.ksmartdiet.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.delizarov.ksmartdiet.BuildConfig
import com.delizarov.ksmartdiet.R
import com.delizarov.ksmartdiet.navigation.ScreenKeys
import com.delizarov.ksmartdiet.presentation.LoginPresenter
import com.delizarov.ksmartdiet.presentation.LoginView
import com.delizarov.navigation.ScreenKeyHolder
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import javax.inject.Inject


class LoginFragment : BaseFragment(), LoginView, ScreenKeyHolder {

    override val screenKey = ScreenKeys.SignInScreenKey

    override fun injectComponents() {

        appComponent.inject(this)
    }

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_login, container, false)

        val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up_animation)
        val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation)

        val signInButton = v.findViewById<Button>(R.id.sign_in)
        val logoImage = v.findViewById<ImageView>(R.id.logo)

        signInButton.startAnimation(slideUp)
        logoImage.startAnimation(slideDown)

        signInButton.setOnClickListener {
            presenter.onSignInWithGoogleClicked()
        }

        return v
    }

    override fun onResume() {
        super.onResume()

        presenter.attachView(this)
        presenter.onViewCreated()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun displaySignInWithGoogle() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken(BuildConfig.CLIENT_ID)
                .build()

        val signInClient = GoogleSignIn.getClient(context!!, gso)

        val signInIntent = signInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            if (account == null)
                presenter.onSignInFail()
            else
                presenter.onSignInSuccess(account.displayName, account.idToken, account.photoUrl)

        } catch (e: ApiException) {
            e.printStackTrace()

            presenter.onSignInFail()
        }
    }

    override fun displayDietScreen() = navController.replaceTo(ScreenKeys.DailyDietScreenKey)

    override fun showSignInError() {

        showError(getString(R.string.sign_in_error))
    }

    override fun showClientDataError() {

        showError(getString(R.string.client_data_error))
    }

    private fun showError(errorText: String) {

        val snackbar = Snackbar
                .make(view!!, errorText, Snackbar.LENGTH_LONG)

        snackbar.show()
    }

    companion object {

        const val RC_SIGN_IN = 1
    }
}