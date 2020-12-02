package com.example.jetpackwithjunit.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackwithjunit.utils.toLiveData
import com.example.jetpackwithjunit.utils.trigger
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject

interface SecondFragmentContract {
    interface Inputs {
        fun btnClicked()
    }

    interface Outputs {
        val goToNextScreen: LiveData<Unit>
    }
}

internal class SecondFragmentViewModel : ViewModel(),SecondFragmentContract.Inputs,SecondFragmentContract.Outputs,LifecycleObserver {

    val inputs : SecondFragmentContract.Inputs = this
    val outputs : SecondFragmentContract.Outputs = this
   val btnClickedSubject = PublishSubject.create<Unit>()

    override fun btnClicked() {
        btnClickedSubject.trigger()
    }

    override val goToNextScreen: LiveData<Unit>
        get() = btnClickedSubject.toLiveData()

}