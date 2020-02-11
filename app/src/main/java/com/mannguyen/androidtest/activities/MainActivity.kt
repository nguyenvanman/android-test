package com.mannguyen.androidtest.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.adapters.VolumesAdapter
import com.mannguyen.androidtest.services.datasources.VolumeDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: VolumesAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        adapter = VolumesAdapter(this)
        rvVolumes.layoutManager = LinearLayoutManager(this)
        rvVolumes.adapter = adapter
    }
}
