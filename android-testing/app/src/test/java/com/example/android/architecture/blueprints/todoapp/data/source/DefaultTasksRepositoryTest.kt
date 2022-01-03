package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test


class DefaultTasksRepositoryTest {

    private val task1 = Task("title1", "description1")
    private val task2 = Task("title2", "description2")
    private val task3 = Task("title3", "description3")

    private val remoteDataSource = listOf(task1, task2, task3).sortedBy { it.id }
    private val localDataSource = listOf(task1, task2).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: TasksDataSource
    private lateinit var tasksLocalDataSource: TasksDataSource


    //system under test
    private lateinit var sut: DefaultTasksRepository

    @Before
    fun setUp() {
        tasksRemoteDataSource = FakeDataSource(remoteDataSource.toMutableList())
        tasksLocalDataSource = FakeDataSource(localDataSource.toMutableList())

        sut = DefaultTasksRepository(
            tasksRemoteDataSource,
            tasksLocalDataSource,
            Dispatchers.Unconfined
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTasks_requestFromRemoteDataSource_returnsAllTasks() = runBlockingTest {
        //arrange
        //act
        val result = sut.getTasks(true) as Result.Success
        //assert
        assertThat(result.data, IsEqual(remoteDataSource))
    }
}