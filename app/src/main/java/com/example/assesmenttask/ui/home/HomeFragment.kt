package com.example.assesmenttask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.assesmenttask.R
import com.example.assesmenttask.adapter.AccountAdapter
import com.example.assesmenttask.adapter.UserDetailsAdapter
import com.example.assesmenttask.data.model.AccountDetails
import com.example.assesmenttask.databinding.FragmentHomeViewBinding
import com.google.android.material.button.MaterialButton
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()
    private var binding: FragmentHomeViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBankDetailAdapter()
        setUpUi()
    }

    private fun setUpUi(){
        viewModel.userDetails.observe(this) { id ->
            binding?.childRecyclerView?.adapter = UserDetailsAdapter(
                context = requireContext(),
                userDetails = id
            )
        }
        viewModel.error.observe(this) { message ->
            Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT)
        }
    }

    private fun setUpBankDetailAdapter() {
        val accounts = arrayListOf(
            AccountDetails(
                index = 0,
                icon = R.drawable.ic_qpay_wallet,
                bankCode = "QPay Balance",
                accountBalance = "₹2,36,000.47",
            ),
            AccountDetails(
                index = 1,
                icon = R.drawable.ic_sbi,
                bankCode = "SBI",
                bankName = "State Bank of India",
                accountBalance = "₹2,36,000.47",
            ),
            AccountDetails(
                index = 2,
                icon = R.drawable.ic_bank_of_baroda,
                bankCode = "BOB",
                bankName = "Bank of Baroda",
                accountBalance = "₹2,13,000.47",
            ),
            AccountDetails(
                index = 3,
                icon = R.drawable.ic_bank,
                bankCode = "Bank Name",
                accountBalance = "₹0.00",
            ),
        )
        val recyclerView = binding?.uiRvBank
        recyclerView?.adapter = AccountAdapter(
            context = requireContext(),
            accounts = accounts,
            onAccountClicked = {
                onAccountClicked(accounts, it)
            }
        )
    }

    private fun onAccountClicked(
        accountsList: ArrayList<AccountDetails>,
        acc: AccountDetails
    ) {
        when(acc.index) {
            accountsList.first().index -> {
                binding?.uiGrpNewAccount?.visibility = View.GONE
                binding?.uiGrpBankDetails?.visibility = View.GONE
                binding?.uiGrpWallet?.visibility = View.VISIBLE
                binding?.uiBtnStatement?.visibility = View.VISIBLE
                binding?.uiBtnStatement?.text = "Add Money"
                binding?.uiBtnStatement?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_money)
                binding?.uiBtnStatement?.iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
                binding?.uiBtnTransfer?.visibility = View.VISIBLE
            }
            accountsList.last().index -> {
                binding?.uiGrpNewAccount?.visibility = View.VISIBLE
                binding?.uiGrpBankDetails?.visibility = View.GONE
                binding?.uiGrpWallet?.visibility = View.GONE
                binding?.uiBtnStatement?.visibility = View.GONE
                binding?.uiBtnTransfer?.visibility = View.GONE
            }
            else -> {
                binding?.uiGrpNewAccount?.visibility = View.GONE
                binding?.uiGrpBankDetails?.visibility = View.VISIBLE
                binding?.uiGrpWallet?.visibility = View.GONE
                binding?.uiTvBankText?.text = acc.bankName
                binding?.uiBtnStatement?.visibility = View.VISIBLE
                binding?.uiBtnStatement?.text = "Statement"
                binding?.uiBtnTransfer?.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}