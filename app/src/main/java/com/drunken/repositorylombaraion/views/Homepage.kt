package com.drunken.repositorylombaraion.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.drunken.repositorylombaraion.R
import com.drunken.repositorylombaraion.databinding.FragmentHomepageBinding
import com.drunken.repositorylombaraion.databinding.ItemLombaLayoutBinding
import com.drunken.repositorylombaraion.models.Lomba
import com.drunken.repositorylombaraion.utils.RecyclerViewAdapter
import com.drunken.repositorylombaraion.utils.LombaDiffUtil
import com.drunken.repositorylombaraion.viewModels.HomepageViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Homepage : Fragment() {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var bottomSheet: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(layoutInflater)

        Toast.makeText(context, "Aplikasinya belum selesai ngab. Keburu deadline", Toast.LENGTH_LONG).show()

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheetHome).apply {
            expandedOffset = 250
            isFitToContents = false
            isHideable = true
            state = BottomSheetBehavior.STATE_HIDDEN
        }

        val viewModel = ViewModelProvider(this)[HomepageViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        viewModel.fetching.observe(viewLifecycleOwner) {
            if (it) {
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                binding.bottomSheetHome.visibility = View.VISIBLE
                binding.fetchingIndicator.visibility = View.VISIBLE
            }
        }

        viewModel.listLomba.observe(viewLifecycleOwner) {
            val adapter = RecyclerViewAdapter<Lomba, ItemLombaLayoutBinding>(
                LombaDiffUtil(),
                R.layout.item_lomba_layout
            ) { lomba ->
                findNavController().navigate(HomepageDirections.actionHomepageToLombaDetailPage(lomba.id))
            }

            binding.rvHomepage.adapter = adapter
            binding.rvHomepage.visibility = View.VISIBLE
            binding.fetchingIndicator.visibility = View.INVISIBLE
            adapter.submitList(it)
        }

        return binding.root
    }
}