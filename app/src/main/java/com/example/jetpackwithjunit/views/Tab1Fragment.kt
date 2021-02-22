package com.example.jetpackwithjunit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackwithjunit.adapter.NotificationAdapter
import com.example.jetpackwithjunit.databinding.FragmentTab1Binding
import com.example.jetpackwithjunit.model.NotificationModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class Tab1Fragment : Fragment() , NotificationAdapter.NotificationClickLister {
    private lateinit var binding : FragmentTab1Binding

    var notificationAdapter:NotificationAdapter?=null

    companion object {
        fun newInstance(): Tab1Fragment {
            val args = Bundle()
            val fragment = Tab1Fragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
 // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //AndroidSupportInjection.inject(this)
        // Toast.makeText(requireContext() ,viewModel.getViewModelName() , Toast.LENGTH_LONG).show()
       initView()
    }

    private fun initView() {
        notificationAdapter = NotificationAdapter(this)
        binding.notiSecurityRecycleView.apply {
            adapter =notificationAdapter
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)
            setHasFixedSize(true)
        }

        notificationAdapter!!.updateList(getNotificationList())
    }


    fun getLayoutId(): Int {
    return R.layout.fragment_tab1
}

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onItemClick(item: NotificationModel) {
       Toast.makeText(requireContext(),item.title , Toast.LENGTH_LONG).show()
    }

    fun getNotificationList():List<NotificationModel>{
        val list: MutableList<NotificationModel> = mutableListOf()
        with(list){
            add(NotificationModel("見守り通知見守り","11ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。12","6/23 08:31","対象車両：ハスラー　品川502 み 80-12",true))
            add(NotificationModel("見守り通知見守り","22ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。22","6/23 08:32","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","11ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。12","6/23 08:31","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","22ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。22","6/23 08:32","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","11ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。12","6/23 08:31","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","22ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。22","6/23 08:32","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","11ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。12","6/23 08:31","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","22ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。22","6/23 08:32","対象車両：ハスラー　品川502 み 80-12",false))
            add(NotificationModel("見守り通知見守り","11ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。12","6/23 08:31","対象車両：ハスラー　品川502 み 80-12",true))
            add(NotificationModel("見守り通知見守り","22ドアが解錠中です。ロックし忘れの場合はダッシュボードからロックしてください。22","6/23 08:32","対象車両：ハスラー　品川502 み 80-12",false))


        }
        return list

    }
}