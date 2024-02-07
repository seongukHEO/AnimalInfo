package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable
import kr.co.lion.androidproject1test.AnimalType

open class AnimalClass{
    var name = ""
    var age = 0
    var type = AnimalType.ANIMAL_TYPE_LION
}