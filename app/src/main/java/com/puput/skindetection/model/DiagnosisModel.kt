package com.puput.skindetection.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiagnosisModel(
        val description: String

) : Parcelable

