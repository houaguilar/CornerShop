package com.example.cornershop.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cornershop.R
import com.example.cornershop.data.Resource
import com.example.cornershop.data.model.Counter
import com.example.cornershop.databinding.MainCounterFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.viewmodel.ext.android.viewModel

class MainCounterFragment : Fragment(R.layout.main_counter_fragment), MainCountersView,
    MainCounterAdapter.CounterListClickListener, MainCounterAdapter.CounterSwipeListener {

    private val presenter: MainCountersPresenter by viewModel<MainCounterViewModel>()
    private val adapter by lazy { MainCounterAdapter(this, this) }
    private var counterList: Counter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MainCounterFragmentBinding.bind(view)

        binding.rvMainCounter.adapter = adapter

        binding.rvMainCounter.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMainCounter.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        presenter.setView(this)

        binding.extendFab.setOnClickListener {
            showCreateCounterListDialog()
        }

        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                // do nothing
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.deleteCounterSwipe(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.rvMainCounter)

    }

    private fun showCreateCounterListDialog() {
        activity?.let {
            val todoTitleEditText = EditText(it)
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.title))
                .setView(todoTitleEditText)
//                .setMessage(resources.getString(R.string.supporting_text))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to neutral button press
                    dialog.dismiss()
                }
//                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
//                    // Respond to negative button press
//                }
                .setPositiveButton(resources.getString(R.string.create_counter)) { dialog, which ->
                    // Respond to positive button press
                    val counter = todoTitleEditText.text.toString().trim()
                    addNewCounter(counter)
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun addNewCounter(title: String) {
        presenter.createCounter(title).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Livedata", "Loading ... ")
                }
                is Resource.Success -> {
                    Log.d("Livedata", "${result.data}} ")
                    presenter.getData()
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    override fun showCounters(counters: ArrayList<Counter>?) {
        adapter.setData(counters)
    }

    override fun showError(throwable: Throwable) {

    }

    override fun listItemClicked(counter: Counter) {
        findNavController().navigate(
            MainCounterFragmentDirections.actionMainCounterFragmentToDetailCounterFragment(
                counter.id,
                counter.title,
                counter.count
            )
        )
    }

    override fun onStart() {
        super.onStart()
        presenter.getData()
    }

    override fun counterSwipe(counter: Counter) {
        presenter.deleteCounter(counter.id)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Counters")
        builder.setMessage("Are you sure you want to delete all counters")
        builder.setPositiveButton("Yes") { _, _ ->

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }

}