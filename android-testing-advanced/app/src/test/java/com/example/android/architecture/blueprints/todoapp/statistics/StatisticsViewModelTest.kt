package com.example.android.architecture.blueprints.todoapp.statistics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.MainCoroutineRule
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StatisticsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: StatisticsViewModel

    private lateinit var repository: FakeTestRepository

    @Before
    fun init() {
        repository = FakeTestRepository()

        viewModel = StatisticsViewModel(repository)
    }

    @Test
    fun refresh_loading(){
        //act
        coroutineRule.pauseDispatcher()    // Pause dispatcher so you can verify initial values.
        viewModel.refresh()
        //assert
        assertThat(viewModel.dataLoading.getOrAwaitValue(), `is`(true))
        coroutineRule.resumeDispatcher() // Execute pending coroutines actions.

        assertThat(viewModel.dataLoading.getOrAwaitValue(), `is`(false))
    }

    @Test
    fun refresh_enableError_returnTrueEmptyAndError(){
        //arrange
        repository.setErrorEnabled(true)
        //act
        viewModel.refresh()
        //assert
        assertThat(viewModel.empty.getOrAwaitValue(), `is`(true))
        assertThat(viewModel.error.getOrAwaitValue(), `is`(true))
    }

}