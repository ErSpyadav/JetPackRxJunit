package com.example.jetpackwithjunit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import com.example.jetpackwithjunit.model.Repos
import com.example.jetpackwithjunit.retrofit.RetrofitClient
import com.example.jetpackwithjunit.utils.LiveDataResult
import com.example.jetpackwithjunit.utils.MainViewModelFactory
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.FirstFragmentViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
   // viewModelProvider: ViewModelProvider.Factory
    //private val args by navArgs<CheckEmailFragmentArgs>()
  private lateinit var viewModel: FirstFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        //  // Create ViewModel and bind observer
        val factory = MainViewModelFactory(RetrofitClient.retrofitClient)
        this.viewModel = ViewModelProviders.of(this, factory).get(FirstFragmentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         lifecycle.addObserver(viewModel)
        // ViewModel factory
//        GlobalScope.launch(Dispatchers.IO) {
//            viewModel.fetchUserRepositories("ErSpyadav")
//        }

        this.viewModel.repositoriesLiveData.observe(viewLifecycleOwner, this.dataObserver)
        this.viewModel.loadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)


        with(viewModel.inputs){
            button_first.setOnClickListener{
                viewModel.btnClicked()
            }
        }
        with(viewModel.outputs){
            goToNextScreen.observe(viewLifecycleOwner, navigateObserver(R.id.action_FirstFragment_to_SecondFragment))
        }
    }

    private val dataObserver = Observer<LiveDataResult<List<Repos>>> { result ->
        when (result?.status) {
            LiveDataResult.Status.LOADING -> {
                // Loading data
            }

            LiveDataResult.Status.ERROR -> {
                // Error for http request
            }

            LiveDataResult.Status.SUCCESS -> {
                val detail = StringBuilder()
                detail.append("Name:"+ result!!.data?.get(0)!!.full_name)
                detail.append("Descriptor:"+ result!!.data?.get(0)!!.descriptor)
                textview_first.text =detail

                // Data from API
            }
        }
    }

    private val loadingObserver = Observer<Boolean> { visibile ->
        // Show hide a progress
    }

    companion object{
        fun instance() = FirstFragment()
    }
}