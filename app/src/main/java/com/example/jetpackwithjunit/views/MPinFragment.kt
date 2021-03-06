package com.example.jetpackwithjunit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.MPinFragmentViewModel
import com.example.jetpackwithjunit.workmanager.NotificationWorkRequest
import kotlinx.android.synthetic.main.fragment_second.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MPinFragment : Fragment() {
    private lateinit var viewModel: MPinFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MPinFragmentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)

        with(viewModel.inputs) {
            button_second.setOnClickListener {
                viewModel.btnClicked()
            }
        }
        with(viewModel.outputs) {
            goToNextScreen.observe(
                viewLifecycleOwner,
                navigateObserver(MPinFragmentDirections.actionMpinFragmentToDashboardfragmentNext())
            )
        }
        button_singleTask.setOnClickListener {
            ScheduleTask()
        }
        button_periodicTask.setOnClickListener {
            PeriodicTask()
        }
    }

    fun ScheduleTask(){
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorkRequest>()
            .setInitialDelay(20,TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance().enqueue(workRequest)
    }

    fun PeriodicTask(){
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .build()


        val workRequest = PeriodicWorkRequestBuilder<NotificationWorkRequest>(1,TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance().enqueue(workRequest)

    }
}