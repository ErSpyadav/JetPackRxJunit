package com.example.jetpackwithjunit.repository

import com.example.jetpackwithjunit.model.Address
import com.example.jetpackwithjunit.model.Fans
import com.example.jetpackwithjunit.model.User
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NetworkService {

    fun getCricketFansObservable(): Observable<List<Fans>> {
        return Observable.create<List<Fans>> { emitter ->
            if (!emitter.isDisposed) {
                // fetch data from network
                val data = fetchCricketFans()
                emitter.onNext(data)
                emitter.onComplete()
            }
        }.subscribeOn(Schedulers.io())
    }

    fun getFootBalFan():Observable<List<Fans>>{
        return Observable.create<List<Fans>>{emitter ->
            if(!emitter.isDisposed){
                val data = fetchFootbalFans()
                emitter.onNext(data)
                emitter.onComplete()
            }

        }.subscribeOn(Schedulers.io())

    }

    fun getFootBalFanDetail():Observable<Fans>{
        return Observable.create<Fans>{emitter ->
            if(!emitter.isDisposed){
                val data = fetchFootbalFansName()
                emitter.onNext(data)
                emitter.onComplete()
            }

        }.subscribeOn(Schedulers.io())

    }

    private fun fetchCricketFans(): List<Fans> {
       val list : MutableList<Fans> = ArrayList()
        list.apply {
            add(Fans("Ashok",24))
            add(Fans("Pradeep",34))
            add(Fans("Ashu",25))
            add(Fans("Sarayu",24))
            add(Fans("Riya",21))


        }
        return list
    }
    private fun fetchFootbalFans(): List<Fans> {
        val list : MutableList<Fans> = ArrayList()
        list.apply {
            add(Fans("Ashok",24))
            add(Fans("Pradeep",34))
            add(Fans("Radha",25))
            add(Fans("Sarayu",24))
            add(Fans("Manju",21))


        }
        return list
    }

    private fun fetchFootbalFansName(): Fans {
        return Fans("Ashok",23)

    }
    public fun filterUserWhoLovesBoth(cricketFans: List<Fans>,
                                       footballFans: List<Fans>): List<Fans> {
        val userWhoLovesBoth = ArrayList<Fans>()
        for (footballFan in footballFans) {
            if (cricketFans.contains(footballFan)) {
                userWhoLovesBoth.add(footballFan)
            }
        }
        return userWhoLovesBoth
    }
}