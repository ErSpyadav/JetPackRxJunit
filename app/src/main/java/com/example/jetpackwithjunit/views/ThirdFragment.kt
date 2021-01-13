package com.example.jetpackwithjunit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.jetpackwithjunit.utils.navigateObserver
import com.example.jetpackwithjunit.viewmodel.SecondFragmentViewModel
import com.example.jetpackwithjunit.viewmodel.ThirdFragmentViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_third.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {
    private lateinit var viewModel: ThirdFragmentViewModel
    private val args by navArgs<ThirdFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ThirdFragmentViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)

        val argVal = args.imageUrl
        textview_third.text =argVal

        with(viewModel.inputs) {
            button_third.setOnClickListener {
                viewModel.btnClicked()
            }
        }
        with(viewModel.outputs) {
            goToNextScreen.observe(
                viewLifecycleOwner,
                navigateObserver(R.id.action_thirdfragment_to_firstfragment)
            )
        }
    }
}