package com.app.lezzet.ui.fragments.instructions

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.app.lezzet.R
import com.app.lezzet.databinding.FragmentInstructionsBinding
import com.app.lezzet.domain.model.Result
import com.app.lezzet.util.Constants.Companion.BUNDLE_KEY
import com.app.lezzet.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : Fragment(R.layout.fragment_instructions) {
    private val binding: FragmentInstructionsBinding by viewBinding(FragmentInstructionsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            webView.webViewClient = object : WebViewClient() {}
            val bundle: Result? = arguments?.getParcelable(BUNDLE_KEY)
            webView.loadUrl(bundle?.sourceUrl!!)
        }
    }

}