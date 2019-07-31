package com.duybui.doapp.ui.login

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenView()
        setupSpan()
    }

    private fun setupSpan() {
        val spannableString = SpannableString(getString(R.string.dont_have_account_sign_up))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorWhiteOpacity50)), 0, spannableString.indexOf("?") + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_dont_have_account.text = spannableString
    }

}