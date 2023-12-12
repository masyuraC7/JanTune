package com.jantune.heartdisease.ui.view.main.identification.saran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jantune.heartdisease.data.model.saranPencegahan
import com.jantune.heartdisease.databinding.ActivitySaranPencegahanBinding

class SaranPencegahan : AppCompatActivity() {
    private lateinit var binding: ActivitySaranPencegahanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaranPencegahanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailToolbar.setOnClickListener {
            finish()
        }

        binding.rvSaranPencegahan.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SaranPencegahan)
        }

        val listAdapter = SaranAdapter()
        binding.rvSaranPencegahan.adapter = listAdapter

        listAdapter.submitList(saranPencegahan)
    }
}