package com.blein72.feature_users.presentation.userDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blein72.core.util.Result
import com.blein72.feature_users.data.UsersRepository
import com.blein72.feature_users.presentation.userDetails.UserDetailsViewModel
import com.blein72.feature_users.testdata.TEST_USER_DETAILS_DATA
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserResponseObjectDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: UsersRepository = mockk()
    private lateinit var viewModel: UserDetailsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserDetailsViewModel(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getUserDetail should update state with user details when repository returns success`() =
        runTest {
            // Given
            val userName = "userName"
            val userDetail = TEST_USER_DETAILS_DATA
            coEvery { repository.getUserDetails(userName) } returns Result.Success(userDetail)

            // When
            viewModel.getUserDetail(userName)

            // Advance the dispatcher to ensure all coroutines complete
            advanceUntilIdle()

            // Assert
            val state = viewModel.state.value
            assertFalse(state.showLoading)
            assertEquals(userDetail, state.userDetail)
            assertFalse(state.showErrorDialog)
            coVerify { repository.getUserDetails(userName) }
        }

    @Test
    fun `getUserDetail should show error dialog when repository returns error`() = runTest {
        // Given
        val userName = "userName"
        val exception = Exception("something went wrong")
        coEvery { repository.getUserDetails(userName) } returns Result.Error(exception)

        // When
        viewModel.getUserDetail(userName)

        // Advance the dispatcher to ensure all coroutines complete
        advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertFalse(state.showLoading)
        assertTrue(state.showErrorDialog)
        assertEquals(exception.toString(), state.errorMessage)
        coVerify { repository.getUserDetails(userName) }
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
