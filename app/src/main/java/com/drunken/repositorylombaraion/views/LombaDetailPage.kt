package com.drunken.repositorylombaraion.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drunken.repositorylombaraion.R
import com.drunken.repositorylombaraion.databinding.FragmentLombaDetailPageBinding
import com.drunken.repositorylombaraion.databinding.ItemAlumniLombaLayoutBinding
import com.drunken.repositorylombaraion.models.HistoryLomba
import com.drunken.repositorylombaraion.utils.AlumniLombaDiffUtil
import com.drunken.repositorylombaraion.utils.LombaDetailPageViewModelFactory
import com.drunken.repositorylombaraion.utils.RecyclerViewAdapter
import com.drunken.repositorylombaraion.viewModels.LombaDetailPageViewModel
import com.google.android.material.appbar.AppBarLayout

class LombaDetailPage : Fragment() {

    private lateinit var binding: FragmentLombaDetailPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLombaDetailPageBinding.inflate(layoutInflater)

        // Set the toolbar to only show title when its collapsed
        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                binding.collapsingToolbar.title = "Detail Lomba"
                isShow = true
            } else if (isShow){
                binding.collapsingToolbar.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })


        val args = LombaDetailPageArgs.fromBundle(requireArguments())
        val viewModelFactory = LombaDetailPageViewModelFactory(args.idLomba)
        val viewModel = ViewModelProvider(this, viewModelFactory)[LombaDetailPageViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = RecyclerViewAdapter<HistoryLomba, ItemAlumniLombaLayoutBinding>(AlumniLombaDiffUtil(), R.layout.item_alumni_lomba_layout) { history ->
            findNavController().navigate(LombaDetailPageDirections.actionLombaDetailPageToKelompokDetailPage(history.idKelompok))
        }
        binding.historyLombaRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.historyLombaRv.adapter = adapter

        viewModel.lomba.observe(viewLifecycleOwner) {
            adapter.submitList(it.historyLomba)
        }


        return binding.root
    }
}