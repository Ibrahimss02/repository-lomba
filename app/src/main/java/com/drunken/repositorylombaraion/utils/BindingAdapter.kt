package com.drunken.repositorylombaraion.utils

import android.text.TextUtils
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drunken.repositorylombaraion.databinding.ItemAlumniLombaLayoutBinding
import com.drunken.repositorylombaraion.models.DateJson
import com.drunken.repositorylombaraion.models.HistoryLomba
import com.drunken.repositorylombaraion.models.User
import java.text.DateFormatSymbols

@BindingAdapter("hasil", "tanggal")
fun TextView.bindHasilLombaToTextView(hasil: String, tahun: DateJson) {
    this.text = hasil + " (${tahun.year})"
}

@BindingAdapter("dateToText")
fun TextView.bindDateToTextView(date: DateJson?) {
    if (date != null) {
        this.text = "${date.day} ${DateFormatSymbols().months[date.month - 1]}"
    }
}

@BindingAdapter("statusLomba")
fun TextView.bindStatusLomba(statusLomba: Int) {
    this.text = when (statusLomba) {
        1 -> "Selesai"
        2 -> "Berjalan"
        3 -> "Pendaftaran"
        else -> "TBD"
    }
}

@BindingAdapter("marqueeOverflowText")
fun TextView.bindAutoscrollToTextView(bool: Boolean) {
    this.apply {
        ellipsize = TextUtils.TruncateAt.MARQUEE
        marqueeRepeatLimit = -1
        isFocusableInTouchMode = true
        focusable = 0x00000010
        isSingleLine = true
        isSelected = true
    }
}