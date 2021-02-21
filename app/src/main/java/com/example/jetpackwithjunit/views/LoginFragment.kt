package com.example.jetpackwithjunit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.jetpackwithjunit.di.Injectable
import com.example.jetpackwithjunit.model.Repos
import com.example.jetpackwithjunit.retrofit.RetrofitClient
import com.example.jetpackwithjunit.utils.LiveDataResult
import com.example.jetpackwithjunit.utils.MainViewModelFactory
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.LoginFragmentViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() , Injectable {

    @Inject
    lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  // Create ViewModel and bind observer
//        val factory = MainViewModelFactory(RetrofitClient.retrofitClient)
//        this.viewModel =
//            ViewModelProviders.of(this, factory).get(LoginFragmentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        lifecycle.addObserver(viewModel)

        this.viewModel.repositoriesLiveData.observe(viewLifecycleOwner, this.dataObserver)
        this.viewModel.loadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)


        with(viewModel.inputs) {
            button_first.setOnClickListener {
                viewModel.btnClicked()
            }
        }
        with(viewModel.outputs) {
            goToNextScreen.observe(
                viewLifecycleOwner,
                navigateObserver(R.id.action_loginFragment_to_mpinFragment)
            )
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
                detail.append("Name:" + result!!.data?.get(0)!!.full_name)
                detail.append("Descriptor:" + result!!.data?.get(0)!!.descriptor)
                textview_first.text = detail

                // Data from API
            }
        }
    }

    private val loadingObserver = Observer<Boolean> { visibile ->
        // Show hide a progress
    }

    companion object {
        fun instance() = LoginFragment()
    }
}