package com.revature.project2.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revature.project2.MainActivity
import com.revature.project2.model.api.alltoys.ToyItem

class ToyItemViewModel: ViewModel()
{
    var toy: ToyItem? = null
}