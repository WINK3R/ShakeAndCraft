package com.example.shakecraft.model

import android.os.Parcel
import android.os.Parcelable

class Item(
    val type: ItemType,
    var stack: Int = 1,
) : Parcelable {


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type.name)
        parcel.writeInt(type.rarity)
        parcel.writeInt(type.image)
        parcel.writeInt(type.xpReward)
        parcel.writeInt(stack)
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {

            return Item(
                ItemType(parcel.readString()!!,parcel.readInt(),
                    parcel.readInt(),
                    parcel.readInt(),),
                parcel.readInt()
            )
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}


