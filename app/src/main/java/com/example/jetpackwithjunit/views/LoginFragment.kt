package com.example.jetpackwithjunit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jetpackwithjunit.model.Repos
import com.example.jetpackwithjunit.utils.LiveDataResult
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class LoginFragment : Fragment()  {

    val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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