package com.app.lezzet.ui.fragments.jokes

import androidx.fragment.app.Fragment
import com.app.lezzet.R
import com.app.lezzet.databinding.ActivityMainBinding.bind
import com.app.lezzet.databinding.FragmentJokeBinding
import com.app.lezzet.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokeFragment : Fragment(R.layout.fragment_joke) {
    private val binding by viewBinding(FragmentJokeBinding::bind)
}