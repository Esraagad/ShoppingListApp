package com.example.shoppinglistapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingDao
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItem
import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingItemDatabase
import com.example.shoppinglistapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", 1)
        shoppingDao.insertShoppingItem(shoppingItem)
        val shoppingList = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(shoppingList).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", 1)
        shoppingDao.insertShoppingItem(shoppingItem)
        shoppingDao.deleteShoppingItem(shoppingItem)
        val shoppingList = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(shoppingList).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPrice() = runTest {
        val shoppingItem1 = ShoppingItem("name", 1, 1f, "url", 1)
        val shoppingItem2 = ShoppingItem("name", 2, 10f, "url", 2)
        val shoppingItem3 = ShoppingItem("name", 4, 2.5f, "url", 3)

        shoppingDao.insertShoppingItem(shoppingItem1)
        shoppingDao.insertShoppingItem(shoppingItem2)
        shoppingDao.insertShoppingItem(shoppingItem3)
        val totalPrice = shoppingDao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(1*1f + 2*10f +4*2.5f)
    }
}