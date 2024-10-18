package com.example.assesmenttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assesmenttask.R
import com.example.assesmenttask.adapter.AccountAdapter
import com.example.assesmenttask.adapter.BankAdapter
import com.example.assesmenttask.adapter.SearchBankAdapter
import com.example.assesmenttask.data.model.AccountDetails
import com.example.assesmenttask.data.model.BankDetails
import com.example.assesmenttask.databinding.SelectBankAccountBinding
import com.example.assesmenttask.databinding.TransferDetailsScreenBinding

class SelectYourBankActivity:AppCompatActivity() {

    private var binding : SelectBankAccountBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectBankAccountBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        // setUpUi()
        setUpBankDetailAdapter()

    }

    private fun setUpBankDetailAdapter() {
        val bankDetails = arrayListOf(
            BankDetails(
                icon = R.drawable.ic_sbi,
                bankName = "State Bank of India",
            ),
            BankDetails(
                icon = R.drawable.ic_bank_of_baroda,
                bankName = "Bank of Baroda",
            ),
            BankDetails(
                icon = R.drawable.ic_sbi,
                bankName = "State Bank of India",
            ),
            BankDetails(
                icon = R.drawable.ic_bank_of_baroda,
                bankName = "Bank of Baroda",
            ),
            BankDetails(
                icon = R.drawable.ic_sbi,
                bankName = "State Bank of India",
            ),
            BankDetails(
                icon = R.drawable.ic_bank_of_baroda,
                bankName = "Bank of Baroda",
            ),
            BankDetails(
                icon = R.drawable.ic_sbi,
                bankName = "State Bank of India",
            ),
            BankDetails(
                icon = R.drawable.ic_bank_of_baroda,
                bankName = "Bank of Baroda",
            ),
            BankDetails(
                icon = R.drawable.ic_sbi,
                bankName = "State Bank of India",
            ),
        )
        val recyclerView = binding?.uiRvBank
        val gridLayoutManager = GridLayoutManager(this, 3)


        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.adapter = BankAdapter(
            context = this,
            bankDetails = bankDetails
        )

        val searchRecyclerView = binding?.uiRvBankDetailsName
        searchRecyclerView?.adapter =   SearchBankAdapter(
            context = this,
            bankDetails = bankDetails
        )
    }


}
