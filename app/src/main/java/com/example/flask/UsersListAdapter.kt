package com.example.flask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flask.databinding.ItemLayoutBinding

class UsersListAdapter(
    private var list: List<UserData>
) : RecyclerView.Adapter<UsersListViewHolder>() {

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context))

        return UsersListViewHolder(binding)
    }

    override fun getItemCount() = list.size

    fun update(newList: List<android.service.autofill.UserData>){
        list = newList
        notifyDataSetChanged()
    }
}