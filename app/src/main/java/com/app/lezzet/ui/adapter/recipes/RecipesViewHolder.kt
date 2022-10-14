package com.app.lezzet.ui.adapter.recipes

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.lezzet.R
import com.app.lezzet.databinding.RecyclerViewItemLayoutBinding
import com.app.lezzet.domain.model.Result
import com.app.lezzet.util.Constants.Companion.CROSS_FADE_DURATION_MILLIS
import com.app.lezzet.util.toJsoup

class RecipesViewHolder(val view: View) :
    RecyclerView.ViewHolder(view) {

    private val binding = RecyclerViewItemLayoutBinding.bind(view)

    fun bind(result: Result, context: Context) {
        binding.run {
            recipeTitleText.text = result.title
            recipeDescriptionText.text = result.summary.toJsoup()
            healthIconText.text = result.aggregateLikes.toString()
            if (result.aggregateLikes in 50..1000) {
                healthImageIcon.setColorFilter(ContextCompat.getColor(context, R.color.red))
                healthIconText.setTextColor(ContextCompat.getColor(context, R.color.red))
            } else {
                healthImageIcon.setColorFilter(ContextCompat.getColor(context, R.color.gray))
                healthIconText.setTextColor(ContextCompat.getColor(context, R.color.gray))
            }
            timeIconText.text = result.readyInMinutes.toString()
            if (result.vegan) {
                veganCheckIcon.setColorFilter(ContextCompat.getColor(context, R.color.green))
                veganIconText.setTextColor(ContextCompat.getColor(context, R.color.green))
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