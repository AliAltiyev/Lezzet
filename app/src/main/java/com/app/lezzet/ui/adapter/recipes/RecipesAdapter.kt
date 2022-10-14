package com.app.lezzet.ui.adapter.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.lezzet.R
import com.app.lezzet.domain.model.Result
import com.app.lezzet.ui.adapter.base.BaseAdapter
import com.app.lezzet.ui.fragments.recipes.RecipesFragmentDirections
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    BaseAdapter<Result>(
        itemsSame = { old, new -> old.id == new.id },
        contentsSame = { old, new -> old.id == new.id }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item_layout, parent, false)
        return RecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecipesViewHolder -> {
                getItem(position)?.let { item ->
                    holder.bind(item, context = context)
                    holder.itemView.setOnClickListener {
                        val action =
                            RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(item)
                        it.findNavController().navigate(action)
                    }
                }
            }
        }
    }
}
