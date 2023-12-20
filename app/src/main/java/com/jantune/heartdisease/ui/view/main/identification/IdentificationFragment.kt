package com.jantune.heartdisease.ui.view.main.identification

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jantune.heartdisease.R
import com.jantune.heartdisease.databinding.FragmentIdentificationBinding
import com.jantune.heartdisease.ui.view.main.identification.detail.IdentificationDetailActivity
import com.jantune.heartdisease.ui.view.main.identification.form.IdentificationFormActivity

class IdentificationFragment : Fragment() {
    private lateinit var binding: FragmentIdentificationBinding
    private val viewModel: IdentificationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIdentificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getUserId = 1 //get from pref user

        binding.rvIdentification.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val listAdapter = IdentificationAdapter(
            onItemClick = { item ->
                val intentToIdentificationActivity =
                    Intent(requireActivity(), IdentificationDetailActivity::class.java)
                intentToIdentificationActivity.putExtra(
                    IdentificationDetailActivity.EXTRA_USER_ID,
                    item.userId
                )
                intentToIdentificationActivity.putExtra(
                    IdentificationDetailActivity.EXTRA_ID,
                    item.id
                )
                startActivity(intentToIdentificationActivity)
            },
            onIvDelete = {
                viewModel.deleteIdentificationById(userId = getUserId, identificationId = it)
                viewModel.getAllIdentificationByUserId(getUserId)
            }
        )
        binding.rvIdentification.adapter = listAdapter

        viewModel.isSuccessMessage.observe(viewLifecycleOwner){
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_SHORT)
                .setAnchorView(requireActivity().findViewById(R.id.bottom_nav_view))
                .show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        viewModel.isError.observe(viewLifecycleOwner){
            showError(it)
        }

        viewModel.getAllIdentificationByUserId(getUserId)

        viewModel.isFilledIdentification.observe(viewLifecycleOwner){
            listAdapter.submitList(it)
        }

        binding.fabAddIdentification.setOnClickListener {
            val intentToIdentificationActivity =
                Intent(requireActivity(), IdentificationFormActivity::class.java)
            requireActivity().startActivity(intentToIdentificationActivity)
        }
    }

    private fun showError(message: String?) {
        if (message.isNullOrEmpty()){
            binding.tvErrorMessage.visibility = View.GONE
        }else{
            binding.tvErrorMessage.visibility = View.VISIBLE
            binding.tvErrorMessage.text = message
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}