package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable

class GiraffeClass(var name:String?, var age:Int, var neck:Int, var run:Int) : Parcelable {
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
        parcel.writeInt(neck)
        parcel.writeInt(run)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GiraffeClass> {
        override fun createFromParcel(parcel: Parcel): GiraffeClass {
            return GiraffeClass(parcel)
        }

        override fun newArray(size: Int): Array<GiraffeClass?> {
            return arrayOfNulls(size)
        }
    }
}