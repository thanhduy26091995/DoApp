package com.duybui.doapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseActivity
import com.duybui.doapp.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvSignUp -> {
                val signUpIntent = Intent(this, SignUpActivity::class.java)
                startActivity(signUpIntent)
            }
        }
    }

    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSpan()
        setFullScreenView()
        tvSignUp.setOnClickListener(this)
    }

    private fun setupSpan() {
        val spannableString = SpannableString(getString(R.string.dont_have_account_sign_up))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorWhiteOpacity50)), 0, spannableString.indexOf("SIGN UP"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvSignUp.text = spannableString
    }

}