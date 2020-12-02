package com.example.jetpackwithjunit.viewmodel

import androidx.lifecycle.*
import com.example.jetpackwithjunit.utils.toLiveData
import com.example.jetpackwithjunit.utils.trigger
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject

interface FirstFragmentContract {
    interface Inputs {
        fun btnClicked()
    }

    interface Outputs {
        val goToNextScreen: LiveData<Unit>
    }
}

internal class FirstFragmentViewModel : ViewModel(),FirstFragmentContract.Inputs,FirstFragmentContract.Outputs,LifecycleObserver {

    val inputs : FirstFragmentContract.Inputs = this
    val outputs : FirstFragmentContract.Outputs = this
   val btnClickedSubject = PublishSubject.create<Unit>()

   @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start(){
        print("On Create method")
    }

    override fun btnClicked() {
        btnClickedSubject.trigger()
    }

    override val goToNextScreen: LiveData<Unit>
        get() = btnClickedSubject.toLiveData()

}