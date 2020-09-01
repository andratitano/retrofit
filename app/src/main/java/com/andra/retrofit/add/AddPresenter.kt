package com.andra.retrofit.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andra.retrofit.network.ApiClient
import com.andra.retrofit.pojo.PostPersonBody
import com.andra.retrofit.pojo.PostPersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPresenter (val listener: Listener){
    interface Listener{
        fun showAddSuccess(msg: String)
        fun showAddFailed(msg: String)
    }

    fun addPerson(firstname : String, lastName: String){
        val personBody = PostPersonBody(firstname,lastName)

        ApiClient.apiService.addPerson(personBody).enqueue(object : Callback<PostPersonResponse> {
            override fun onResponse(
                call: Call<PostPersonResponse>,
                response: Response<PostPersonResponse>
            ) {
                listener.showAddSuccess("Tambah Data Berhasil")
            }

            override fun onFailure(call: Call<PostPersonResponse>, t: Throwable) {
                listener.showAddFailed("$t")
            }

        })
    }
}