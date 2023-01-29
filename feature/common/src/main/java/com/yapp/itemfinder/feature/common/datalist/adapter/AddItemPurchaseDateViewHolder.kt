package com.yapp.itemfinder.feature.common.datalist.adapter

import com.yapp.itemfinder.domain.model.AddItemPurchaseDate
import com.yapp.itemfinder.feature.common.databinding.AddItemPurchaseDateBinding

class AddItemPurchaseDateViewHolder(
    val binding: AddItemPurchaseDateBinding
) : DataViewHolder<AddItemPurchaseDate>(binding) {
    override fun reset() {
        return
    }

    override fun bindData(data: AddItemPurchaseDate) {
        super.bindData(data)
        binding.purchaseDateTextView.text = data.purchaseDate
    }

    override fun bindViews(data: AddItemPurchaseDate) {
        binding.purchaseDateTextView.setOnClickListener { data.openDatePicker() }
    }
}