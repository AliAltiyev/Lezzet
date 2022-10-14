package com.app.lezzet.ui.fragments.overview

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.app.lezzet.R
import com.app.lezzet.databinding.FragmentOverviewBinding
import com.app.lezzet.domain.model.Result
import com.app.lezzet.util.Constants.Companion.BUNDLE_KEY
import com.app.lezzet.util.toJsoup
import com.app.lezzet.viewBinding
import kotlinx.android.synthetic.main.fragment_overview.*

class OverViewFragment : Fragment(R.layout.fragment_overview) {
    private val binding: FragmentOverviewBinding by viewBinding(FragmentOverviewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Result? = arguments?.getParcelable(BUNDLE_KEY)
        binding.run {
            gradient_image_view.load(bundle?.image)
            name_text_view.text = bundle?.title
            time_text_view.text = bundle?.readyInMinutes.toString()
            like_image_text.text = bundle?.aggregateLikes.toString()
            description_text_view.text = bundle?.summary?.toJsoup()
            if (bundle?.vegan == true) {
                veganCheckIcon.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                veganIconText.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            if (bundle?.veryHealthy == true) {
                healthyIcon.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                healthyIconTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            }
            if (bundle?.cheap == true) {
                cheapCheckIcon.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                cheapCheckTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            }
        }
    }
}