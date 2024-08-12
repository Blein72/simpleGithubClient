package com.blein72.feature_users.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blein72.core.data.UserData
import com.blein72.feature_users.data.UsersRepository
import com.blein72.core.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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

    private var queryParam = QueryState()

    fun getInitialData() {
        getUserList(showScreenLoading = true)
    }

    fun loadNextPage() {
        getUserList(showScreenLoading = false)
    }

    fun reloadCurrentPage() {
        getUserList(showScreenLoading = false)
    }

    private fun getUserList(showScreenLoading: Boolean) {
        viewModelScope.launch(dispatcher) {
            if (showScreenLoading) {
                _state.update { data ->
                    data.copy(
                        showScreenLoading = true
                    )
                }
            } else {
                _state.update { data ->
                    data.copy(showListItemsLoading = true)
                }
            }

            val result = repository.getUsers(queryParam.latestId)
            when (result) {
                is Result.Error -> {
                    showErrorDialog(result.exception.toString())
                }

                is Result.Success -> {
                    queryParam = queryParam.copy(latestId = result.data.last().id)
                    val list = _state.value.userList + result.data
                    _state.update { data ->
                        data.copy(
                            showScreenLoading = false,
                            showListItemsLoading = false,
                            userList = list
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
                showScreenLoading = false,
                showListItemsLoading = false,
                showErrorDialog = true,
                errorMessage = error
            )
        }
    }

    data class State(
        val showScreenLoading: Boolean = false,
        val showListItemsLoading: Boolean = false,
        val userList: List<UserData> = emptyList(),
        val showErrorDialog: Boolean = false,
        val errorMessage: String? = null
    )

    data class QueryState(
        val latestId: Int = 0
    )
}