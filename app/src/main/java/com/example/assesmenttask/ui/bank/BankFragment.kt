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
    private val binding by lazy {
        FragmentCardBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.uiViewPage.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(binding.uiTabs, binding.uiViewPage) { tab, position ->
            tab.text = tobBarItems[position].title
        }.attach()
    }
}