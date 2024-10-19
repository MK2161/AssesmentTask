package com.example.assesmenttask.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.assesmenttask.databinding.DownloadPendingScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DownloadPendingScreen : AppCompatActivity() {


    private var binding : DownloadPendingScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DownloadPendingScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(this@DownloadPendingScreen, DownloadSuccessScreen:: class.java)
            startActivity(intent)
        }
    }
}