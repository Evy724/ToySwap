package com.revature.project2.viewmodel

import com.revature.project2.model.api.allusers.User
import com.revature.project2.viewmodel.LoginViewModel
import junit.framework.TestCase
import org.junit.Test

class LoginViewModelTest : TestCase() {

    @Test
    fun testExistingUserCheck() {
        val userList:List<User> = listOf(
            User("Ryan","Pass",0),
            User("Tom","Pass",1),
            User("James","Pass",2),
        )
        val sName = "Ryan"
        val sPass = "Pass"
        var bResult = false
        userList.forEach {
            if (sName == it.sName && sPass == it.sPass){
                bResult = true
            }
        }
        assertTrue(bResult)


    }
}