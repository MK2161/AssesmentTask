package com.example.assesmenttask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assesmenttask.R
import com.example.assesmenttask.data.model.UserDetails

class HistoryListAdapter(
    private val userDetails: MutableList<UserDetails?>,
    private val context: Context,
    private val onHistoryClicked : () -> Unit,
) : RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_history_item, parent, false)
        return HistoryListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userDetails.size
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        val  userDetail = userDetails[position]
        Glide.with(context)
            .load(userDetail?.avatarUrl)
            .into(holder.uiIvImages)
        holder.uiTvName.text = userDetail?.login
        holder.uiIvCvHistoryCard.setOnClickListener {
            onHistoryClicked()
        }
    }


    inner class HistoryListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var uiTvName: AppCompatTextView = view.findViewById(R.id.uiTvName)
        val uiIvImages: AppCompatImageView = view.findViewById(R.id.uiIvImages)
        val uiIvCvHistoryCard: ConstraintLayout = view.findViewById(R.id.uiCvHistoryItem)
    }
}