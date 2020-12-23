package com.example.jetpackwithjunit.repository

import com.example.jetpackwithjunit.model.UserData
import io.reactivex.Flowable
import javax.inject.Inject

interface UserDataRepository {
    fun getUserData(): Flowable<UserData>

    fun setFullName(firstName: String, middleName: String, lastName: String)
    fun setFirstName(firstName: String)
    fun setLastName(lastName: String)
}

//class UserDataRepositoryImpl @Inject constructor(private val userDataDataSource: UserDataDataSource) : UserDataRepository {
//    override fun getUserData(): Flowable<UserData> = userDataDataSource.getUserData()
//
//    override fun setFirstName(firstName: String) {
//        userDataDataSource.setFirstName(firstName)
//    }
//
//    override fun setLastName(lastName: String) {
//        userDataDataSource.setLastName(lastName)
//    }
//
//    override fun setFullName(firstName: String, middleName: String, lastName: String) {
//        userDataDataSource.setFullName(firstName, middleName, lastName)
//    }
//
//}
