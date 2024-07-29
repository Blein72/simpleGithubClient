package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.testdata.TEST_USER_DETAILS_DATA
import com.blein72.simplegithubclient.testdata.TEST_USER_LIST_DATA
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
        val expectedResponse = Response.success(TEST_USER_LIST_DATA)
        coEvery { api.getUsersList() } returns expectedResponse

        // When
        val actualResponse = usersRemoteDataSource.getUsersList()

        // Assert
        assertEquals(expectedResponse, actualResponse)
        coVerify { api.getUsersList() }
    }

    @Test(expected = Exception::class)
    fun `getUsersList should call API getUsersList and return exception in case of api return Exception`() =
        runTest {
            // Given
            val expectedResponse = Exception("something went wrong")
            coEvery { api.getUsersList() } throws expectedResponse

            // When
            usersRemoteDataSource.getUsersList()

            // Assert
            coVerify { api.getUsersList() }
        }

    @Test()
    fun `getUserDetails should return response from api`() = runTest {
        // Given
        val userName = "userName"
        val expectedResponse = Response.success(TEST_USER_DETAILS_DATA)
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
            coVerify { api.getUsersList() }
        }
}
