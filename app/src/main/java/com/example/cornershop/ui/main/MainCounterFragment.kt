package com.example.cornershop.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cornershop.R

class MainCounterFragment : Fragment() {

    companion object {
        fun newInstance() = MainCounterFragment()
    }

    private lateinit var viewModel: MainCounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_counter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainCounterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}