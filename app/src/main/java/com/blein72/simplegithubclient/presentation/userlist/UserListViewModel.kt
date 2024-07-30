package com.blein72.simplegithubclient.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blein72.simplegithubclient.data.UsersRepository
import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UsersRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun getUserList() {
        viewModelScope.launch(dispatcher) {
            _state.update { data ->
                data.copy(
                    showLoading = true
                )
            }
            val result = repository.getUsers()
            when (result) {
                is Result.Error -> {
                    showErrorDialog(result.exception.toString())
                }

                is Result.Success -> {
                    _state.update { data ->
                        data.copy(
                            showLoading = false,
                            userList = result.data
                        )
                    }
                }
            }

        }
    }

    fun hideErrorDialog() {
        _state.update { data ->
            data.copy(
                showErrorDialog = false
            )
        }
    }

    private fun showErrorDialog(error: String) {
        _state.update { data ->
            data.copy(
                showLoading = false,
                showErrorDialog = true,
                errorMessage = error
            )
        }
    }

    data class State(
        val showLoading: Boolean = false,
        val userList: List<User> = emptyList(),
        val showErrorDialog: Boolean = false,
        val errorMessage: String? = null
    )
}