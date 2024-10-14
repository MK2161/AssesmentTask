package com.example.assesmenttask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assesmenttask.adapter.HistoryListAdapter
import com.example.assesmenttask.databinding.HistoryScreenBinding
import com.example.assesmenttask.handler.showFaceBottomSheetDialog
import com.example.assesmenttask.ui.home.HomeViewModel
import org.koin.android.ext.android.inject

class HistoryActivity : AppCompatActivity() {
    private var binding : HistoryScreenBinding? = null
    private val viewModel: HomeViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpUi()
        binding?.uiIvFilter?.setOnClickListener {
            showFaceBottomSheetDialog(
                onClickAction = {},
                context = this
            )
        }
        binding?.uiIvBackArrow?.setOnClickListener {
            finish()
        }

    }
    private fun setUpUi(){
        viewModel.userDetails.observe(this) { id ->
            binding?.uiRvHistoryDetails?.adapter = HistoryListAdapter(
                context = this,
                userDetails = id
            )
        }
        viewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

}


