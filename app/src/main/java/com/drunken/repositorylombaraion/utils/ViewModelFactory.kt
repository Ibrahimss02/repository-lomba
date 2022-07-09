package com.drunken.repositorylombaraion.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drunken.repositorylombaraion.viewModels.KelompokDetailPageViewModel
import com.drunken.repositorylombaraion.viewModels.LombaDetailPageViewModel

class LombaDetailPageViewModelFactory(private val idLomba: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LombaDetailPageViewModel::class.java)) {
            return LombaDetailPageViewModel(idLomba) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}

class KelompokDetailPageViewModelFactory(private val idKelompok: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KelompokDetailPageViewModel::class.java)) {
            return KelompokDetailPageViewModel(idKelompok) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}