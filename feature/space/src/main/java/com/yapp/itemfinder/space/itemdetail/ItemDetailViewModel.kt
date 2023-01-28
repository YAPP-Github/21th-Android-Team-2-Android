package com.yapp.itemfinder.space.itemdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yapp.itemfinder.domain.repository.ItemRepository
import com.yapp.itemfinder.feature.common.BaseStateViewModel
import com.yapp.itemfinder.feature.common.extension.runCatchingWithErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseStateViewModel<ItemDetailState, ItemDetailSideEffect>() {

    override val _stateFlow: MutableStateFlow<ItemDetailState> =
        MutableStateFlow(ItemDetailState.Uninitialized)

    override val _sideEffectFlow: MutableSharedFlow<ItemDetailSideEffect> = MutableSharedFlow()

    private val itemId by lazy { savedStateHandle.get<Long>(ItemDetailFragment.ITEM_ID_KEY) }

    override fun fetchData(): Job = viewModelScope.launch {
        runCatchingWithErrorHandler {
            setState(ItemDetailState.Loading)
            val item = itemId?.let { itemRepository.getItemById(it) }
                ?: throw IllegalArgumentException("물건 정보를 불러올 수 없습니다.")
            item
        }.onSuccess { item ->
            setState(
                ItemDetailState.Success(
                    item = item
                )
            )
        }.onFailure {
            setState(ItemDetailState.Error(it))
        }
    }
}
