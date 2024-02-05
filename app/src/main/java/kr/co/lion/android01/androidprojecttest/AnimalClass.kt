package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable

open class AnimalClass(open var name:String?, open var age:Int, open var uriImage:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(uriImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimalClass> {
        override fun createFromParcel(parcel: Parcel): AnimalClass {
            return AnimalClass(parcel)
        }

        override fun newArray(size: Int): Array<AnimalClass?> {
            return arrayOfNulls(size)
        }
    }

}

