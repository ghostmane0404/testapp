package com.example.testproject.core.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DataBinding : ViewDataBinding>(
    private val layoutId: Int
) : Fragment() {

    abstract fun onCustomCreate()
    protected lateinit var binding: DataBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        onCustomCreate()
        return binding.root
    }




}


