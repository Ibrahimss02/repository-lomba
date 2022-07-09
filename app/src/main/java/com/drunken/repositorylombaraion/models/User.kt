package com.drunken.repositorylombaraion.models

data class User(
    val id: String,
    val nama: String,
    val prodi: String?,
    val angkatan: Int?,
    val jabatan: String,
    val image: String,
    val historyLomba: List<HistoryLomba>?
)
