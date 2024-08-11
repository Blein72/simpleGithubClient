package com.blein72.simplegithubclient.presentation.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blein72.simplegithubclient.data.UsersRepository
import com.blein72.simplegithubclient.testdata.TEST_USER_LIST_DATA
import com.blein72.simplegithubclient.testdata.TEST_USER_RESPONSE_LIST_DATA
import com.blein72.simplegithubclient.util.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserResponseObjectListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: UsersRepository = mockk()
    private lateinit var viewModel: UserListViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserListViewModel(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getUserList should update state with user list when repository returns success`() = runTest {
        // Given
        val userList = TEST_USER_LIST_DATA
        coEvery { repository.getUsers() } returns Result.Success(userList)

        // When
        viewModel.getUserList()

        // Advance the dispatcher to ensure all coroutines complete
        advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertFalse(state.showLoading)
        assertEquals(userList, state.userList)
        assertFalse(state.showErrorDialog)
        coVerify { repository.getUsers() }
    }

    @Test
    fun `getUserList should show error dialog when repository returns error`() = runTest {
        // Given
        val exception = Exception("something went wrong")
        coEvery { repository.getUsers() } returns Result.Error(exception)

        // When
        viewModel.getUserList()

        // Advance the dispatcher to ensure all coroutines complete
        advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertFalse(state.showLoading)
        assertTrue(state.showErrorDialog)
        assertEquals(exception.toString(), state.errorMessage)
        coVerify { repository.getUsers() }
    }

    @Test
    fun `hideErrorDialog should update state to hide error dialog`() = runTest {
        // When
        viewModel.hideErrorDialog()

        // Assert
        val state = viewModel.state.value
        assertFalse(state.showErrorDialog)
    }

}