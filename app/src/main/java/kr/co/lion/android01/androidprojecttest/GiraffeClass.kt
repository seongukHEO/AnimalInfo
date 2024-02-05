package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable

class GiraffeClass(
    override var name: String?,
    override var age: Int,
    override var uriImage: Int = R.drawable.giraffe,
    var neck:Int,
    var run:Int) : AnimalClass(), Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(uriImage)
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