package com.andra.retrofit.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andra.retrofit.network.ApiClient
import com.andra.retrofit.pojo.DeletePersonResponse
import com.andra.retrofit.pojo.GetPersonsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter (val listener: Listener){
    interface Listener{
        fun showListPerson(list: List<GetPersonsResponse.Result>)
        fun showDialog()
        fun dismissDialog()
        fun showToastError(t: Throwable)
        fun deleteSuccess(msg: String)
        fun goToAddActivity()
        fun goToEditActivity(result: GetPersonsResponse.Result)
    }

    fun getPersons(){
        listener.showDialog()
        ApiClient.apiService.getAllPersons().enqueue(object : Callback<GetPersonsResponse> {
            override fun onResponse(
                call: Call<GetPersonsResponse>,
                response: Response<GetPersonsResponse>
            ) {
                response.body()?.result?.let {
                    listener.showListPerson(it)
                }
                listener.dismissDialog()
            }

            override fun onFailure(call: Call<GetPersonsResponse>, t: Throwable) {
                listener.showToastError(t)
                listener.dismissDialog()
            }


        })
    }

    fun goAddActivity(){
        listener.goToAddActivity()
    }

    fun goUpdateActivity(result: GetPersonsResponse.Result){
        listener.goToEditActivity(result)
    }

    fun deletePerson(result: GetPersonsResponse.Result){
        listener.showDialog()
        ApiClient.apiService.deletePerson(result.iD.toString()).enqueue(object : Callback<DeletePersonResponse> {
            override fun onResponse(
                call: Call<DeletePersonResponse>,
                response: Response<DeletePersonResponse>
            ) {
                listener.deleteSuccess(response.message())
                listener.dismissDialog()
            }

            override fun onFailure(call: Call<DeletePersonResponse>, t: Throwable) {
                listener.showToastError(t)
                listener.dismissDialog()
            }

        })
    }
}