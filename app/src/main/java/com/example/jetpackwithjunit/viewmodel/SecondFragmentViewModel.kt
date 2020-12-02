package com.example.jetpackwithjunit.viewmodel

import androidx.lifecycle.*
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