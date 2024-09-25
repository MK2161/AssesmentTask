package com.example.assesmenttask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.assesmenttask.databinding.ActivityMainBinding
import com.example.assesmenttask.ui.MainViewModel
import com.example.assesmenttask.ui.home.HomeFragment
import com.example.assesmenttask.ui.bank.BankFragment
import com.example.assesmenttask.ui.profile.ProfileFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by inject()
    private val homeFragment by lazy {
        HomeFragment()
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpUi()
        handleNavigationView()
    }

    private fun setUpUi() {
        viewModel.userDetails.observe(this) { id ->
           // onNavigationOtp(id)
        }
        viewModel.error.observe(this) { message ->
            showErrorMessage(message)
        }
    }
   /* supportFragmentManager.beginTransaction()
    .add(R.id.fragment_container, fragment)
    .commit()*/
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