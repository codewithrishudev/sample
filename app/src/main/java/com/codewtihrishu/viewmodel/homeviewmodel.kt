package com.citycarcare.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codewtihrishu.data.UserDao
import com.codewtihrishu.data.UserDatabase
import com.codewtihrishu.model.ResultsItem
import com.citycarcare.myapplication.networkservice.ApiResponse
import com.citycarcare.myapplication.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class homeviewmodel(application: Application) :AndroidViewModel(application) {
    val readAllData: LiveData<List<ResultsItem>>
    val userDao: UserDao
    private val homerepository: HomeRepository
    init {
         userDao = UserDatabase.getDatabase(application).userDao()
        homerepository = HomeRepository(userDao)
        readAllData = homerepository.readAllData
    }
    fun addvideo(resultitem: ResultsItem){
        viewModelScope.launch(Dispatchers.IO) {
            homerepository.addvideo(resultitem)
        }
    }
    private val videolistrepo: HomeRepository by lazy {
        HomeRepository(userDao)
    }
    fun videolistrepo(term :String,media :String): MutableLiveData<ApiResponse> {
        return videolistrepo.profileByUUID(term,media)
    }
}