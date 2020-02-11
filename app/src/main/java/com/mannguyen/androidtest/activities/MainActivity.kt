package com.mannguyen.androidtest.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.adapters.VolumesAdapter
import com.mannguyen.androidtest.constants.IntentKeys
import com.mannguyen.androidtest.utils.hide
import com.mannguyen.androidtest.utils.loadImage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        val adapter = VolumesAdapter(this).apply {
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

        edtQuery.onActionSearch { query ->
            adapter.search(
                query = query,
                onStart = this@MainActivity::showLoading,
                onCompleted = {
                    hideLoading()
                    hideError()
                },
                onError = {
                    hideLoading()
                    showError(it.toString())
                }
            )
        }

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
