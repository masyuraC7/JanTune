package com.jantune.heartdisease.ui.view.main.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jantune.heartdisease.R
import com.jantune.heartdisease.databinding.FragmentProfileBinding
import com.jantune.heartdisease.ui.view.auth.AuthActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEditProfile.setOnClickListener {
            val intentToEditProfileActivity =
                Intent(requireActivity(), EditProfileActivity::class.java)
            requireActivity().startActivity(intentToEditProfileActivity)
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.logout_confirmation_view)

        val yesBtn = dialog.findViewById(R.id.btn_logout_ya) as Button
        yesBtn.setOnClickListener {
            val intentToAuthActivity = Intent(requireActivity(), AuthActivity::class.java)
            intentToAuthActivity.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity().startActivity(intentToAuthActivity)
        }

        val noBtn = dialog.findViewById(R.id.btn_logout_tidak) as Button
        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}