package com.mannguyen.androidtest.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.adapters.VolumesAdapter
import com.mannguyen.androidtest.constants.IntentKeys
import com.mannguyen.androidtest.utils.hide
import com.mannguyen.androidtest.utils.loadImage
import com.mannguyen.androidtest.widgets.HasSubmitActionSearchView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    lateinit var adapter: VolumesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val actionSearch = menu!!.findItem(R.id.actionSearch)
        val searchView = actionSearch.actionView as HasSubmitActionSearchView
        setUpSearchView(searchView)
        return true
    }

    private fun setUpSearchView(searchView: HasSubmitActionSearchView) {
        searchView.apply {
            queryHint = getString(R.string.edit_text_search_book_volume_hint)
            searchView.onSearchSubmit {
                search(it)
            }
        }
    }

    private fun search(query: String) {
        adapter.search(
            query = query,
            onStart = this@MainActivity::showLoading,
            onCompleted = {
                hideLoading()
                hideError()
            },
            onError = { error ->
                hideLoading()
                showError(error.toString())
            }
        )
    }

    private fun initialize() {
        adapter = VolumesAdapter(this).apply {
            onLoadMoreError = {
                toast(it.toString())
            }

            onItemClick = { bookVolume ->
                val intent = Intent(this@MainActivity, VolumeDetailActivity::class.java).apply {
                    putExtra(IntentKeys.VolumeId, bookVolume?.id)
                }

                startActivity(intent)
            }
        }

        rvVolumes.adapter = adapter

        rvVolumes.onScrolledToBottom {
            adapter.loadMore()
        }
    }

    private fun showLoading() {
        pbLoading.loadImage()
        vCover.loadImage()
    }

    private fun hideLoading() {
        pbLoading.hide()
        vCover.hide()
    }

    private fun showError(message: String) {
        rvVolumes.hide()
        tvError.loadImage()
        tvError.text = message
    }

    private fun hideError() {
        rvVolumes.loadImage()
        tvError.hide()
    }

}
