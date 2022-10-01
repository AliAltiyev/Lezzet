package com.app.lezzet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.app.lezzet.databinding.RecyclerViewItemLayoutBinding
import com.app.lezzet.domain.model.Result

class RecipesAdapter : ListAdapter<Result, RecipesViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view = RecyclerViewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)

    }
}