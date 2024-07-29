package com.blein72.simplegithubclient.data

import com.blein72.simplegithubclient.data.datasource.UsersRemoteDataSource
import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.data.model.UserDetail
import com.blein72.simplegithubclient.util.Result
import com.blein72.simplegithubclient.testdata.TEST_USER_DETAILS_DATA
import com.blein72.simplegithubclient.testdata.TEST_USER_LIST_DATA
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class UsersRepositoryImplTest {

    private val remoteDataSource: UsersRemoteDataSource = mockk()
    private lateinit var usersRepository: UsersRepositoryImpl

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getUsers should return Success when remoteDataSource returns a successful response`() = runBlocking {
        // Given
        val expectedUsersList = TEST_USER_LIST_DATA
        val response = Response.success(expectedUsersList)
        coEvery { remoteDataSource.getUsersList() } returns response

        // When
        val result = usersRepository.getUsers()

        // Assert
        assertTrue(result is Result.Success)
        assertEquals(expectedUsersList, (result as Result.Success).data)
        coVerify { remoteDataSource.getUsersList() }
    }

    @Test
    fun `getUsers should return Error when remoteDataSource returns an error response`() = runBlocking {
        // Given
        val response = Response.error<List<User>>(404, responseBody)
        coEvery { remoteDataSource.getUsersList() } returns response

        // When
        val result = usersRepository.getUsers()

        // Assert
        assertTrue(result is Result.Error)
        assertEquals("Failed with code: 404", (result as Result.Error).exception.message)
        coVerify { remoteDataSource.getUsersList() }
    }

    @Test
    fun `getUsers should return Error when remoteDataSource throws an exception`() = runBlocking {
        // Given
        val exception = Exception("something went wrong")
        coEvery { remoteDataSource.getUsersList() } throws exception

        // When
        val result = usersRepository.getUsers()

        // Assert
        assertTrue(result is Result.Error)
        assertEquals(exception.message, (result as Result.Error).exception.message)
        coVerify { remoteDataSource.getUsersList() }
    }

    @Test
    fun `getUserDetails should return Success when remoteDataSource returns a successful response`() = runBlocking {
        // Given
        val userName = "userName"
        val expectedUserDetail = TEST_USER_DETAILS_DATA
        val response = Response.success(expectedUserDetail)
        coEvery { remoteDataSource.getUserDetails(userName) } returns response

        // When
        val result = usersRepository.getUserDetails(userName)

        // Assert
        assertTrue(result is Result.Success)
        assertEquals(expectedUserDetail, (result as Result.Success).data)
        coVerify { remoteDataSource.getUserDetails(userName) }
    }

    @Test
    fun `getUserDetails should return Error when remoteDataSource returns an error response`() = runBlocking {
        // Given
        val userName = "userName"
        val response = Response.error<UserDetail>(404,responseBody)
        coEvery { remoteDataSource.getUserDetails(userName) } returns response

        // When
        val result = usersRepository.getUserDetails(userName)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals("Failed with code: 404", (result as Result.Error).exception.message)
        coVerify { remoteDataSource.getUserDetails(userName) }
    }

    @Test
    fun `getUserDetails should return Error when remoteDataSource throws an exception`() = runBlocking {
        // Given
        val userName = "userName"
        val exception = Exception("something went wrong")
        coEvery { remoteDataSource.getUserDetails(userName) } throws exception

        // When
        val result = usersRepository.getUserDetails(userName)

        // Assert
        assertTrue(result is Result.Error)
        assertEquals(exception.message, (result as Result.Error).exception.message)
        coVerify { remoteDataSource.getUserDetails(userName) }
    }
}

val mediaType = "application/json".toMediaTypeOrNull()
val responseBody = ResponseBody.create(mediaType, "{\"key\":\"value\"}")