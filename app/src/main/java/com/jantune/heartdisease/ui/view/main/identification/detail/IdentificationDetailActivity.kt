package com.jantune.heartdisease.ui.view.main.identification.detail


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jantune.heartdisease.R
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.databinding.ActivityIdentificationDetailBinding
import com.jantune.heartdisease.ui.view.main.identification.location.MapsActivity
import com.jantune.heartdisease.ui.view.main.identification.saran.SaranPenanganan
import com.jantune.heartdisease.ui.view.main.identification.saran.SaranPencegahan
import com.jantune.heartdisease.utils.DateFormatter
import com.jantune.heartdisease.utils.END_SPAN_AGE
import com.jantune.heartdisease.utils.END_SPAN_ANGINA
import com.jantune.heartdisease.utils.END_SPAN_CPT
import com.jantune.heartdisease.utils.END_SPAN_DATE
import com.jantune.heartdisease.utils.END_SPAN_FASTINGBS
import com.jantune.heartdisease.utils.END_SPAN_GENDER
import com.jantune.heartdisease.utils.END_SPAN_KOLESTEROL
import com.jantune.heartdisease.utils.END_SPAN_MAXHR
import com.jantune.heartdisease.utils.END_SPAN_NAME
import com.jantune.heartdisease.utils.END_SPAN_OLDPEAK
import com.jantune.heartdisease.utils.END_SPAN_RESTINGBP
import com.jantune.heartdisease.utils.END_SPAN_RESTINGECG
import com.jantune.heartdisease.utils.END_SPAN_STSLOPE
import com.jantune.heartdisease.utils.END_SPAN_TIME
import com.jantune.heartdisease.utils.TextFormatUtils.spannableBoldFormat
import com.jantune.heartdisease.utils.chestPainTypeFormat
import com.jantune.heartdisease.utils.exAnginaFormat
import com.jantune.heartdisease.utils.genderFormat
import com.jantune.heartdisease.utils.restingFCGFormat
import com.jantune.heartdisease.utils.stSlopeFormat
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

        viewModel.isFilledIdentification.observe(this) {
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

            with(binding) {
                btnResultIdentification.text =
                    getString(R.string.negatif_result)
                btnResultIdentification.setTextColor(R.color.negative_identification)
                tvBtnSaran.text =
                    getString(R.string.saran_pencegahan)
                iconEndBtnSaran.setOnClickListener {
                    startActivity(
                        Intent(
                            this@IdentificationDetailActivity,
                            SaranPencegahan::class.java
                        )
                    )
                }
            }
        } else {
            binding.iconEndBtnSaran.setOnClickListener {
                startActivity(
                    Intent(
                        this@IdentificationDetailActivity,
                        SaranPenanganan::class.java
                    )
                )
            }
        }

        val getName = getString(R.string.iden_detail_name, item.name)
        binding.tvDetailName.text = spannableBoldFormat(
            getName,
            END_SPAN_NAME
        )

        val newDate = DateFormatter.formatDate(item.date.toString())
        val getDate = getString(R.string.iden_detail_tanggal, newDate)
        binding.tvDetailDate.text = spannableBoldFormat(
            getDate,
            END_SPAN_DATE
        )

        val getTime = getString(R.string.iden_detail_waktu, item.time)
        binding.tvDetailTime.text = spannableBoldFormat(
            getTime,
            END_SPAN_TIME
        )

        val getGender = getString(
            R.string.iden_detail_gender, genderFormat(item.sex.toString().toInt())
        )
        binding.tvDetailGender.text = spannableBoldFormat(
            getGender,
            END_SPAN_GENDER
        )

        val getAge = getString(R.string.iden_detail_age, item.age.toString())
        binding.tvDetailAge.text = spannableBoldFormat(
            getAge,
            END_SPAN_AGE
        )

        val setCPT = chestPainTypeFormat(item.chestPainType.toString().toInt())
        val getCPT = getString(R.string.iden_detail_nyeri_dada, setCPT)
        binding.tvDetailNyeriDada.text = spannableBoldFormat(
            getCPT,
            END_SPAN_CPT
        )

        val getRBP = getString(R.string.iden_detail_tekanan_darah, item.restingBP.toString())
        binding.tvDetailTekananDarah.text = spannableBoldFormat(
            getRBP,
            END_SPAN_RESTINGBP
        )

        val getKolesterol = getString(R.string.iden_detail_kolesterol, item.cholesterol.toString())
        binding.tvDetailKolesterol.text = spannableBoldFormat(
            getKolesterol,
            END_SPAN_KOLESTEROL
        )

        val getFBS = getString(R.string.iden_detail_gula_darah, item.fastingBS.toString())
        binding.tvDetailGulaDarah.text = spannableBoldFormat(
            getFBS,
            END_SPAN_FASTINGBS
        )

        val setRECG = restingFCGFormat(item.restingECG.toString().toInt())
        val getRECG = getString(R.string.iden_detail_elektrokardiogram, setRECG)
        binding.tvDetailElektrokardiogram.text = spannableBoldFormat(
            getRECG,
            END_SPAN_RESTINGECG
        )

        val setAngina = exAnginaFormat(item.exerciseAngina.toString().toInt())
        val getAngina = getString(R.string.iden_detail_angina, setAngina)
        binding.tvDetailAngina.text = spannableBoldFormat(
            getAngina,
            END_SPAN_ANGINA
        )

        val getOldPeak = getString(R.string.iden_detail_old_peak, item.oldpeak.toString())
        binding.tvDetailOldPeak.text = spannableBoldFormat(
            getOldPeak,
            END_SPAN_OLDPEAK
        )

        val setStSlope = stSlopeFormat(item.stSlope.toString().toInt())
        val getStSlope = getString(R.string.iden_detail_kemiringan_st, setStSlope)
        binding.tvDetailKemiringanSt.text = spannableBoldFormat(
            getStSlope,
            END_SPAN_STSLOPE
        )

        val getMaxHr = getString(R.string.iden_detail_max_hr, item.maxHR.toString())
        binding.tvDetailMaxHr.text = spannableBoldFormat(
            getMaxHr,
            END_SPAN_MAXHR
        )
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_USER_ID = "extra_user_id"
    }
}