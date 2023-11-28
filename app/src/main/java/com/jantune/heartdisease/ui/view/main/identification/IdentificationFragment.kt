package com.jantune.heartdisease.ui.view.main.identification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jantune.heartdisease.data.model.dummyIdentificationHistory
import com.jantune.heartdisease.databinding.FragmentIdentificationBinding

class IdentificationFragment : Fragment() {
    private lateinit var binding: FragmentIdentificationBinding

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
        val listAdapter = IdentificationAdapter()
        binding.rvIdentification.adapter = listAdapter

        listAdapter.submitList(dummyIdentificationHistory)
    }
}