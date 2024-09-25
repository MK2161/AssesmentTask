package com.example.assesmenttask.ui.bank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assesmenttask.adapter.ViewPagerAdapter
import com.example.assesmenttask.data.model.tobBarItems
import com.example.assesmenttask.databinding.FragmentCardBinding
import com.example.assesmenttask.ui.profile.ProfileFragment
import com.google.android.material.tabs.TabLayoutMediator

class BankFragment : Fragment() {
    private var binding: FragmentCardBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binding?.uiViewPage?.adapter = ViewPagerAdapter(this)
            TabLayoutMediator(binding?.uiTabs!!, binding?.uiViewPage!!) { tab, position ->
                tab.text = tobBarItems[position].title
            }.attach()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentCardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    companion object {
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}