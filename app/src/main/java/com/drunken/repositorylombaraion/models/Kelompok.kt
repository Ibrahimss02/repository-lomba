package com.drunken.repositorylombaraion.models

import com.squareup.moshi.Json

data class Kelompok(
    @Json(name = "id_kelompok") val idKelompok: String,
    val name: String,
    @Json(name = "daftar_anggota") val daftarAnggota: List<User>?,
    @Json(name = "history_lomba") val historyLomba: List<HistoryLombaUser>?
)
