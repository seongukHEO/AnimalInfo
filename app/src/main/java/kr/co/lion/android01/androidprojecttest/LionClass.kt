package kr.co.lion.android01.androidprojecttest

import android.os.Parcel
import android.os.Parcelable

class LionClass(
    override var name: String?,
    override var age: Int,
    override var uriImage: Int = R.drawable.lion,
    var fair:Int,
    var gender:String?) : AnimalClass(), Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(uriImage)
        parcel.writeInt(fair)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LionClass> {
        override fun createFromParcel(parcel: Parcel): LionClass {
            return LionClass(parcel)
        }

        override fun newArray(size: Int): Array<LionClass?> {
            return arrayOfNulls(size)
        }
    }
}