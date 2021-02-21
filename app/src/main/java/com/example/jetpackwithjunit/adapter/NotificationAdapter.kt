package com.example.jetpackwithjunit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwithjunit.databinding.ItemNotificationBinding
import com.example.jetpackwithjunit.model.NotificationModel

class NotificationAdapter(val listener: NotificationClickLister) :
    RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {

     var notificationList : List<NotificationModel> = ArrayList<NotificationModel>()

    inner class NotificationHolder(val binding: ItemNotificationBinding)
        :RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
       val itemNotificationBinding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationHolder(itemNotificationBinding)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        with(holder){
            with(notificationList[position]){
                holder.binding.item = notificationList[position]
                holder.binding.lister = listener
            }
        }
    }

    override fun getItemCount(): Int {
       return notificationList.size
    }

    fun updateList(list :List<NotificationModel>){
        notificationList = list
        notifyDataSetChanged()

    }

 interface NotificationClickLister {
    fun onItemClick(item : NotificationModel)
 }
}