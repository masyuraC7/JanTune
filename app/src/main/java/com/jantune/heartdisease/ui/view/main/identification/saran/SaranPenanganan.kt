package com.jantune.heartdisease.ui.view.main.identification.saran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jantune.heartdisease.data.model.saranPenanganan
import com.jantune.heartdisease.databinding.ActivitySaranPenangananBinding

class SaranPenanganan : AppCompatActivity() {
    private lateinit var binding: ActivitySaranPenangananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaranPenangananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailToolbar.setOnClickListener {
            finish()
        }

        binding.rvSaranPenanganan.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SaranPenanganan)
        }

        val listAdapter = SaranAdapter()
        binding.rvSaranPenanganan.adapter = listAdapter

        listAdapter.submitList(saranPenanganan)
    }
}