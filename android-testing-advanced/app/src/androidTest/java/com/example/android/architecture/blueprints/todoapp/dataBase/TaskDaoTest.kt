package com.example.android.architecture.blueprints.todoapp.dataBase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksDao
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    //run sync
    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    private lateinit var db: ToDoDatabase
    private lateinit var dao: TasksDao

    private val task = Task("title", "description")

    @Before
    fun openDb() {
        db = Room.inMemoryDatabaseBuilder( //temporary
            getApplicationContext(),
            ToDoDatabase::class.java
        ).build()

        dao = db.taskDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun insertTaskAndGetById() = runBlockingTest {
        //arrange
        dao.insertTask(task)

        //act
        val result = dao.getTaskById(task.id)

        //assert
        assertThat(result as Task, notNullValue())
        assertThat(result.id, `is`(task.id))
        assertThat(result.title, `is`(task.title))
        assertThat(result.description, `is`(task.description))
        assertThat(result.isCompleted, `is`(task.isCompleted))
    }

    @Test
    fun updateTaskGetById() = runBlockingTest {
        // 1. Insert a task into the DAO.
        task.title = "new title"
        dao.insertTask(task)
        dao.updateTask(task)
        // 2. Update the task by creating a new task with the same ID but different attributes.

        val result = dao.getTaskById(task.id)

        // 3. Check that when you get the task by its ID, it has the updated values.
        assertThat(result as Task, notNullValue())
        assertThat(result.id, `is`(task.id))
        assertThat(result.title, `is`(task.title))
        assertThat(result.description, `is`(task.description))
        assertThat(result.isCompleted, `is`(task.isCompleted))
    }

}
