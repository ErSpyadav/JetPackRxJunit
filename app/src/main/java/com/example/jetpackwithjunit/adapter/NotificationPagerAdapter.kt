package com.example.jetpackwithjunit.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackwithjunit.Tab1Fragment
import com.example.jetpackwithjunit.Tab2Fragment
import com.example.jetpackwithjunit.Tab3Fragment

class NotificationPagerAdapter(val context: Fragment) : FragmentStateAdapter(context) {
    companion object {
        internal const val NOTIFICATION_SECURITY_POSITION = 0
        internal const val NUMBER_OF_SCREEEN =3
    }

    override fun getItemCount(): Int {
        return NUMBER_OF_SCREEEN
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> Tab1Fragment.newInstance()
        1 -> {
            Tab2Fragment.newInstance()
        }
        2 -> Tab3Fragment.newInstance()

        else -> throw IllegalStateException("Invalid adapter position")
    }
}