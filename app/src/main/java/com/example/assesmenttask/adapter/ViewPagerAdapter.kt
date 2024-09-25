package com.example.assesmenttask.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assesmenttask.ui.bank.AccountsFragment
import com.example.assesmenttask.ui.bank.Cards

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

         return when(position){
            0 -> {
                AccountsFragment()
            }

            1 -> {
                Cards()
            }

            else -> {
                Cards()
            }
        }
    }

}