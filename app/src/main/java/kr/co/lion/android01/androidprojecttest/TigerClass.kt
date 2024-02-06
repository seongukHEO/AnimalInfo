package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable

class TigerClass(
    override var name: String?,
    override var age: Int,
    override var uriImage: Int,
    var stripe:Int,
    var weight:Double) : AnimalClass(name, age, uriImage), Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(uriImage)
        parcel.writeInt(stripe)
        parcel.writeDouble(weight)
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