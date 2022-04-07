package com.revature.project2.model.api.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revature.project2.model.api.alltoys.ToyItem

@Database(version = 1, entities = [ToyItem::class], exportSchema = false)
abstract class UserToysDatabase: RoomDatabase() {

    abstract fun userToysDao():UserToysDAO

    companion object{
        @Volatile
        private var INSTANCE:UserToysDatabase? = null

        fun getUserToyDatabase(context: Context):UserToysDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(lock = this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    UserToysDatabase::class.java,"UserToyDatabase").build()

                INSTANCE = instance

                return instance
            }
        }
    }
}