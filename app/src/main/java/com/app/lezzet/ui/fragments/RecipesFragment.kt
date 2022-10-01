package com.app.lezzet.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.lezzet.databinding.FragmentRecipesBinding
import com.app.lezzet.ui.adapter.RecipesAdapter
import com.app.lezzet.ui.viewmodel.MainViewModel
import com.app.lezzet.util.Constants.Companion.API_KEY
import com.app.lezzet.util.NetworkResult
import com.app.lezzet.viewBinding
import com.yonder.statelayout.State


class RecipesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { RecipesAdapter() }
    private val binding by viewBinding(FragmentRecipesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setView()
    }

    private fun setView() {
        binding.run {
            viewModel.getRecipes(applyQueries())
            viewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResult.Loading -> state.setState(State.LOADING)
                    is NetworkResult.Success -> {
                        response.data?.let { adapter.submitList(it.results) }
                        state.setState(State.CONTENT)
                    }
                    is NetworkResult.Error -> state.setState(State.ERROR)
                }

            }
            recyclerView.adapter = adapter

        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["number"] = "number"
        map["apiKey"] = API_KEY
        map["type"] = "snack"
        map["diet"] = "vegan"
        map["addRecipeInformation"] = "true"
        map["fillIngredients"] = "true"
        return map
    }

}