package com.jantune.heartdisease.data.model

import com.jantune.heartdisease.R

data class Gejala(
    val image: Int,
    val title: String
)

val gejalaItems = listOf(
    Gejala(R.drawable.gejala_djtt, "Detak Jantung Tidak Teratur"),
    Gejala(R.drawable.gejala_tdt, "Tekanan Darah Tinggi"),
    Gejala(R.drawable.gejala_kd, "Keringat Dingin"),
    Gejala(R.drawable.gejala_ml, "Mudah Lelah"),
    Gejala(R.drawable.gejala_nd, "Nyeri Dada"),
    Gejala(R.drawable.gejala_sk, "Sakit Kepala"),
)