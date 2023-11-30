package com.jantune.heartdisease.data.model

data class IdentificationHistory(
    val name: String,
    val identification: String,
    val dateTime: String,
    val isActive: Boolean = true
)

val dummyIdentificationHistory = mutableListOf(
    IdentificationHistory(
        "Leonardo",
        "Negatif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam1",
        "Negatif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam2",
        "Positif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam3",
        "Negatif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam4",
        "Positif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam5",
        "Positif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam6",
        "Negatif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam7",
        "Positif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam8",
        "Negatif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    ),
    IdentificationHistory(
        "Adam9",
        "Positif Kardiovaskuler",
        "16 /11/ 2023   10:11 AM"
    )
)