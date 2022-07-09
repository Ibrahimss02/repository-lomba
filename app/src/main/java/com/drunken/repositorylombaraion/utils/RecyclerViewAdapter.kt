package com.drunken.repositorylombaraion.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drunken.repositorylombaraion.R
import com.drunken.repositorylombaraion.databinding.ItemAlumniLombaLayoutBinding
import com.drunken.repositorylombaraion.databinding.ItemAnggotaKelompokLayoutBinding
import com.drunken.repositorylombaraion.databinding.ItemHistoryLombaLayoutBinding
import com.drunken.repositorylombaraion.databinding.ItemLombaLayoutBinding
import com.drunken.repositorylombaraion.models.HistoryLomba
import com.drunken.repositorylombaraion.models.HistoryLombaUser
import com.drunken.repositorylombaraion.models.Lomba
import com.drunken.repositorylombaraion.models.User


/*
    Custom Generic Recycler View Adapter
 */
class RecyclerViewAdapter<T : Any, B : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val layoutId: Int,
    private val itemClickListener: ((T) -> Unit)? = null
) : ListAdapter<T, ViewHolder<B>>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        return ViewHolder<B>(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        ).apply {
            binding
                .root
                .findViewById<CardView>(R.id.lombaCardView)
                .setOnClickListener {
                    itemClickListener?.invoke(getItem(adapterPosition))
                }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }
}

open class ViewHolder<B : ViewDataBinding>(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Any) {
        when (binding) {
            is ItemLombaLayoutBinding -> {
                binding.lomba = item as Lomba
                binding.executePendingBindings()
            }
            is ItemAlumniLombaLayoutBinding -> {
                binding.kelompok = item as HistoryLomba
                binding.executePendingBindings()
            }
            is ItemAnggotaKelompokLayoutBinding -> {
                binding.user = item as User
                binding.executePendingBindings()
            }
            is ItemHistoryLombaLayoutBinding -> {
                binding.history = item as HistoryLombaUser
                binding.executePendingBindings()
            }
        }
    }
}