package com.example.jetpackwithjunit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.jetpackwithjunit.adapter.NotificationPagerAdapter
import com.example.jetpackwithjunit.databinding.FragmentThirdBinding
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.DashboardFragmentViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var viewModel: DashboardFragmentViewModel
    private val args by navArgs<DashboardFragmentArgs>()
    private lateinit var titles: Array<String>
    private var notificationPagerAdapter: NotificationPagerAdapter? = null
    private lateinit var mNotificationFragmentBinding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mNotificationFragmentBinding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(DashboardFragmentViewModel::class.java)
        titles =
                arrayOf("Tab1","Tab2","Tab3")
        setupViewPager()
        // Inflate the layout for this fragment
        return mNotificationFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)

        val argVal = args.imageUrl
     //   textview_third.text =argVal

        with(viewModel.inputs) {
//            button_third.setOnClickListener {
//                viewModel.btnClicked()
//            }
        }
        with(viewModel.outputs) {
            goToNextScreen.observe(
                viewLifecycleOwner,
                navigateObserver(R.id.action_dashboardfragment_to_loginfragment)
            )
        }
    }
   fun getLayoutId(): Int {
        return R.layout.fragment_third
    }
    private fun setupViewPager() {
        notificationPagerAdapter = null
        notificationPagerAdapter =
            NotificationPagerAdapter(this)
        //enable/disable swipe of viewpager
        mNotificationFragmentBinding.notificationPager.isUserInputEnabled = false

        mNotificationFragmentBinding.notificationPager.apply {
            adapter = null
            adapter = notificationPagerAdapter
            currentItem = NotificationPagerAdapter.NOTIFICATION_SECURITY_POSITION
            offscreenPageLimit = 3
        }

        TabLayoutMediator(mNotificationFragmentBinding.notificationTab,
            mNotificationFragmentBinding.notificationPager,
            true,
            true
        ) { currentTab, currentPosition ->
            currentTab.text = titles[currentPosition]
        }.attach()

        createTabIcons(mNotificationFragmentBinding.notificationTab)
        mNotificationFragmentBinding.notificationTab.addOnTabSelectedListener(
            OnTabSelectedListener
        )
    }

    private val OnTabSelectedListener: TabLayout.OnTabSelectedListener = object :
        TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            selectedTabView(tab)

        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            unSelectedTabView(tab)
        }

        override fun onTabReselected(tab: TabLayout.Tab) {}
    }

    fun selectedTabView(tab: TabLayout.Tab) {
        val selected = tab.customView
        val iv_text = selected!!.findViewById<View>(R.id.tabName_Tv) as TextView
        iv_text.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.pin_color_focuses
            )
        )
    }

    fun unSelectedTabView(tab: TabLayout.Tab) {
        val selected = tab.customView
        val iv_text = selected!!.findViewById<View>(R.id.tabName_Tv) as TextView
        iv_text.setTextColor(ContextCompat.getColor(requireContext(), R.color.mediumTextColor))
    }

    /*showing 3 Tab if user is primary. ogtherwise showing 2 tab only.*/
    private fun createTabIcons(tabLayout: TabLayout) {
        for (index in titles.indices) {
            val customView = LayoutInflater.from(requireActivity())
                .inflate(R.layout.notification_custom_tab, null) as ConstraintLayout
            customView.findViewById<TextView>(R.id.tabName_Tv).text = titles[index]
            tabLayout.getTabAt(index)!!.customView = customView
        }

    }



}