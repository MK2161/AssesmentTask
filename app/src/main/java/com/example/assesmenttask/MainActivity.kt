package com.example.assesmenttask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.assesmenttask.databinding.ActivityMainBinding
import com.example.assesmenttask.ui.bank.BankFragment
import com.example.assesmenttask.ui.home.HomeFragment
import com.example.assesmenttask.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private val homeFragment by lazy {
        HomeFragment.newInstance()
    }

    private val bankFragment by lazy {
        BankFragment()
    }
    private val profileFragment by lazy {
        ProfileFragment()
    }
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        changeFragment(homeFragment, "homeFragment")
        binding?.navView?.menu?.findItem(R.id.navigation_history)?.isEnabled = false
        handleNavigationView()
    }


    private fun handleNavigationView(){
        binding?.navView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragment(
                        homeFragment,
                        "homeFragment"
                    )
                }
                R.id.navigation_bank -> {
                    changeFragment(
                        bankFragment,
                        "bankFragment"
                    )
                }
                R.id.navigation_profile -> {
                    changeFragment(
                        profileFragment,
                        "profileFragment"
                    )
                }

            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment?, tagFragmentName: String?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment? = supportFragmentManager.primaryNavigationFragment
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
        }
        var fragmentTemp: Fragment? = supportFragmentManager.findFragmentByTag(tagFragmentName)
        if (fragmentTemp == null) {
            fragmentTemp = fragment
            fragmentTemp?.let {
                fragmentTransaction.add(R.id.uiFragmentContainer, fragmentTemp, tagFragmentName)
            }
        } else {
            fragmentTransaction.show(fragmentTemp)
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitNowAllowingStateLoss()
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}