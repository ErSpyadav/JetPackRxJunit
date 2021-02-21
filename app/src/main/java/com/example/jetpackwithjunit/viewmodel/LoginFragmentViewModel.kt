package com.example.jetpackwithjunit.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.jetpackwithjunit.model.Repos
import com.example.jetpackwithjunit.navigator.LoginNavigator
import com.example.jetpackwithjunit.retrofit.ApiService
import com.example.jetpackwithjunit.utils.BaseViewModel
import com.example.jetpackwithjunit.utils.LiveDataResult
import com.example.jetpackwithjunit.utils.toLiveData
import com.example.jetpackwithjunit.utils.trigger
import io.reactivex.BackpressureStrategy
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface FirstFragmentContract {
    interface Inputs {
        fun btnClicked()
    }

    interface Outputs {
        val goToNextScreen: LiveData<Unit>
    }
}

 class LoginFragmentViewModel  @Inject constructor(): BaseViewModel<LoginNavigator>(),FirstFragmentContract.Inputs,FirstFragmentContract.Outputs,LifecycleObserver {

    val inputs : FirstFragmentContract.Inputs = this
    val outputs : FirstFragmentContract.Outputs = this
   val btnClickedSubject = PublishSubject.create<Unit>()

     @Inject
     lateinit var apiService: ApiService

    /*restful*/
    val repositoriesLiveData = MutableLiveData<LiveDataResult<List<Repos>>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            fetchUserRepositories("ErSpyadav")
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start(){
        print("On Create method")
    }

    override fun btnClicked() {
        btnClickedSubject.trigger()
    }

    override val goToNextScreen: LiveData<Unit>
        get() = btnClickedSubject.toLiveData()

    /**
     * Request user's repositories
     * @param githubUser Github usename
     */
    fun fetchUserRepositories(githubUser: String) {
        this.setLoadingVisibility(true)
     //   this.userService.getRepositories(githubUser).subscribe(GetRepositoriesConsumer())
    }

    /**
     * Set a progress dialog visible on the View
     * @param visible visible or not visible
     */
    fun setLoadingVisibility(visible: Boolean) {
        loadingLiveData.postValue(visible)
    }

    /**
     * userService.fetchUserRepositories() Observer
     */
    inner class GetRepositoriesConsumer : MaybeObserver<List<Repos>> {
        override fun onSubscribe(d: Disposable) {
            this@LoginFragmentViewModel.repositoriesLiveData.postValue(LiveDataResult.loading())
        }

        override fun onError(e: Throwable) {
            this@LoginFragmentViewModel.repositoriesLiveData.postValue(LiveDataResult.error(e))
            this@LoginFragmentViewModel.setLoadingVisibility(false)
            print("onError :\n"+e.localizedMessage)
        }

        override fun onSuccess(t: List<Repos>) {
            this@LoginFragmentViewModel.repositoriesLiveData.postValue(LiveDataResult.succes(t))
            this@LoginFragmentViewModel.setLoadingVisibility(false)
            print("onSuccess :\n"+t.get(0).toString())
        }

        override fun onComplete() {
            this@LoginFragmentViewModel.setLoadingVisibility(false)
        }

    }

}