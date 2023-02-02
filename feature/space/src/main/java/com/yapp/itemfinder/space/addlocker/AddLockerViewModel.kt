package com.yapp.itemfinder.space.addlocker

import com.yapp.itemfinder.domain.model.AddLockerNameInput
import com.yapp.itemfinder.domain.model.AddLockerPhoto
import com.yapp.itemfinder.domain.model.AddLockerSpace
import com.yapp.itemfinder.domain.model.LockerIcons
import com.yapp.itemfinder.feature.common.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddLockerViewModel @Inject constructor(

) : BaseStateViewModel<AddLockerState, AddLockerSideEffect>() {
    override val _stateFlow: MutableStateFlow<AddLockerState> =
        MutableStateFlow(AddLockerState.Uninitialized)
    override val _sideEffectFlow: MutableSharedFlow<AddLockerSideEffect> = MutableSharedFlow()

    init {
        setState(
            AddLockerState.Success(
                listOf(
                    AddLockerNameInput(),
                    AddLockerSpace(name = "주방"),
                    LockerIcons(),
                    AddLockerPhoto()
                ),
                spaceId = 2L
            )
        )
    }

    fun openSelectSpace() {
        withState<AddLockerState.Success> {
            postSideEffect(AddLockerSideEffect.OpenSelectSpace)
        }
    }

    fun getSpaceId(): Long {
        var id = 0L
        withState<AddLockerState.Success> { state ->
            id = state.spaceId
        }
        return id
    }

    fun setSpaceId(id: Long) {
        withState<AddLockerState.Success> { state ->
            setState(
                state.copy(spaceId = id)
            )
        }
    }

    fun changeSpace(name: String) {
        withState<AddLockerState.Success> { state ->
            setState(
                state.copy(
                    dataList = ArrayList(state.dataList).apply {
                        removeAt(1)
                        add(1, AddLockerSpace(name = name))
                    }
                )
            )
        }
    }
}
