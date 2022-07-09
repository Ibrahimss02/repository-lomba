package com.drunken.repositorylombaraion.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lomba(
    @Json(name = "id_lomba") val id: String,
    val name: String,
    val deskripsi: String?,
    val tanggal: DateJson,
    val penyelenggara: String,
    val kategori: String,
    @Json(name = "kategori_hadiah") val kategoriHadiah: Int?,
    val image: String,
    @Json(name = "status_lomba") val statusLomba: Int,
    @Json(name = "history_lomba") val historyLomba: List<HistoryLomba>?
) : Parcelable
