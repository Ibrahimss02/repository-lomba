package com.drunken.repositorylombaraion.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drunken.repositorylombaraion.models.Lomba
import com.drunken.repositorylombaraion.network.RepositoryLombaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomepageViewModel : ViewModel() {

    private val _listLomba = MutableLiveData<List<Lomba>>()
    val listLomba: LiveData<List<Lomba>>
        get() = _listLomba

    private val _fetching = MutableLiveData<Boolean>(false)
    val fetching: LiveData<Boolean>
        get() = _fetching


    fun fetchAllLomba() {
        _fetching.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val listLomba = RepositoryLombaApi.retrofitService.getAllLomba()

            if (listLomba != null) {
                withContext(Dispatchers.Default) {
                    _listLomba.postValue(listLomba)
                }
            }
        }
    }
}