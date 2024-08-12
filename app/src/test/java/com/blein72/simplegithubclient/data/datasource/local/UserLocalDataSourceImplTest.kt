package com.blein72.simplegithubclient.data.datasource.local

import com.blein72.simplegithubclient.data.datasource.local.db.UserDataDao
import com.blein72.simplegithubclient.testdata.TEST_USER_LIST_DATA
import com.blein72.simplegithubclient.testdata.TEST_USER_LIST_DATA_ENTITY
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserLocalDataSourceImplTest {

    private lateinit var userLocalDataSource: UserLocalDataSourceImpl
    private val userDataDao: UserDataDao = mockk()

    @Before
    fun setUp() {
        userLocalDataSource = UserLocalDataSourceImpl(userDataDao)
    }

    @Test
    fun `saveUsersList should map data and call dao saveUserData`() = runTest {
        // Given
        val userDataList = TEST_USER_LIST_DATA
        val userDataEntityList = TEST_USER_LIST_DATA_ENTITY

        coEvery { userDataDao.saveUserData(userDataEntityList) } returns Unit

        // When
        userLocalDataSource.saveUsersList(userDataList)

        // Assert
        coEvery { userDataDao.saveUserData(userDataEntityList) }
    }

    @Test
    fun `getUserData should call dao getNextUserData and return mapped data`() = runTest {
        // Given
        val startingId = 1
        val userDataEntityList = TEST_USER_LIST_DATA_ENTITY
        val userDataList = TEST_USER_LIST_DATA

        coEvery  { userDataDao.getNextUserData(startingId) } returns userDataEntityList

        // When
        val result = userLocalDataSource.getUserData(startingId)

        // Assert
        assertEquals(userDataList, result)
        coVerify { userDataDao.getNextUserData(startingId) }
    }
}