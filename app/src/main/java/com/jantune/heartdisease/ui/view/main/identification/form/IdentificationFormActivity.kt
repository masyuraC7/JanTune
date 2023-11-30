package com.jantune.heartdisease.ui.view.main.identification.form

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jantune.heartdisease.databinding.ActivityIdentificationFormBinding
import com.jantune.heartdisease.ui.view.main.identification.detail.IdentificationDetailActivity

class IdentificationFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentificationFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentificationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.formToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnFormIdentification.setOnClickListener {
            val intentToIdentificationDetailActivity =
                Intent(this, IdentificationDetailActivity::class.java)
            intentToIdentificationDetailActivity.putExtra(
                IdentificationDetailActivity.EXTRA_IDENTIFICATION,
                "Positif Kardiovaskuler"
            )
            startActivity(intentToIdentificationDetailActivity)
        }
    }
}