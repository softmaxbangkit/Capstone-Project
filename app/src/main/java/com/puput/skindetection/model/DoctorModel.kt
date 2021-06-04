package com.puput.skindetection.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(
        var img : String,
        var information : String,
        var name : String,
        var occupation : String

) : Parcelable