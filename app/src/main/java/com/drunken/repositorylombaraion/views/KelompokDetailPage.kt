package com.drunken.repositorylombaraion.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.drunken.repositorylombaraion.R
import com.drunken.repositorylombaraion.databinding.FragmentKelompokDetailPageBinding
import com.drunken.repositorylombaraion.databinding.ItemAnggotaKelompokLayoutBinding
import com.drunken.repositorylombaraion.databinding.ItemHistoryLombaLayoutBinding
import com.drunken.repositorylombaraion.models.HistoryLombaUser
import com.drunken.repositorylombaraion.models.User
import com.drunken.repositorylombaraion.utils.AnggotaDiffUtil
import com.drunken.repositorylombaraion.utils.HistoryLombaDiffUtil
import com.drunken.repositorylombaraion.utils.KelompokDetailPageViewModelFactory
import com.drunken.repositorylombaraion.utils.RecyclerViewAdapter
import com.drunken.repositorylombaraion.viewModels.KelompokDetailPageViewModel
import com.google.android.material.appbar.AppBarLayout

class KelompokDetailPage : Fragment() {

    private lateinit var binding: FragmentKelompokDetailPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKelompokDetailPageBinding.inflate(layoutInflater)

        // Set the toolbar to only show title when its collapsed
        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                binding.collapsingToolbar.title = "Detail Kelompok"
                isShow = true
            } else if (isShow){
                binding.collapsingToolbar.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })

        val args = KelompokDetailPageArgs.fromBundle(requireArguments())
        val viewModelFactory = KelompokDetailPageViewModelFactory(args.idKelompok)
        val viewModel = ViewModelProvider(this, viewModelFactory)[KelompokDetailPageViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.kelompok.observe(viewLifecycleOwner) {
            binding.kelompok = it

            val listAnggotaAdapter = RecyclerViewAdapter<User, ItemAnggotaKelompokLayoutBinding>(AnggotaDiffUtil(), R.layout.item_anggota_kelompok_layout) { user ->
                Log.d("LIST ANGGOTA DEBUG", user.toString())
            }

            listAnggotaAdapter.submitList(it.daftarAnggota)
            binding.listAnggotaRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.listAnggotaRv.adapter = listAnggotaAdapter

            val listHistoryLombaAdapter = RecyclerViewAdapter<HistoryLombaUser, ItemHistoryLombaLayoutBinding>(HistoryLombaDiffUtil(), R.layout.item_history_lomba_layout,) { historyLombaUser ->
                Log.d("LIST HISTORY LOMBA DEBUG", historyLombaUser.toString())
            }

            listHistoryLombaAdapter.submitList(it.historyLomba)
            binding.listLombaRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.listLombaRv.adapter = listHistoryLombaAdapter
        }

        return binding.root
    }
}