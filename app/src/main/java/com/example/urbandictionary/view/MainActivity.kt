package com.example.urbandictionary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionary.R
import com.example.urbandictionary.adapter.DefinitionAdapter
import com.example.urbandictionary.databinding.ActivityMainBinding
import com.example.urbandictionary.model.Definition
import com.example.urbandictionary.viewmodel.UrbanViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

import kotlin.Comparator

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: UrbanViewModel
    private val compositeDisposable = CompositeDisposable()
    private lateinit var comparator: Comparator<Definition>
    private lateinit var adapter: DefinitionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

          supportActionBar?.hide()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)
            .get(UrbanViewModel::class.java)


        binding.sortSpinner.onItemSelectedListener
        binding.searchEdittext.requestFocus()

        //Definitions of the search word are obtained throught the api call.
        viewModel.makeCall(binding.searchEdittext.text.toString())
        binding.searchButton.setOnClickListener {

            binding.itemsRecyclerview.visibility = View.INVISIBLE
            binding.loadingProgressbar.visibility = View.VISIBLE
            compositeDisposable.add(viewModel.getDefinitions(binding.searchEdittext.text.toString())
                .subscribe({ definitions ->

                    displayDefinitions(definitions, binding.sortSpinner.selectedItem.toString())
                    binding.loadingProgressbar.visibility = View.INVISIBLE
                    binding.itemsRecyclerview.visibility = View.VISIBLE

                }, { throwable ->
                    Log.d("TAG_ERROR", throwable.message.toString())
                })
            )

        }

        //Drop-down menu is filled with the choices or sorting by thumbs up or thumbs down
        ArrayAdapter.createFromResource(
            this,
            R.array.sort_choices,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sortSpinner.adapter = adapter

        }


    }


    //Adapter is initialized and recycler view is displayed with the definitions.
    private fun displayDefinitions(repositories: List<Definition>, selectedSort: String){
        if(selectedSort == "Thumbs Up") {
            comparator = compareByDescending<Definition> { it.thumbsUp }
            adapter = DefinitionAdapter(repositories.sortedWith(comparator))

        } else if(selectedSort == "Thumbs Down") {
            comparator = compareByDescending<Definition> { it.thumbsDown }
            adapter = DefinitionAdapter(repositories.sortedWith(comparator))
        } else {
            adapter = DefinitionAdapter(repositories)
        }

        items_recyclerview.adapter = adapter
        val linear = LinearLayoutManager(this)
        items_recyclerview.layoutManager = linear

    }


}
