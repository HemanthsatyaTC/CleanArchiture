package com.hemanth.cleanarchiture.viewmodel

import com.hemanth.data.model.shopping.ShoppingDataItemModel
import com.hemanth.data.repository.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SuperHeroAdapterTest{

    private lateinit var viewModel: SuperHeroAdapter
    private val mockRepository = mockk<Repository>()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SuperHeroAdapter(repository = mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchDetails successfully updates upcomingData`(): Unit = runTest {
        // Mock repository call to return fake data
        val fakeData = listOf(
            ShoppingDataItemModel(id = 1, title = "Item 1", category = "electronics", price = 100.0),
            ShoppingDataItemModel(id = 2, title = "Item 2", category = "jewelery", price = 50.0)
        )

        // Mock the repository's getDetails function
        coEvery { mockRepository.getDetails() } returns fakeData

        // Trigger the fetchDetails method
        viewModel.fetchDetails()

        // Simulate coroutine execution until idle
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify that the upcomingData was updated with fakeData
        assertEquals(fakeData, viewModel.upcomingData.value)

        // Verify that the repository's getDetails function was called once
        coVerify(exactly = 1) { mockRepository.getDetails() }
    }

    @Test
    fun `getProductsByCategory filters products correctly`() = runTest {
        // Prepare fake data
        val fakeData = listOf(
            ShoppingDataItemModel( title = "Item 1", category = "electronics", price = 100.0),
            ShoppingDataItemModel( title = "Item 2", category = "jewelery", price = 50.0)
        )

        coEvery { mockRepository.getDetails() } returns fakeData

        // Trigger data fetch
        viewModel.fetchDetails()

        // Simulate coroutine execution
        testDispatcher.scheduler.advanceUntilIdle()

        // Test filtering by category
        val electronics = viewModel.getProductsByCategory("electronics")
        assertEquals(1, electronics.size)
        assertEquals("Item 1", electronics[0].title)
    }
}