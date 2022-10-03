package com.app.lezzet.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.lezzet.R
import com.app.lezzet.databinding.FragmentRecipesBinding
import com.app.lezzet.ui.adapter.RecipesAdapter
import com.app.lezzet.ui.viewmodel.MainViewModel
import com.app.lezzet.ui.viewmodel.RecipeViewModel
import com.app.lezzet.util.Constants.Companion.NO_INTERNET_CONNECTION
import com.app.lezzet.util.NetworkResult
import com.app.lezzet.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.yonder.statelayout.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private val mainViewModel: MainViewModel by viewModels()
    private val recipeViewModel: RecipeViewModel by viewModels()
    private val adapter by lazy { RecipesAdapter() }
    private val binding by viewBinding(FragmentRecipesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        fetchFromDatabase()
    }

    private fun fetchFromApi() {
        Log.d("Fetch", "Fetch from api")
        binding.run {
            mainViewModel.getRecipes(recipeViewModel.applyQueries())
            mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResult.Loading -> state.setState(State.LOADING)
                    is NetworkResult.Success -> {
                        response.data?.let { foodRecipes ->
                            adapter.submitList(foodRecipes.results)
                            state.setState(State.CONTENT)
                        }
                    }
                    is NetworkResult.Error -> {
                        state.setState(State.ERROR)
                        Snackbar.make(requireView(), NO_INTERNET_CONNECTION, Snackbar.LENGTH_LONG)
                            .show()
                    }
                }

            }
        }
    }

    private fun fetchFromDatabase() {
        mainViewModel.fetchRecipesFromRoom.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Log.d("Fetch", "Fetch from database")
                adapter.submitList(it[0].foodRecipe.results)
            } else {
                fetchFromApi()
            }
        }
    }
}
