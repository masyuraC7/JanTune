package com.jantune.heartdisease.utils

const val END_SPAN_NAME = 4
const val END_SPAN_DATE = 7
const val END_SPAN_TIME = 5
const val END_SPAN_GENDER = 13
const val END_SPAN_AGE = 4
const val END_SPAN_CPT = 16
const val END_SPAN_RESTINGBP = 28
const val END_SPAN_KOLESTEROL = 10
const val END_SPAN_FASTINGBS = 21
const val END_SPAN_RESTINGECG = 32
const val END_SPAN_ANGINA = 14
const val END_SPAN_OLDPEAK = 8
const val END_SPAN_STSLOPE = 13
const val END_SPAN_MAXHR = 6

fun genderFormat(sex: Int): String {
    return if (sex == 0) "Female" else "Male"
}

fun stSlopeFormat(stSlope: Int): String {
    return when (stSlope) {
        1 -> "Down"
        2 -> "Flat"
        else -> "Up"
    }
}

fun restingFCGFormat(restingFCG: Int): String {
    return if (restingFCG == 1)
        "LVH"
    else if (restingFCG == 2)
        "Normal"
    else
        "ST"
}

fun chestPainTypeFormat(chestPT: Int): String {
    return if (chestPT == 1)
        "ASY"
    else if (chestPT == 2)
        "ATA"
    else if (chestPT == 3)
        "NAP"
    else
        "TA"
}

fun exAnginaFormat(angina: Int): String {
    return if (angina == 0) "No" else "Yes"
}