package com.revature.project2.model.api.database

import android.app.Application
import com.revature.project2.model.api.alltoys.ToyItem

class UserToyRoomRepo(app:Application) {

    private var userToyDao:UserToysDAO

    init {
        var database = UserToysDatabase.getUserToyDatabase(app)
        userToyDao = database.userToysDao()
    }

    suspend fun updateUserToys(list:List<ToyItem>){
        userToyDao.deleteUserToys()
        list.forEach {  toy->
            userToyDao.insertUserToy(toy)
        }
    }

    val readAllUserToys: List<ToyItem> =
        userToyDao.fetchAllUserToys()

    suspend fun deleteUserToyById(id:Int){
        userToyDao.deleteUserToyById(id)
    }

    suspend fun insertCustomer(toy:ToyItem){
        userToyDao.insertUserToy(toy)
    }

    suspend fun deleteUserToys(){
        userToyDao.deleteUserToys()
    }
}