package com.jantune.heartdisease.ui.view.main.identification.detail


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jantune.heartdisease.R
import com.jantune.heartdisease.databinding.ActivityIdentificationDetailBinding
import com.jantune.heartdisease.ui.view.main.identification.location.MapsActivity
import com.jantune.heartdisease.ui.view.main.identification.saran.SaranPenanganan
import com.jantune.heartdisease.ui.view.main.identification.saran.SaranPencegahan


class IdentificationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentificationDetailBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentificationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.iconEndBtnLokasi.setOnClickListener {
            startActivity(Intent(this@IdentificationDetailActivity, MapsActivity::class.java))
        }
        val identification = intent.getStringExtra(EXTRA_IDENTIFICATION)

        if (identification == getString(R.string.negatif_result)) {
            Glide.with(this)
                .load(R.drawable.negatif_kardiovaskuler_heart)
                .into(binding.ivHeartDetailIdentification)
                .clearOnDetach()

            binding.btnResultIdentification.text =
                getString(R.string.negatif_result)
            binding.btnResultIdentification.setTextColor(R.color.negative_identification)
            binding.tvBtnSaran.text =
                getString(R.string.saran_pencegahan)
            binding.iconEndBtnSaran.setOnClickListener {
                startActivity(Intent(this@IdentificationDetailActivity, SaranPencegahan::class.java))
            }
        }else{
            binding.iconEndBtnSaran.setOnClickListener {
                startActivity(Intent(this@IdentificationDetailActivity, SaranPenanganan::class.java))
            }
        }
    }

    companion object {
        const val EXTRA_IDENTIFICATION = "extra_identification"
    }
}