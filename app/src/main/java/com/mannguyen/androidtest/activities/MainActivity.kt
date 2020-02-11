package com.mannguyen.androidtest.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.adapters.VolumesAdapter
import com.mannguyen.androidtest.services.datasources.VolumeDataSource
import com.mannguyen.androidtest.utils.hide
import com.mannguyen.androidtest.utils.show
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: VolumesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        adapter = VolumesAdapter(this)
        rvVolumes.layoutManager = LinearLayoutManager(this)
        rvVolumes.adapter = adapter

        edtQuery.onActionSearch { query ->
            adapter.search(
                query = query,
                onStart = this@MainActivity::showLoading,
                onCompleted = this@MainActivity::hideLoading,
                onError = {
                    hideLoading()
                }
            )
        }
    }

    private fun showLoading() {
        pbLoading.show()
        vCover.show()
    }

    private fun hideLoading() {
        pbLoading.hide()
        vCover.hide()
    }

}
