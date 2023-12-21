package com.jantune.heartdisease.ui.view.auth.login

import android.content.Intent
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
import com.jantune.heartdisease.utils.Result
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jantune.heartdisease.R
import com.jantune.heartdisease.databinding.FragmentLoginBinding
import com.jantune.heartdisease.ui.view.auth.AuthViewModel
import com.jantune.heartdisease.ui.view.main.MainActivity
import com.jantune.heartdisease.ui.view.main.identification.IdentificationViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }


        setSpannableToRegister()

        with(binding) {
            btnLogin.setOnClickListener {
                if ((edtEmailLogin.error != null) || (edtPassLogin.error != null)) {
                    showSnackbar(getString(R.string.error_login_correct))
                } else if (edtEmailLogin.text.isNullOrEmpty() || edtPassLogin.text.isNullOrEmpty()) {
                    showSnackbar(getString(R.string.error_login_empty))
                } else {
                    login()
                }
            }
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_LONG
        ).setAction("Close") {
        }.show()
    }

    private fun setSpannableToRegister() {
        val firstString = getString(R.string.txt_create_account)
        val extraString = getString(R.string.create_your_account)
        val ss = SpannableString(getString(R.string.txt_create_account, extraString))

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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

        binding.tvToRegister.text = ss
        binding.tvToRegister.movementMethod = LinkMovementMethod.getInstance()
        binding.tvToRegister.highlightColor = Color.TRANSPARENT
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun login() {
        val email = binding.edtEmailLogin.text.toString()
        val password = binding.edtPassLogin.text.toString()
        viewModel.userLogin(email, password)
    }

    private fun startMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
