package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable

class TigerClass(var name:String?, var age:Int, var stripe:Int, var weight:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(stripe)
        parcel.writeInt(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TigerClass> {
        override fun createFromParcel(parcel: Parcel): TigerClass {
            return TigerClass(parcel)
        }

        override fun newArray(size: Int): Array<TigerClass?> {
            return arrayOfNulls(size)
        }
    }
}