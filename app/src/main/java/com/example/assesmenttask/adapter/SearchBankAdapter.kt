package com.example.assesmenttask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assesmenttask.R
import com.example.assesmenttask.data.model.BankDetails

class SearchBankAdapter(
    private val context: Context,
    private val bankDetails: ArrayList<BankDetails>,
): RecyclerView.Adapter<SearchBankAdapter.BankViewHolder>() {


    inner class BankViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var uiTvAccIcon: ImageView = view.findViewById(R.id.uiIvImages)
        var uiTvAccName: AppCompatTextView = view.findViewById(R.id.uiTvBankName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_search_item, parent, false)
        return BankViewHolder(view)

    }

    override fun getItemCount(): Int {
        return bankDetails.size
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val  bankDetails = bankDetails[position]
        Glide.with(context)
            .load(bankDetails.icon)
            .override(40)
            .centerCrop()
            .into(holder.uiTvAccIcon)
        holder.uiTvAccName.text = bankDetails.bankName
    }
}