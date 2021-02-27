package com.example.jetpackwithjunit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.*
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.MPinFragmentViewModel
import com.example.jetpackwithjunit.workmanager.NotificationWorkRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second.*
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class MPinFragment : Fragment() {
   // private lateinit var viewModel: MPinFragmentViewModel by vie
    private val loginViewModel: MPinFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      //  viewModel = ViewModelProvider(this).get(MPinFragmentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // lifecycle.addObserver(viewModel)

        with(loginViewModel.inputs) {
            button_second.setOnClickListener {
                loginViewModel.btnClicked()
            }
        }
        with(loginViewModel.outputs) {
            goToNextScreen.observe(
                viewLifecycleOwner,
                navigateObserver(MPinFragmentDirections.actionMpinFragmentToDashboardfragmentNext())
            )
        }
        button_singleTask.setOnClickListener {
            ScheduleTask()
        //    val number = NumberFormat.getNumberInstance(Locale.US).format("9999999".toInt())
         //   Toast.makeText(requireContext() ,"Number : $number" , Toast.LENGTH_LONG).show()//9,999,999
           //loginViewModel.getCricketAndFootBal()//zip Operator
            loginViewModel.getFootBalFanMap()//Map Operarotor
        }
        button_periodicTask.setOnClickListener {
           // PeriodicTask()
           // loginViewModel.getFootbalFanDetailFlatMap()//flatMap
            loginViewModel.checkFlowableObservable()
        }
        fetch_btn.setOnClickListener {
           loginViewModel.fetchUser()
        }


        loginViewModel.user.observe(viewLifecycleOwner ,{
            val result = StringBuilder()
            result.append("\nid : ${it.id}")
            result.append("\nname : ${it.name}")
            result.append("\nemail : ${it.email}")
            textview_second.text = result
        })
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