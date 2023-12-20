package com.jantune.heartdisease.ui.view.main.identification.form

import com.jantune.heartdisease.ui.view.main.identification.form.spinner.AnginaSpinner
import com.jantune.heartdisease.ui.view.main.identification.form.spinner.KemiringanSTSpinner
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.jantune.heartdisease.databinding.ActivityIdentificationFormBinding
import com.jantune.heartdisease.ui.view.main.identification.detail.IdentificationDetailActivity
import com.jantune.heartdisease.ui.view.main.identification.form.spinner.ElektrokardiogramSpinner
import com.jantune.heartdisease.ui.view.main.identification.form.spinner.GenderSpinner
import com.jantune.heartdisease.ui.view.main.identification.form.spinner.JenisNyeriDadaSpinner

class IdentificationFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentificationFormBinding
    val item = listOf(
        "L/P",
        "Laki-Laki",
        "Perempuan"
    )

    private val itemJnd = listOf(
        "ASY/BB/SS",
        "ASY",
        "BB",
        "SS"
    )

    private val itemEktd = listOf(
        "Normal/ST/LVH",
        "Normal",
        "ST",
        "LVH"
    )

    private val itemAngina = listOf(
        "N/Y",
        "N",
        "Y"
    )

    private val itemKST = listOf(
        "UP/FLAT",
        "UP",
        "FLAT"
    )

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
                IdentificationDetailActivity.EXTRA_ID,
                "Positif Kardiovaskuler"
            )
            startActivity(intentToIdentificationDetailActivity)
        }

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        binding.btnFormDate.setOnClickListener {
            datePicker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener {
                binding.btnFormDate.text = datePicker.headerText
            }
        }

        binding.edtFormGender.onItemSelectedListener = GenderSpinner(applicationContext, item)
        binding.edtFormNyeriDada.onItemSelectedListener = JenisNyeriDadaSpinner(applicationContext, itemJnd)
        binding.edtFormElektrokardiogram.onItemSelectedListener = ElektrokardiogramSpinner(applicationContext, itemEktd)
        binding.edtFormAngina.onItemSelectedListener = AnginaSpinner(applicationContext, itemAngina)
        binding.edtFormKemiringanST.onItemSelectedListener = KemiringanSTSpinner(applicationContext, itemKST)

        val adapter = object : ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, item){
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView

                if(position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    view.setTextColor(Color.BLACK)
                }

                return view
            }
        }
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.edtFormGender.adapter = adapter

        val adapterJnd = object : ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, itemJnd){
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView

                if(position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    view.setTextColor(Color.BLACK)
                }

                return view
            }
        }
        adapterJnd.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.edtFormNyeriDada.adapter = adapterJnd

        val adapterEktd = object : ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, itemEktd){
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView

                if(position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    view.setTextColor(Color.BLACK)
                }

                return view
            }
        }
        adapterEktd.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.edtFormElektrokardiogram.adapter = adapterEktd

        val adapterAngina = object : ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, itemAngina){
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView

                if(position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    view.setTextColor(Color.BLACK)
                }

                return view
            }
        }
        adapterAngina.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.edtFormAngina.adapter = adapterAngina

        val adapterKST = object : ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, itemKST){
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView

                if(position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    view.setTextColor(Color.BLACK)
                }

                return view
            }
        }
        adapterKST.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.edtFormKemiringanST.adapter = adapterKST
    }
}
