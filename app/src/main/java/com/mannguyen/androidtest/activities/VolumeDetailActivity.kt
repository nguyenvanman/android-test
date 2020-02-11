package com.mannguyen.androidtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.constants.IntentKeys
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.services.datasources.VolumeDataSource
import com.mannguyen.androidtest.utils.hide
import com.mannguyen.androidtest.utils.show
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
        refreshLayout.setOnRefreshListener {
            if (!isLoading) {
                getVolume()
            }
        }
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
                    showError(it.message.toString())
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
        tvError.show()
        tvError.text = message
    }

    private fun hideError() {
        content.show()
        tvError.hide()
    }

    private fun display(bookVolume: BookVolume?) {
        bookVolume?.volumeInfo?.apply {
            imgThumbnail.show(this@VolumeDetailActivity, imageLinks?.highestQualityUrl()?.toHttps())
            tvTitle.text = title
            tvDescription.text = description
            tvPublisher.text = String.format(getString(R.string.publish_info), publisher, publishDate)
            tvAuthors.text = String.format(getString(R.string.authors), authors.joinToString())
        }
    }

}
