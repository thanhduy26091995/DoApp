package com.duybui.doapp.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.tvSignIn
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.include_toolbar.*

class SignUpActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.tvSignIn -> {
                finish()
            }
            R.id.tvContinue -> {
                val intent = Intent(this, IntroductionActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override val layoutRes: Int
        get() = R.layout.activity_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSpan()
        setupToolbar()
        tvSignIn.setOnClickListener(this)
        tvContinue.setOnClickListener(this)
    }

    private fun setupSpan() {
        val spannableString = SpannableString(getString(R.string.already_have_an_account))
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorWhiteOpacity50)), 0, spannableString.indexOf("SIGN IN"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvSignIn.text = spannableString
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }
        tvHeader.text = getString(R.string.sign_up)
    }
}