package com.example.jetpackwithjunit.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.jetpackwithjunit.model.Fans
import com.example.jetpackwithjunit.model.User
import com.example.jetpackwithjunit.navigator.MpinNavigator
import com.example.jetpackwithjunit.repository.MPinRepository
import com.example.jetpackwithjunit.repository.NetworkService
import com.example.jetpackwithjunit.utils.BaseViewModel
import com.example.jetpackwithjunit.utils.toLiveData
import com.example.jetpackwithjunit.utils.trigger
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.subscribeBy
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
        btnClickedSubject.trigger()
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
//For test Data
    fun getCricketAndFootBal(){
    val networkUser = NetworkService()
    Observable.zip(
        networkUser.getCricketFansObservable(),
        networkUser.getFootBalFan(),
        BiFunction<List<Fans>, List<Fans>, List<Fans>> { cricketFans, footballFans ->
            return@BiFunction networkUser.filterUserWhoLovesBoth(cricketFans, footballFans)
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getObserver())
    }

    fun getFootBalFanMap(){
        val networkUser = NetworkService()
        networkUser.getFootBalFanDetail()
            .map(Function<Fans, Fans> { apiUser -> // here we get the ApiUser from the server
                // then by converting it into the user, we are returning
               val fans = apiUser
                return@Function fans
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFansDetailObserver())
    }

    fun getFootbalFanDetailFlatMap(){
        val networkUser = NetworkService()
        networkUser.getFootBalFanDetail()
            .flatMap(Function<Any?, Observable<List<Fans>>?> { apiUser ->
                networkUser.getCricketFansObservable()
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())
    }



    private fun getObserver():Observer<List<Fans>>{
        return object : Observer<List<Fans>> {
            override fun onSubscribe(d: Disposable) {
                print("onSubscribe")
            }

            override fun onNext(t: List<Fans>) {
               println(t)
                t.forEach {
                    println("${it.name}  age:${it.Age}")
                }
            }

            override fun onError(e: Throwable) {
                print("Error")
            }

            override fun onComplete() {
               print("completed")
            }

        }
    }
    private fun getFansDetailObserver():Observer<Fans>{
        return object : Observer<Fans> {
            override fun onSubscribe(d: Disposable) {
                print("onSubscribe")
            }

            override fun onNext(t: Fans) {
                println(t)
                println("${t.name}  age:${t.Age}")
            }

            override fun onError(e: Throwable) {
                print("Error")
            }

            override fun onComplete() {
                print("completed")
            }

        }
    }

    fun checkFlowableObservable(){
        val observable = PublishSubject.create<Int>()
        observable
            .toFlowable(BackpressureStrategy.MISSING).buffer(300)
            .observeOn(Schedulers.computation())
            .subscribeBy (
                onNext ={
                    println("number: ${it}")
                },onError = {t->
                    print(t.message)
                }
            )
        for (i in 0..1000000){
            observable.onNext(i)
        }
    }
}