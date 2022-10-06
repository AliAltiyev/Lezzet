package com.app.lezzet.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
class RecipesFragment : Fragment(R.layout.fragment_recipes), SearchView.OnQueryTextListener {
    private val navArgs by navArgs<RecipesFragmentArgs>()
    private val mainViewModel: MainViewModel by viewModels()
    private val recipeViewModel: RecipeViewModel by viewModels()
    private val adapter by lazy { RecipesAdapter() }
    private val binding by viewBinding(FragmentRecipesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.recyclerView.adapter = adapter
        fetchFromDatabase()
        setUpFloatingActionBar()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(text: String?): Boolean {

        return true
        Log.d("save", text.toString())
    }

    override fun onQueryTextChange(text: String?): Boolean {
        Log.d("save", text.toString())
        return true
    }

    private fun fetchFromDatabase() {
        mainViewModel.fetchRecipesFromRoom.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && navArgs.answerFromBottomSheet) {
                Log.d("Fetch", "Fetch from database")
                adapter.submitList(it[0].foodRecipe.results)
            } else {
                fetchFromApi()
            }
        }
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

    private fun setUpFloatingActionBar() {
        binding.floatingActionBar.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_bottomSheet)
        }
    }
}
