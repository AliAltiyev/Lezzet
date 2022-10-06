package com.app.lezzet.ui.fragments.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.lezzet.databinding.BottomSheetDialogBinding
import com.app.lezzet.ui.viewmodel.RecipeViewModel
import com.app.lezzet.util.Constants.Companion.DEFAULT_COUNTRY
import com.app.lezzet.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BottomSheet : BottomSheetDialogFragment() {
    private var defMealType = DEFAULT_MEAL_TYPE
    private var defMEalTypeId = 0
    private var defCountry = DEFAULT_COUNTRY
    private var defCountryId = 0

    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var binding: BottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogBinding.inflate(inflater, container, false)
        setBottomSheetChipGroup()
        saveFieldsToDataStore()
        readDataFromDataStore()
        return binding.root
    }

    private fun readDataFromDataStore() {
        recipeViewModel.readMealAndCountry.asLiveData().observe(viewLifecycleOwner) { types ->
            defMealType = types.selectedMealType
            defCountry = types.selectedCountry
            updateChip(types.selectedCountryId, binding.countryChipGroupId)
            updateChip(types.selectedMealTypeId, binding.mealTypeChipGroupId)
        }
    }

    private fun updateChip(selectedCountryId: Int, chipGroup: ChipGroup) {
        if (selectedCountryId != 0) {
            try {
                chipGroup.findViewById<Chip>(selectedCountryId)
            } catch (e: IllegalStateException) {
                e.localizedMessage
            }
        }
    }

    private fun setBottomSheetChipGroup() {
        binding.run {
            //Getting meal type text and Id
            mealTypeChipGroupId.setOnCheckedChangeListener { group, checkedId ->
                val chip = group.findViewById<Chip>(checkedId)
                defMealType = chip.text.toString().lowercase(Locale.ROOT)
                defMEalTypeId = checkedId
            }
            //Getting country text and Id
            countryChipGroupId.setOnCheckedChangeListener { group, checkedId ->
                val chip = group.findViewById<Chip>(checkedId)
                defCountry = chip.text.toString().lowercase(Locale.ROOT)
                defCountryId = checkedId
            }
        }
    }

    private fun saveFieldsToDataStore() {
        binding.run {
            searchButton.setOnClickListener {
                recipeViewModel.saveMealAndDietType(
                    mealType = defMealType,
                    mealTypeId = defMEalTypeId,
                    country = defCountry,
                    countryId = defCountryId
                )
                val action = BottomSheetDirections.actionBottomSheetToRecipesFragment(true)
                findNavController().navigate(action)
            }
        }
    }
}