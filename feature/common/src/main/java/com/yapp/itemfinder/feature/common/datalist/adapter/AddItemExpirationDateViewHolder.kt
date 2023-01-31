package com.yapp.itemfinder.feature.common.datalist.adapter

import com.yapp.itemfinder.domain.model.AddItemExpirationDate
import com.yapp.itemfinder.feature.common.databinding.AddItemExpirationDateBinding

class AddItemExpirationDateViewHolder(
    val binding: AddItemExpirationDateBinding
) : DataViewHolder<AddItemExpirationDate>(binding) {
    override fun reset() {
        return
    }

    override fun bindData(data: AddItemExpirationDate) {
        super.bindData(data)
        binding.expirationDateTextView.text = data.expirationDate
    }

    override fun bindViews(data: AddItemExpirationDate) {
        binding.expirationDateTextView.setOnClickListener { data.openDatePicker() }
    }
}
