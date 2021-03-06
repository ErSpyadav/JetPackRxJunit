package com.example.jetpackwithjunit.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.jetpackwithjunit.navigator.DashboardNavigator
import com.example.jetpackwithjunit.utils.BaseViewModel
import com.example.jetpackwithjunit.utils.toLiveData
import io.reactivex.subjects.PublishSubject

interface ThirdFragmentContract{
    interface Input{
        fun btnClicked()
    }
    interface Output{
        val goToNextScreen : LiveData<Unit>
    }
}

class DashboardFragmentViewModel : BaseViewModel<DashboardNavigator>(),ThirdFragmentContract.Input,ThirdFragmentContract.Output,LifecycleObserver {
    private  val TAG = "ThirdFragmentViewModel"
    val inputs : ThirdFragmentContract.Input = this
    val outputs : ThirdFragmentContract.Output = this
    val btnClickSubject = PublishSubject.create<Unit>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.d(TAG, "onCreate: ")
    }

    override fun btnClicked() {
        btnClickSubject.onNext(Unit)
    }

    override val goToNextScreen: LiveData<Unit>
        get() = btnClickSubject.toLiveData()

    fun getViewModelName():String{
        return "DashBoard ViewModel"
    }

}