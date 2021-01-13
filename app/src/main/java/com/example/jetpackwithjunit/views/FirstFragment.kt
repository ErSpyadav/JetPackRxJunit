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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.FirstFragmentViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
   // viewModelProvider: ViewModelProvider.Factory
    //private val args by navArgs<CheckEmailFragmentArgs>()
  private lateinit var viewModel: FirstFragmentViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         lifecycle.addObserver(viewModel)

        with(viewModel.inputs){
            button_first.setOnClickListener{
                viewModel.btnClicked()
            }
        }
        with(viewModel.outputs){
            goToNextScreen.observe(viewLifecycleOwner, navigateObserver(R.id.action_FirstFragment_to_SecondFragment))
        }
    }
}