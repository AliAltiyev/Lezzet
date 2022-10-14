package com.app.lezzet.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.app.lezzet.R
import com.app.lezzet.databinding.ActivityDetailsBinding
import com.app.lezzet.ui.adapter.viewpageradapter.PagerAdapter
import com.app.lezzet.ui.fragments.ingredients.IngredientsFragment
import com.app.lezzet.ui.fragments.instructions.InstructionsFragment
import com.app.lezzet.ui.fragments.overview.OverViewFragment
import com.app.lezzet.util.Constants.Companion.BUNDLE_KEY
import com.app.lezzet.util.Constants.Companion.INGREDIENTS_FRAGMENT_TITLE
import com.app.lezzet.util.Constants.Companion.INSTRUCTIONS_FRAGMENT_TITLE
import com.app.lezzet.util.Constants.Companion.OVERVIEW_FRAGMENT_TITLE
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val navArgs by navArgs<DetailsActivityArgs>()
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = Bundle()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bundle.putParcelable(BUNDLE_KEY, navArgs.result)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listFragments = ArrayList<Fragment>()
        listFragments.add(OverViewFragment())
        listFragments.add(IngredientsFragment())
        listFragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add(OVERVIEW_FRAGMENT_TITLE)
        titles.add(INGREDIENTS_FRAGMENT_TITLE)
        titles.add(INSTRUCTIONS_FRAGMENT_TITLE)

        setViewPagerAdapter(listFragments, titles, bundle)
    }
    private fun setViewPagerAdapter(
        listFragments: ArrayList<Fragment>,
        listTitles: ArrayList<String>,
        bundle: Bundle
    ) = with(binding) {
        val adapter = PagerAdapter(this@DetailsActivity, listFragments, listTitles, bundle)
        viewPager2.adapter = adapter
        viewPager2.currentItem = 0
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
