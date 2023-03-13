package com.example.shakecraft.model

import android.os.Parcel
import android.os.Parcelable


class Item(
    var name: String,
    var rarity: Int = 1,
    var stack: Int = 1,
    var image: Int,
    var xpReward: Int = 0,
) : Parcelable{


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(rarity)
        parcel.writeInt(stack)
        parcel.writeInt(image)
        parcel.writeInt(xpReward)
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(
                parcel.readString()!!,
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt()
            )
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}


