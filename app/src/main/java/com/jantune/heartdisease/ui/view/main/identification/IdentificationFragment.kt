package com.jantune.heartdisease.ui.view.main.identification

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
                    IdentificationDetailActivity.EXTRA_IDENTIFICATION,
                    item.identification
                )
                requireActivity().startActivity(intentToIdentificationActivity)
            },
            onIvDelete = {
                viewModel.updateIdentification(it)
                viewModel.getActiveIdentification()
            }
        )
        binding.rvIdentification.adapter = listAdapter

        viewModel.getActiveIdentification()

        viewModel.isUpdateIdentification.observe(viewLifecycleOwner){
            listAdapter.submitList(it)
        }

        binding.fabAddIdentification.setOnClickListener {
            val intentToIdentificationActivity =
                Intent(requireActivity(), IdentificationFormActivity::class.java)
            requireActivity().startActivity(intentToIdentificationActivity)
        }
    }
}