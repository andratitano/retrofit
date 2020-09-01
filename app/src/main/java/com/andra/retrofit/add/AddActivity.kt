package com.andra.retrofit.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andra.retrofit.R
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddPresenter.Listener {
    private lateinit var presenter: AddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        presenter = AddPresenter(this)

        btnAdd.setOnClickListener {
            presenter.addPerson(etFirstName.text.toString(), etLastName.text.toString())
        }

    }

    override fun showAddSuccess(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showAddFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}