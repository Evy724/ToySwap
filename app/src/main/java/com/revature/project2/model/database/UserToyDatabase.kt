package com.revature.project2.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revature.project2.model.api.alltoys.ToyItem

@Database(version =1 , entities = [ToyItem::class], exportSchema = false)
abstract class UserToyDatabase: RoomDatabase() {

    abstract fun userToyDao():UserToyDAO

    companion object{
        @Volatile
        private var INSTANCE:UserToyDatabase? = null

        fun getDatabase(context: Context):UserToyDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(lock=this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserToyDatabase::class.java,
                        "UserToyDatabase").build()

                INSTANCE = instance
                return instance
            }
        }

    }

}