package com.dishant.kotlinpractices.recyclerViewDemo.fragment

import androidx.fragment.app.Fragment

abstract class BaseFragment<T> : Fragment() {
    protected var _binding : T? = null
    protected  val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}