package com.citycarcare.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codewtihrishu.data.UserDao
import com.codewtihrishu.model.ResultsItem
import com.citycarcare.myapplication.datasource.getHomeresponse
import com.citycarcare.myapplication.datasource.onError
import com.citycarcare.myapplication.datasource.onSuccess
import com.citycarcare.myapplication.networkservice.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<ResultsItem>> = userDao.readAllData()
    suspend fun addvideo(user: ResultsItem){
        userDao.addvideo(user)
    }

    fun profileByUUID(term:String,media :String): MutableLiveData<ApiResponse> {
        val result = MutableLiveData<ApiResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val requets = getHomeresponse(term,media)
            requets.onSuccess {
               // it.status.let {  -> }
                result.postValue(ApiResponse(it, null))
            }
            requets.onError {
                result.postValue(ApiResponse(null, it))

            }
        }
        return result
    }
}