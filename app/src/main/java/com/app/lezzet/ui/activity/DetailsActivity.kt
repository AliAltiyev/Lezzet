package com.app.lezzet.ui.activity

import android.R.id
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.app.lezzet.R
import com.app.lezzet.data.database.entity.FavoritesRoomModel
import com.app.lezzet.databinding.ActivityDetailsBinding
import com.app.lezzet.ui.adapter.viewpageradapter.PagerAdapter
import com.app.lezzet.ui.fragments.ingredients.IngredientsFragment
import com.app.lezzet.ui.fragments.instructions.InstructionsFragment
import com.app.lezzet.ui.fragments.overview.OverViewFragment
import com.app.lezzet.ui.viewmodel.DetailsViewModel
import com.app.lezzet.util.Constants.Companion.BUNDLE_KEY
import com.app.lezzet.util.Constants.Companion.INGREDIENTS_FRAGMENT_TITLE
import com.app.lezzet.util.Constants.Companion.INSTRUCTIONS_FRAGMENT_TITLE
import com.app.lezzet.util.Constants.Companion.OVERVIEW_FRAGMENT_TITLE
import com.app.lezzet.util.ZERO
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val navArgs by navArgs<DetailsActivityArgs>()
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = Bundle()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bundle.putParcelable(BUNDLE_KEY, navArgs.result)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listFragments = ArrayList<androidx.fragment.app.Fragment>()
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
        listFragments: ArrayList<androidx.fragment.app.Fragment>,
        listTitles: ArrayList<String>,
        bundle: Bundle
    ) = with(binding) {
        val adapter = PagerAdapter(this@DetailsActivity, listFragments, listTitles, bundle)
        viewPager2.adapter = adapter
        viewPager2.currentItem = Int.ZERO
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == id.home) {
            finish()
            return true
        } else if (item.itemId == R.id.favorites_menu) {
            saveToFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(item: MenuItem) {
        val model = FavoritesRoomModel(Int.ZERO, navArgs.result)
        viewModel.insertToFavorites(model)
        item.icon.setTint(ContextCompat.getColor(this, R.color.red))
        Snackbar.make(binding.root, getString(R.string.added_to_favorites), Snackbar.LENGTH_LONG)
            .setAction("Okay") {}
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites_menu, menu)
        checkFavorites(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun checkFavorites(item: Menu?) {
        viewModel.getAllFromFavorites.observe(this) { list ->
            for (saved in list) {
                if (saved.result.id == navArgs.result.id) {
                    item?.findItem(R.id.favorites_menu)?.icon?.setTint(
                        ContextCompat.getColor(
                            this,
                            R.color.red
                        )
                    )
                } else {
                    item?.findItem(R.id.favorites_menu)?.icon?.setTint(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                }
            }

        }
    }
}
