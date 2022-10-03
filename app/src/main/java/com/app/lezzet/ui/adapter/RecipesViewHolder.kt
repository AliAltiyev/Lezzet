package com.app.lezzet.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.lezzet.R
import com.app.lezzet.databinding.RecyclerViewItemLayoutBinding
import com.app.lezzet.domain.model.Result
import com.app.lezzet.util.Constants.Companion.CROSS_FADE_DURATION_MILLIS

class RecipesViewHolder(private val binding: RecyclerViewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor")
    fun bind(result: Result) {
        binding.run {
            recipeTitleText.text = result.title
            recipeDescriptionText.text = result.summary
            healthIconText.text = result.aggregateLikes.toString()
            if (result.aggregateLikes in 1..10) {
                healthImageIcon.setColorFilter(R.color.toolbar_color)
                healthIconText.setTextColor(R.color.toolbar_color)
            } else if (result.aggregateLikes in 85..90) {
                healthImageIcon.setColorFilter(R.color.green)
                healthIconText.setTextColor(R.color.green)
            } else if (result.aggregateLikes in 90..100) {
                healthImageIcon.setColorFilter(R.color.toolbar_color2)
                healthIconText.setTextColor(R.color.toolbar_color2)
            } else {
                healthImageIcon.setColorFilter(R.color.gray)
                healthIconText.setTextColor(R.color.gray)
            }
            timeIconText.text = result.readyInMinutes.toString()
            if (result.vegan) {
                veganIcon.setColorFilter(R.color.toolbar_color)
                veganIconText.setTextColor(R.color.toolbar_color)
            }
            recipeImageView.load(
                result.image,
            ) {
                crossfade(CROSS_FADE_DURATION_MILLIS)
                error(R.drawable.placeholder)
            }
        }
        binding.executePendingBindings()
    }

}