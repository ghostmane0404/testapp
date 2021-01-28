package com.example.testproject.ui.home_page


import android.text.Html
import androidx.fragment.app.FragmentManager
import com.example.testproject.R

import com.example.testproject.core.shared.base.BaseFragment
import com.example.testproject.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.PagingData
import com.example.testproject.core.data.network.responses.TopResponse
import com.example.testproject.core.data.storage.PreferencesUtil
import com.example.testproject.core.shared.adapter.TopGamesAdapter
import com.example.testproject.core.utils.transitionAnim
import kotlinx.android.synthetic.main.toolbar.*

class HomeFragment: BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {
    private val viewModel:HomeViewModel by viewModel()
    private val topAdapter:TopGamesAdapter = TopGamesAdapter()
    override fun onCustomCreate() {
        viewModel.isTimeFinished.observe(viewLifecycleOwner, timeObserver)
            binding.apply {
                recycler.adapter = topAdapter
                recycler.hasFixedSize()
            }
        viewModel.getTop().observe(viewLifecycleOwner, topGamesObserver)
        if(PreferencesUtil.isReviewed){
            viewModel.timer.start()
        }
        binding.showModal.setOnClickListener {
            showModal()
        }
    }

    //timer observer
    private val timeObserver = Observer<Boolean> {
        if (it == true) {
            showModal()
        }
    }
    private var topGamesObserver = Observer<PagingData<TopResponse>> {
        topAdapter.submitData(viewLifecycleOwner.lifecycle, it)
    }

    private fun showModal(){
        NavHostFragment.findNavController(this).navigate(
            R.id.reviewAppWidget,null,
            this.transitionAnim().build()
        )
    }

}