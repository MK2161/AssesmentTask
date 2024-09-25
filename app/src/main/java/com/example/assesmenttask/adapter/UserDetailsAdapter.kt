package com.example.assesmenttask.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assesmenttask.R
import com.example.assesmenttask.data.model.UserDetails

class UserDetailsAdapter(
    private val userDetails: MutableList<UserDetails?>,
    private val context: Context,
    ) : RecyclerView.Adapter<UserDetailsAdapter.UserDetailsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_details_info, parent, false)
        return UserDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userDetails.size
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        val  userDetail = userDetails[position]
        Glide.with(context)
            .load(userDetail?.avatarUrl)
            .into(holder.uiIvImages)
        holder.uiTvName.text = userDetail?.login
    }

    inner class UserDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var uiTvName: AppCompatTextView = view.findViewById(R.id.uiTvName)
        val uiIvImages: AppCompatImageView = view.findViewById(R.id.uiIvImages)
    }
}

