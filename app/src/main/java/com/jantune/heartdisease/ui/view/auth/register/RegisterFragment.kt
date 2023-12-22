package com.jantune.heartdisease.ui.view.auth.register

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jantune.heartdisease.R
import com.jantune.heartdisease.databinding.FragmentRegisterBinding
import com.jantune.heartdisease.ui.view.auth.AuthViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.errorMsg.observe(viewLifecycleOwner) {
            showSnackbar(it)
        }

        with(binding) {
            btnRegister.setOnClickListener {
                if ((edtEmailRegister.error != null) || (edtPassRegister.error != null) || (edtNameRegister.error != null)) {
                    showSnackbar(getString(R.string.error_login_correct))
                } else if (edtEmailRegister.text.isNullOrEmpty() || edtPassRegister.text.isNullOrEmpty() || edtNameRegister.text.isNullOrEmpty()) {
                    showSnackbar(getString(R.string.error_login_empty))
                } else {
                    register()
                }
            }
        }

        setSpannableToLogin()
    }

    private fun register() {
        val name = binding.edtNameRegister.text.toString()
        val email = binding.edtEmailRegister.text.toString()
        val password = binding.edtPassRegister.text.toString()

        viewModel.userRegister(name, email, password)
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_LONG
        ).setAction("Close") {
        }.show()
    }

    private fun setSpannableToLogin() {
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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
