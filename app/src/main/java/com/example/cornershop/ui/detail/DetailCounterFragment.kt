package com.example.cornershop.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.cornershop.R
import com.example.cornershop.data.Resource
import com.example.cornershop.databinding.DetailCounterFragmentBinding
import com.example.cornershop.ui.main.MainCounterViewModel
import com.example.cornershop.ui.main.MainCountersPresenter
import org.koin.android.viewmodel.ext.android.viewModel

class DetailCounterFragment : Fragment(R.layout.detail_counter_fragment) {

    private lateinit var binding: DetailCounterFragmentBinding
    private val presenter: MainCountersPresenter by viewModel<MainCounterViewModel>()
    private val args by navArgs<DetailCounterFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DetailCounterFragmentBinding.bind(view)

        binding.txtNameCounter.text = args.title
        binding.txtCounter.text = args.count.toString()

        binding.btnAddition.setOnClickListener {
            val idCounterInc = args.id
            incrementCounter(idCounterInc)
            binding.txtCounter.text
        }

        binding.btnDecrement.setOnClickListener {
            val idCounterDec = args.id
            decrementCounter(idCounterDec)
        }

        binding.btnDeleteCounter.setOnClickListener {
            val idDeleteCounter = args.id
            deleteCounterInDetail(idDeleteCounter)
            activity?.onBackPressed()
        }
    }

    private fun incrementCounter(id: String) {
        presenter.incCounter(id).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Log.d("Livedata", "Loading ... ")
                }
                is Resource.Success -> {
                    val cc = it.data.mapIndexed { index, counter ->
                        counter.count
                    }
                    binding.txtCounter.text = cc.toString()
                    Log.d("Livedata", "${it.data} ")
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    private fun decrementCounter(id: String) {
        presenter.decCounter(id).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Log.d("Livedata", "Loading ... ")
                }
                is Resource.Success -> {
                    val cc = it.data.mapIndexed { index, counter ->
                        counter.count
                    }
                    binding.txtCounter.text = cc.toString()

                    Log.d("Livedata", "${it.data}} ")
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    private fun deleteCounterInDetail(id: String) {
        presenter.deleteCounter(id)
    }
}