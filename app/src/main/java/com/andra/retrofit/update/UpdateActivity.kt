package com.andra.retrofit.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andra.retrofit.R
import com.andra.retrofit.pojo.*
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity(), UpdatePresenter.Listener {
    private  lateinit var presenter: UpdatePresenter
    private  lateinit var result: GetPersonsResponse.Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        intent.getParcelableExtra<GetPersonsResponse.Result>("PERSON")?.let {
            result = it
        }

        presenter = UpdatePresenter(this)

        etFirstName.setText(result.firstName)
        etLastName.setText(result.lastName)

        btnUpdate.setOnClickListener {
            result.apply {
                firstName = etFirstName.text.toString()
                lastName = etLastName.text.toString()
            }

            presenter.updatePerson(result)
        }

    }

    override fun showEditSuccess(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showEditFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}