package com.app.lezzet.ui.adapter.ingedients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.lezzet.R
import com.app.lezzet.domain.model.ExtendedIngredients
import com.app.lezzet.ui.adapter.base.BaseAdapter
import com.app.lezzet.util.Constants.Companion.INGREDIENTS_BASE_URL
import kotlinx.android.synthetic.main.ingredients_recycler_item_raw.view.*
import java.util.*

class IngredientsAdapter : BaseAdapter<ExtendedIngredients>(
    itemsSame = { old, new -> old.amount == new.amount },
    contentsSame = { old, new -> new.amount == old.amount }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.ingredients_recycler_item_raw,
                parent, false
            )
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is IngredientsViewHolder ->
                holder.itemView.run {
                    ingredient_image.load(INGREDIENTS_BASE_URL + item.image)
                    name_text_view.text = item.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                    amount_text_view.text = item.amount.toString()
                    unit_text_view.text = item.unit
                    consistency_text_view.text = item.consistency
                    original_text_view.text = item.original
                }
        }
    }
}