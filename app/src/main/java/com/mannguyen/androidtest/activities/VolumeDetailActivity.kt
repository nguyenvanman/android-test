package com.mannguyen.androidtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.constants.IntentKeys

class VolumeDetailActivity : AppCompatActivity() {

    private var volumeId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume_detail)

        getDataFromIntent()
    }

    private fun getDataFromIntent() {
        volumeId = intent.getStringExtra(IntentKeys.VolumeId)
    }

}
