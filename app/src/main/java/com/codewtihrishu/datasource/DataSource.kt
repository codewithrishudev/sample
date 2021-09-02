package com.citycarcare.myapplication.datasource

import com.codewtihrishu.model.HomeResponse
import retrofit2.Call


import com.citycarcare.myapplication.networkservice.RetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Callback
import retrofit2.Response

suspend fun <T : Any> Call<T>.getResult(): com.codewtihrishu.result.Result<T> = suspendCoroutine {
    this.enqueue(object : Callback<T> {

        override fun onFailure(call: Call<T>?, error: Throwable?) =
            it.resume(com.bigsteptask.result.Result.Error(error))

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if (response?.body() != null) {
                it.resume(com.bigsteptask.result.Result.Success(response.body()!!))
            } else {
                it.resume(com.bigsteptask.result.Result.Error(Throwable("INTERNAL SERVER ERROR")))
            }
        }
    })
}

inline fun <T : Any> com.codewtihrishu.result.Result<T>.onSuccess(action: (T) -> Unit): com.codewtihrishu.result.Result<T> {
    if (this is com.codewtihrishu.result.Result.Success) action(data)

    return this
}

inline fun <T : Any> com.codewtihrishu.result.Result<T>.onError(action: (Throwable) -> Unit) {
    if (this is com.codewtihrishu.result.Result.Error && error != null) action(error)
}

suspend fun getHomeresponse(term:String,media :String): com.codewtihrishu.result.Result<HomeResponse> = GlobalScope.async {
    return@async RetrofitService().videolist.homedata(term,media).getResult()
}.await()