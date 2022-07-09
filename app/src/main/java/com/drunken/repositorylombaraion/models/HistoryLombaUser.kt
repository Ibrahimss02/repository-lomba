package com.drunken.repositorylombaraion.models

import com.squareup.moshi.Json
import java.time.LocalDate

data class HistoryLombaUser(
    @Json(name = "id_lomba") val idLomba: String,
    @Json(name = "nama_lomba") val namaLomba: String,
    @Json(name = "tahun") val tanggal: DateJson,
    val hasil: String
)
