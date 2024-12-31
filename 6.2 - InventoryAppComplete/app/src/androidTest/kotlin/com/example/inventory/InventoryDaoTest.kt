package com.example.inventory

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InventoryDaoTest {
	private lateinit var dao: ItemDao
	private lateinit var db: InventoryDatabase
	
	private val item1 = Item(id =1, name = "item1", price = 1.0, quantity = 1)
	private val item2 = Item(id =2, name = "item2", price = 2.0, quantity = 2)
	
	private suspend fun insertOneItem() {
		dao.insert(item1)
	}
	
	private suspend fun insertTwoItems() {
		dao.insert(item1)
		dao.insert(item2)
	}
	
	@Before
	fun setup() {
		db = Room.inMemoryDatabaseBuilder(
			context = ApplicationProvider.getApplicationContext(),
			klass = InventoryDatabase::class.java
		)
			.allowMainThreadQueries().build()
		dao = db.itemDao()
	}
	
	@After
	@Throws(IOException::class)
	fun teardown() {
		db.close()
	}
	
	@Test
	@Throws(Exception::class)
	fun daoInsert_insertsOneItem() {
		runBlocking {
			insertOneItem()
			val items = dao.getAllItems().first()
			assertEquals(items[0], item1)
		}
	}
	
	@Test
	@Throws(Exception::class)
	fun daoGetAllItems_returnsAllItems() {
		runBlocking {
			insertTwoItems()
			val items = dao.getAllItems().first()
			assertEquals(items[0], item1)
			assertEquals(items[1], item2)
		}
	}
	
	@Test
	@Throws(Exception::class)
	fun daoUpdate_updatesItem() {
		runBlocking {
			insertTwoItems()
			
			val updatedItem1 = item1.copy(name = "updatedItem1", price = 2.0, quantity = 2)
			val updatedItem2 = item2.copy(name = "updatedItem2", price = 3.2, quantity = 3)
			
			dao.update(updatedItem1)
			dao.update(updatedItem2)
			
			val items = dao.getAllItems().first()
			assertEquals(items[0], updatedItem1)
			assertEquals(items[1], updatedItem2)
		}
	}
	
	@Test
	@Throws(Exception::class)
	fun daoDelete_deletesItem() {
		runBlocking {
			insertTwoItems()
			dao.delete(item1)
			val items = dao.getAllItems().first()
			assertEquals(items.size, 1)
			assertEquals(items[0], item2)
		}
	}
	
	@Test
	@Throws(Exception::class)
	fun daoGetItem_returnsItem() {
		runBlocking {
			insertTwoItems()
			val item = dao.getItem(item1.id).first()
			assertEquals(item, item1)
		}
	}
}