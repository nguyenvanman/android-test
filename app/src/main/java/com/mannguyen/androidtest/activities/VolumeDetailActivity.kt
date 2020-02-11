package com.mannguyen.androidtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.constants.IntentKeys
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.services.datasources.VolumeDataSource
import com.mannguyen.androidtest.utils.hide
import com.mannguyen.androidtest.utils.loadImage
import com.mannguyen.androidtest.utils.setTextFromHtml
import com.mannguyen.androidtest.utils.toHttps
import kotlinx.android.synthetic.main.activity_volume_detail.*

class VolumeDetailActivity : AppCompatActivity() {

    private var volumeId: String? = null

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume_detail)

        getDataFromIntent()

        initialize()

        getVolume()
    }

    private fun initialize() {
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        refreshLayout.setOnRefreshListener {
            if (!isLoading) {
                getVolume()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    private fun getDataFromIntent() {
        volumeId = intent.getStringExtra(IntentKeys.VolumeId)
    }

    private fun getVolume() {
        volumeId?.apply {
            showLoading()
            hideError()
            VolumeDataSource.getVolume(
                volumeId = this,
                onSuccess = {
                    display(it)
                    hideLoading()
                },
                onError = {
                    hideLoading()
                    showError(it.toString())
                }
            )
        }
    }

    private fun showLoading() {
        refreshLayout.isRefreshing = true
        isLoading = true
    }

    private fun hideLoading() {
        refreshLayout.isRefreshing = false
        isLoading = false
    }

    private fun showError(message: String) {
        content.hide()
        tvError.loadImage()
        tvError.text = message
    }

    private fun hideError() {
        content.loadImage()
        tvError.hide()
    }

    private fun display(bookVolume: BookVolume) {
        bookVolume.volumeInfo?.apply {
            imgThumbnail.loadImage(this@VolumeDetailActivity, imageLinks?.highestQualityUrl()?.toHttps())
            tvTitle.text = title
            tvDescription.setTextFromHtml(description)
            tvPublisher.text = String.format(getString(R.string.publish_info), publisher, publishDate)
            tvAuthors.text = String.format(getString(R.string.authors), authors.joinToString())
        }
    }

}
