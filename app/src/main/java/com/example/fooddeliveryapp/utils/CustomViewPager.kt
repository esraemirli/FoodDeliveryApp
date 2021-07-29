package com.example.fooddeliveryapp.utils

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fooddeliveryapp.ui.login.LoginFragment
import com.example.fooddeliveryapp.ui.login.SignupFragment

class CustomViewPager(manager :FragmentManager): FragmentStatePagerAdapter(manager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragmentList: ArrayList<Fragment> = ArrayList()
    var titleList: ArrayList<String> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    fun addFragment(fragment: Fragment,title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }


}