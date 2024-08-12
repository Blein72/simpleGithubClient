package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.data.datasource.remote.USERS_PER_PAGE
import com.blein72.simplegithubclient.data.datasource.remote.UsersRemoteDataSourceImpl
import com.blein72.simplegithubclient.data.datasource.remote.api.UserApi
import com.blein72.simplegithubclient.testdata.TEST_USER_DETAILS_RESPONSE_DATA
import com.blein72.simplegithubclient.testdata.TEST_USER_RESPONSE_LIST_DATA
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class UsersRemoteDataSourceImplTest {

    private val api: UserApi = mockk()
    private lateinit var usersRemoteDataSource: UsersRemoteDataSourceImpl

    @Before
    fun setUp() {
        usersRemoteDataSource = UsersRemoteDataSourceImpl(api)
    }

    @Test
    fun `getUsersList should call API getUsersList and return proper response`() = runTest {
        // Given
        val expectedResponse = Response.success(TEST_USER_RESPONSE_LIST_DATA)
        val since = 10
        coEvery { api.getUsersList(any(), any()) } returns expectedResponse

        // When
        val actualResponse = usersRemoteDataSource.getUsersList(since)

        // Assert
        assertEquals(expectedResponse, actualResponse)
        coVerify { api.getUsersList(perPage = USERS_PER_PAGE, since = since) }
    }

    @Test(expected = Exception::class)
    fun `getUsersList should call API getUsersList and return exception in case of api return Exception`() =
        runTest {
            // Given
            val expectedResponse = Exception("something went wrong")
            val since = 10
            coEvery { api.getUsersList(any(), any()) } throws expectedResponse

            // When
            usersRemoteDataSource.getUsersList(since)

            // Assert
            coVerify {
                api.getUsersList(
                    perPage = USERS_PER_PAGE,
                    since = since
                )
            }
        }

    @Test
    fun `getUserDetails should return response from api`() = runTest {
        // Given
        val userName = "userName"
        val expectedResponse = Response.success(TEST_USER_DETAILS_RESPONSE_DATA)
        coEvery { api.getUserDetails(userName) } returns expectedResponse

        // When
        val actualResponse = usersRemoteDataSource.getUserDetails(userName)

        // Assert
        assertEquals(expectedResponse, actualResponse)
        coVerify { api.getUserDetails(userName) }
    }

    @Test(expected = Exception::class)
    fun `getUserDetails should call API getUserDetails and return exception in case of api return Exception`() =
        runTest {
            // Given
            val userName = "userName"
            val expectedResponse = Exception("something went wrong")
            coEvery { api.getUserDetails(userName) } throws expectedResponse

            // When
            usersRemoteDataSource.getUserDetails(userName)

            // Assert
            coVerify { api.getUserDetails(userName) }
        }
}
