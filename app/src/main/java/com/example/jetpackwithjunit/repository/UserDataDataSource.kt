package com.example.jetpackwithjunit.repository

import com.example.jetpackwithjunit.model.UserData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

interface UserDataDataSource {
    fun getUserData(): Flowable<UserData>

    fun setFullName(firstName: String, middleName: String, lastName: String)
    fun setFirstName(firstName: String)
    fun setLastName(lastName: String)
}

//@Singleton
//class LocalUserDataDataSource @Inject constructor() : UserDataDataSource {
//    private val userDataSubject: BehaviorSubject<UserData> =
//        BehaviorSubject.createDefault(UserData())
//    private val userData: UserData
//        get() = userDataSubject.value ?: UserData()
//
//    override fun getUserData(): Flowable<UserData> =
//        userDataSubject.toFlowable(BackpressureStrategy.LATEST)
//
//    override fun setFullName(firstName: String, middleName: String, lastName: String) {
//        userDataSubject.onNext(userData.copy(firstName = firstName, middleName = middleName, lastName = lastName))
//    }
//
//    override fun setFirstName(firstName: String) {
//        userDataSubject.onNext(userData.copy(firstName = firstName))
//    }
//
//    override fun setLastName(lastName: String) {
//        userDataSubject.onNext(userData.copy(lastName = lastName))
//    }
//}
