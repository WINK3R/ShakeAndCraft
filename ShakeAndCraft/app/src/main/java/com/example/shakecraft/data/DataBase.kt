package com.example.shakecraft.data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shakecraft.model.Player
import android.content.Context

@Database(entities = [Player::class],version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    companion object{
        private var INSTANCE : DataBase? = null
        fun getInstance(context: Context): DataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "player_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}