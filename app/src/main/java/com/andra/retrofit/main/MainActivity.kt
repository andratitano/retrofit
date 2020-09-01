package com.andra.retrofit.main

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.andra.retrofit.R
import com.andra.retrofit.add.AddActivity
import com.andra.retrofit.pojo.GetPersonsResponse
import com.andra.retrofit.update.UpdateActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {
    lateinit var progressDialog: ProgressDialog
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait...")

        fabAdd.setOnClickListener {
            presenter.goAddActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getPersons()
    }

    override fun showListPerson(list: List<GetPersonsResponse.Result>) {
        rvContainer.adapter = PersonAdapter(list, presenter)
        rvContainer.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
    }

    override fun showDialog() {
        progressDialog.show()
    }

    override fun dismissDialog() {
        progressDialog.dismiss()
    }

    override fun showToastError(t: Throwable) {
        Toast.makeText(this@MainActivity, "Error ${t.localizedMessage}", Toast.LENGTH_LONG)
            .show()
    }

    override fun deleteSuccess(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG)
            .show()
    }

    override fun goToAddActivity() {
        startActivity(Intent(this,AddActivity::class.java))
    }

    override fun goToEditActivity(result: GetPersonsResponse.Result) {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("PERSON", result)
        startActivity(intent)
    }
}