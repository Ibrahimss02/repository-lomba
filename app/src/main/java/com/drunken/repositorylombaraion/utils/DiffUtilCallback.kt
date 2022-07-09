package com.drunken.repositorylombaraion.utils

import androidx.recyclerview.widget.DiffUtil
import com.drunken.repositorylombaraion.models.HistoryLomba
import com.drunken.repositorylombaraion.models.HistoryLombaUser
import com.drunken.repositorylombaraion.models.Lomba
import com.drunken.repositorylombaraion.models.User

class LombaDiffUtil: DiffUtil.ItemCallback<Lomba>(){
    override fun areItemsTheSame(oldItem: Lomba, newItem: Lomba): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lomba, newItem: Lomba): Boolean {
        return oldItem == newItem
    }
}

class AlumniLombaDiffUtil: DiffUtil.ItemCallback<HistoryLomba>() {
    override fun areItemsTheSame(oldItem: HistoryLomba, newItem: HistoryLomba): Boolean {
        return oldItem.idKelompok == newItem.idKelompok
    }

    override fun areContentsTheSame(oldItem: HistoryLomba, newItem: HistoryLomba): Boolean {
        return oldItem == newItem
    }
}

class AnggotaDiffUtil: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

class HistoryLombaDiffUtil: DiffUtil.ItemCallback<HistoryLombaUser>() {
    override fun areItemsTheSame(oldItem: HistoryLombaUser, newItem: HistoryLombaUser): Boolean {
        return oldItem.idLomba == newItem.idLomba
    }

    override fun areContentsTheSame(oldItem: HistoryLombaUser, newItem: HistoryLombaUser): Boolean {
        return oldItem == newItem
    }

}