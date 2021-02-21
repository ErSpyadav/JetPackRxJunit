package com.example.jetpackwithjunit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Tab3Fragment : Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3, container, false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        AndroidSupportInjection.inject(this)
//        Toast.makeText(requireContext() ,viewModel.getViewModelName() , Toast.LENGTH_LONG).show()


    }

    companion object {
        fun newInstance(): Tab3Fragment {
            val args = Bundle()
            val fragment = Tab3Fragment()
            fragment.arguments = args
            return fragment
        }
    }
}