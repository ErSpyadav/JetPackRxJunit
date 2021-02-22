package com.example.jetpackwithjunit.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.jetpackwithjunit.model.User
import com.example.jetpackwithjunit.navigator.MpinNavigator
import com.example.jetpackwithjunit.repository.MPinRepository
import com.example.jetpackwithjunit.utils.BaseViewModel
import com.example.jetpackwithjunit.utils.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface SecondFragmentContract {
    interface Inputs {
        fun btnClicked()
    }

    interface Outputs {
        val goToNextScreen: LiveData<Unit>
    }
}

class MPinFragmentViewModel @ViewModelInject constructor(val mPinRepository: MPinRepository): BaseViewModel<MpinNavigator>(),SecondFragmentContract.Inputs,SecondFragmentContract.Outputs,LifecycleObserver {

    val inputs : SecondFragmentContract.Inputs = this
    val outputs : SecondFragmentContract.Outputs = this
   val btnClickedSubject = PublishSubject.create<Unit>()
    var disposable : CompositeDisposable = CompositeDisposable()
    var user = MutableLiveData<User>()

    companion object{

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start(){
        print("On Create method")
    }

    override fun btnClicked() {

      //  btnClickedSubject.trigger()
    }

    override val goToNextScreen: LiveData<Unit>
        get() = btnClickedSubject.toLiveData()

    fun fetchUser(){
        GlobalScope.launch {
            disposable.add(
                mPinRepository.getUserById(1).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<User>() {
                        override fun onSuccess(value: User) {
                            user.postValue(value)
                            println("Response :\n id : ${value.name}")
                        }

                        override fun onError(e: Throwable) {

                        }
                    })
            )
        }
    }
}