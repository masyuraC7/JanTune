package com.jantune.heartdisease.ui.auth.register

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.jantune.heartdisease.R
import com.jantune.heartdisease.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpannableToLogin()
    }

    private fun setSpannableToLogin(){
        val firstString = getString(R.string.txt_login_account)
        val extraString = getString(R.string.login_your_account)
        val ss = SpannableString(getString(R.string.txt_login_account, extraString))

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.seed)
                ds.isUnderlineText = false
            }
        }
        val startLinks = firstString.length - 4
        val endLinks = startLinks + extraString.length
        ss.setSpan(clickableSpan, startLinks, endLinks, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvToLogin.text = ss
        binding.tvToLogin.movementMethod = LinkMovementMethod.getInstance()
        binding.tvToLogin.highlightColor = Color.TRANSPARENT
    }
}