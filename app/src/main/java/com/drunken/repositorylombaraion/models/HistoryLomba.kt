package com.drunken.repositorylombaraion.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class HistoryLomba(
    @Json(name = "id_kelompok") val idKelompok: String,
    @Json(name = "nama_kelompok") val namaKelompok: String,
    @Json(name = "tahun") val tanggal: DateJson,
    val hasil: String,
    @Json(name = "link_submission") val linkSubmission: String
) : Parcelable
