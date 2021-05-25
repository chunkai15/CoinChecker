package com.spiraldev.cryptoticker.ui.home.coinsList

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akki.circlemenu.CircleMenu
import com.akki.circlemenu.OnCircleMenuItemClicked
import com.iammert.library.ui.multisearchviewlib.MultiSearchView
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.adapters.CoinsListAdapter
import com.spiraldev.cryptoticker.adapters.OnItemClickCallback
import com.spiraldev.cryptoticker.databinding.FragmentListBinding
import com.spiraldev.cryptoticker.core.common.MainNavigationFragment
import com.spiraldev.cryptoticker.ui.projectProfile.ProjectProfileActivity
import com.spiraldev.cryptoticker.util.Constants
import com.spiraldev.cryptoticker.util.extensions.doOnChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class CoinListFragment : MainNavigationFragment(), OnItemClickCallback, OnCircleMenuItemClicked {

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding
    private var coinsListAdapter = CoinsListAdapter(this)
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@CoinListFragment.viewModel
            }
        observeViewModel()
        return binding.root
    }

    private fun doTheAutoRefresh() {
        handler.postDelayed(Runnable {
            // auto refresh
            viewModel.loadCoinsFromApi()
            doTheAutoRefresh()
        }, 6000)
    }


    override fun onMenuItemClicked(id: Int){
        when (id) {
            R.drawable.ic_az -> coinsListAdapter.sortNameAZ()
            R.drawable.ic_az_up -> coinsListAdapter.sortNameZA()
            R.drawable.ic_number -> coinsListAdapter.sortNumber()
            R.drawable.ic_number_up -> coinsListAdapter.sortNumberup()
            R.drawable.ic_baseline_arrow_downward_24 -> coinsListAdapter.sortdowntrend()
            R.drawable.ic_baseline_arrow_upward_24 -> coinsListAdapter.sortuptrend()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        viewModel.loadCoinsFromApi()

        circle_menu.setOnMenuItemClickListener(onCircleMenuItemClicked = this)

        //Search
        search_coin.setSearchViewListener(object : MultiSearchView.MultiSearchViewListener {
            override fun onItemSelected(index: Int, s: CharSequence) {
                viewModel.loadCoinsFromApi()
                coinsListAdapter.filter.filter(s)
            }

            override fun onTextChanged(index: Int, s: CharSequence) {
                viewModel.loadCoinsFromApi()
                coinsListAdapter.filter.filter(s)
            }

            override fun onSearchComplete(index: Int, s: CharSequence) {
                coinsListAdapter.filter.filter(s)
            }

            override fun onSearchItemRemoved(index: Int) {
                viewModel.loadCoinsFromApi()
            }

        })

        doTheAutoRefresh()


//        search_coin.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//            override fun onQueryTextChange(newText: String?): Boolean {
//                coinsListAdapter.filter.filter(newText)
//                return false
//            }
//
//        })
    }


    override fun onBackPressed() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        coinsListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coinsListAdapter
        }
    }

    override fun observeViewModel() {
        viewModel.isLoading.doOnChange(this) {
            coinsListLoading.visibility =
                if (viewModel.isListEmpty() && it) View.VISIBLE else View.GONE

            if (it) {
                coinsListErrorView.visibility = View.GONE
            }
        }

        viewModel.coinsListData.doOnChange(this) {
            coinsListAdapter.updateList(it)

            coinsListErrorView.visibility =
                if (viewModel.isListEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.favouriteStock.doOnChange(this) {
            it?.let {
                showToast(
                    getString(if (it.isFavourite) R.string.added_to_favourite else R.string.removed_to_favourite).format(
                        it.symbol
                    )
                )
            }
        }
    }


    override fun onItemClick(symbol: String, id: String, name: String) {
        requireActivity().run {
            startActivity(
                Intent(this, ProjectProfileActivity::class.java)
                    .apply {
                        putExtra(Constants.EXTRA_SYMBOL, symbol)
                        putExtra(Constants.EXTRA_SYMBOL_ID, id)
                        putExtra(Constants.EXTRA_NAME_COIN, name)
                    }
            )
        }

    }


    override fun onFavouriteClicked(symbol: String) {
        viewModel.updateFavouriteStatus(symbol)
    }
}

