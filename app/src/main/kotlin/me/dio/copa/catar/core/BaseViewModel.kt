package me.dio.copa.catar.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState, UiAction>(initialState: UiState) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UiState> = _state

    private val _action = MutableSharedFlow<UiAction>()
    val action: SharedFlow<UiAction> = _action

    val stateValue: UiState
        get() = state.value

    protected fun setState(block: UiState.() -> UiState) {
        _state.value = _state.value.block()
    }

    protected fun sendAction(action: UiAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }
}
