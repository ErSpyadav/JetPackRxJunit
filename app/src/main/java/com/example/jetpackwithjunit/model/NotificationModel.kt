package com.example.jetpackwithjunit.model

import android.icu.text.CaseMap
import java.io.Serializable

data class NotificationModel(
    var title: String,
    var desc: String,
    var time: String,
    var vehicle : String ,
    val isUnreadMsg : Boolean

):Serializable