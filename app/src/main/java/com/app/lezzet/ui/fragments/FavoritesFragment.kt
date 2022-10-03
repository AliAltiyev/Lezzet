package com.app.lezzet.ui.fragments

import androidx.fragment.app.Fragment
import com.app.lezzet.R
import com.app.lezzet.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(com.app.lezzet.databinding.FragmentFavoritesBinding::bind)


}