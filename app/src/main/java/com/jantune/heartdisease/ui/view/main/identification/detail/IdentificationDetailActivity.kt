package com.jantune.heartdisease.ui.view.main.identification.detail


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jantune.heartdisease.R
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.databinding.ActivityIdentificationDetailBinding
import com.jantune.heartdisease.ui.view.main.identification.location.MapsActivity
import com.jantune.heartdisease.ui.view.main.identification.saran.SaranPenanganan
import com.jantune.heartdisease.ui.view.main.identification.saran.SaranPencegahan
import com.jantune.heartdisease.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdentificationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentificationDetailBinding
    private val viewModel: IdentificationDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentificationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getUserId = intent.getIntExtra(EXTRA_USER_ID, 0)
        val getIdenId = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.getIdentificationById(getUserId, getIdenId)

        viewModel.isFilledIdentification.observe(this){
            if (it != null) {
                showIdentificationDetail(it)
            }
        }

        binding.detailToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.iconEndBtnLokasi.setOnClickListener {
            startActivity(Intent(this@IdentificationDetailActivity, MapsActivity::class.java))
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun showIdentificationDetail(item: IdentificationItemResponse) {
        if (item.result == getString(R.string.negatif_result)) {
            Glide.with(this)
                .load(R.drawable.negatif_kardiovaskuler_heart)
                .into(binding.ivHeartDetailIdentification)
                .clearOnDetach()

            with(binding){
                btnResultIdentification.text =
                    getString(R.string.negatif_result)
                btnResultIdentification.setTextColor(R.color.negative_identification)
                tvBtnSaran.text =
                    getString(R.string.saran_pencegahan)
                iconEndBtnSaran.setOnClickListener {
                    startActivity(Intent(this@IdentificationDetailActivity, SaranPencegahan::class.java))
                }
            }
        }else{
            binding.iconEndBtnSaran.setOnClickListener {
                startActivity(Intent(this@IdentificationDetailActivity, SaranPenanganan::class.java))
            }
        }

        with(binding){
            tvDetailName.text = item.name
            tvDetailAge.text = item.age.toString()
            tvDetailAngina.text = item.exerciseAngina.toString()
            tvDetailDate.text = item.date
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_USER_ID = "extra_user_id"
    }
}