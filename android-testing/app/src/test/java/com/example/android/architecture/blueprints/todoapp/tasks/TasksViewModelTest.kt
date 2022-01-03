package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFilterType.ALL_TASKS
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
//@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    private lateinit var fakeRepository: FakeTestRepository
    private lateinit var viewModel: TasksViewModel

    private val task1 = Task("title", "description")
    private val task2 = Task("title", "description")
    private val task3 = Task("title", "description")

    @get:Rule
    var executorRule = InstantTaskExecutorRule() //execute sync

    @Before
    fun setUp() {
        fakeRepository = FakeTestRepository()
        fakeRepository.addTasks(task1, task2, task3)

        viewModel = TasksViewModel(fakeRepository)

        /* with roboletric
        * viewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
        * when it uses AndroidViewModel(application)
        * */

    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        //arrange
        //act
        viewModel.addNewTask()
        //assert
        val result = viewModel.newTaskEvent.getOrAwaitValue()
        assertThat(result.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        // Given a fresh ViewModel
        // When the filter type is ALL_TASKS
        viewModel.setFiltering(ALL_TASKS)
        // Then the "Add task" action is visible
        val result = viewModel.tasksAddViewVisible.getOrAwaitValue()

        assertThat(result, `is`(true))
    }
}