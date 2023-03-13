package com.example.shakecraft.model

import android.os.Parcel
import android.os.Parcelable

class Recipe(val item: Item, val ingredients : List<Item>, val type: String) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(item, flags)
        parcel.writeTypedList(ingredients)
        parcel.writeString(type)
    }
    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(
                parcel.readParcelable(Item::class.java.classLoader)!!,
                parcel.createTypedArrayList(Item.CREATOR)!!,
                parcel.readString()!!
            )
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}