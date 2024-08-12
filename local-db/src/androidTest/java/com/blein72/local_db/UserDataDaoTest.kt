package com.blein72.local_db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.blein72.local_db.entity.UserDataEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDataDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserLocalDatabase
    private lateinit var userDataDao: UserDataDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserLocalDatabase::class.java
        ).allowMainThreadQueries().build()

        userDataDao = database.userDataDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `saveUserData_should_insert_data_into_the_database`() = runBlocking {
        // Given
        val userDataEntityList = TEST_USER_LIST_DATA_ENTITY

        // When
        userDataDao.saveUserData(userDataEntityList)

        // Assert
        val result = userDataDao.getNextUserData(startingId = 0, limit = 4)
        assertEquals(userDataEntityList, result)
    }

    @Test
    fun `getNextUserData_should_return_the_correct_data`() = runBlocking {
        // Given
        val userDataEntityList = TEST_USER_LIST_DATA_ENTITY

        userDataDao.saveUserData(userDataEntityList)

        // When
        val result = userDataDao.getNextUserData(startingId = 0, limit = 4)

        // Assert
        assertEquals(userDataEntityList, result)
    }

    @Test
    fun `deleteAllUsers_should_clear_all_data_from_the_database`() = runBlocking {
        // Given
        val userDataEntityList = TEST_USER_LIST_DATA_ENTITY
        userDataDao.saveUserData(userDataEntityList)

        // When
        userDataDao.deleteAllUsers()

        // Assert
        val result = userDataDao.getNextUserData(startingId = 0, limit = 4)
        assertEquals(emptyList<UserDataEntity>(), result)
    }
}

val TEST_USER_DATA_ENTITY = UserDataEntity(
    avatarUrl = "avatarUrl",
    login = "Name",
    url = "profileUrl",
    id = 1
)

val TEST_USER_LIST_DATA_ENTITY = listOf(
    TEST_USER_DATA_ENTITY.copy(
        login = "Name1",
        url = "ProfileUrl1",
        id = 1
    ),
    TEST_USER_DATA_ENTITY.copy(
        login = "Name2",
        url = "ProfileUrl2",
        id = 2
    ),
    TEST_USER_DATA_ENTITY.copy(
        login = "Name3",
        url = "ProfileUrl3",
        id = 3
    ),
    TEST_USER_DATA_ENTITY.copy(
        login = "Name4",
        url = "ProfileUrl4",
        id = 4
    )
)
