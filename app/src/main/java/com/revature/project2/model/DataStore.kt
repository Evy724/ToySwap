package com.revature.project2.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    //set up our datastore
    companion object{
        private val Context.dataStore: DataStore<Preferences>
            by preferencesDataStore("userLogin")

        //Store the username and password in it
        val USER_USERNAME_KEY = stringPreferencesKey("user_username")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    //get Username
    val getUsername: Flow<String?> = context.dataStore.data.map {
        it[USER_USERNAME_KEY]?:"Username"
    }

    //save Username
    suspend fun saveUsername(name:String){
        context.dataStore.edit {
            it[USER_USERNAME_KEY] = name
        }
    }
    //get Password
    val getPassword: Flow<String?> = context.dataStore.data.map {
        it[USER_PASSWORD_KEY]?:"Password"
    }

    //save Password
    suspend fun savePassword(name:String){
        context.dataStore.edit {
            it[USER_PASSWORD_KEY] = name
        }
    }



}