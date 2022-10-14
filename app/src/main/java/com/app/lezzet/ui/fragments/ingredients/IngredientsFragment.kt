package com.app.lezzet.ui.fragments.ingredients

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.lezzet.R
import com.app.lezzet.databinding.FragmentIngredientsBinding
import com.app.lezzet.domain.model.Result
import com.app.lezzet.ui.adapter.ingedients.IngredientsAdapter
import com.app.lezzet.util.Constants.Companion.BUNDLE_KEY
import com.app.lezzet.viewBinding

class IngredientsFragment : Fragment(R.layout.fragment_ingredients) {
    private val binding: FragmentIngredientsBinding by viewBinding(FragmentIngredientsBinding::bind)
    private val adapter by lazy { IngredientsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            recyclerView.adapter = adapter
            val bundle: Result? = arguments?.getParcelable(BUNDLE_KEY)
            adapter.submitList(
                bundle?.extendedIngredients
            )
        }
    }

}