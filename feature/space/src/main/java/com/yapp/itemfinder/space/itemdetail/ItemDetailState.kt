package com.yapp.itemfinder.space.itemdetail

import com.yapp.itemfinder.domain.model.Item
import com.yapp.itemfinder.feature.common.State

sealed class ItemDetailState : State {
    object Uninitialized : ItemDetailState()

    object Loading : ItemDetailState()

    data class Success(
        val item: Item
    ) : ItemDetailState()

    data class Error(
        val e: Throwable
    ) : ItemDetailState()
}
