package com.drunken.repositorylombaraion.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateJson(
    val year: Int,
    val month: Int,
    val day: Int
) : Parcelable
