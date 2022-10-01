package com.app.lezzet.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.app.lezzet.domain.model.Result

class RecipeDiffUtil : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}