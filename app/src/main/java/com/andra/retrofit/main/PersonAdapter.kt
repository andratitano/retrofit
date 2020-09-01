package com.andra.retrofit.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andra.retrofit.R
import com.andra.retrofit.pojo.GetPersonsResponse
import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter (val listPerson: List<GetPersonsResponse.Result>, val presenter: MainActivityPresenter) : RecyclerView.Adapter<PersonAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvId.text = listPerson[position].iD.toString()
        holder.itemView.tvFirstName.text = listPerson[position].firstName
        holder.itemView.tvLastName.text = listPerson[position].lastName
        holder.itemView.tvUpdatedAt.text = listPerson[position].updatedAt
        holder.itemView.tvCreatedAt.text = listPerson[position].createdAt
        holder.itemView.tvDeletedAt.text = "${listPerson[position].deletedAt}"

        holder.itemView.btnDelete.setOnClickListener {
            presenter.deletePerson(listPerson[position])
        }

        holder.itemView.btnEdit.setOnClickListener {
            presenter.goUpdateActivity(listPerson[position])
        }
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

}