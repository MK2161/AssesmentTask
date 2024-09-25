package com.example.assesmenttask.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assesmenttask.ui.home.Accounts
import com.example.assesmenttask.ui.home.Cards

class ViewPagerAdapter(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

         return when(position){
            0 -> {
                Accounts()
            }

            1 -> {
                Cards()
            }

            else -> {
                Accounts()
            }
        }
    }

}