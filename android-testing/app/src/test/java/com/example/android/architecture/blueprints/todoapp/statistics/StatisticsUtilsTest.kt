package com.example.android.architecture.blueprints.todoapp.statistics


import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
(TEST SINGLE FUNCTION OR CLASS, FAST, LOW FIDELITY)

 * testName_actionOrInput_resultState
 * subjectUnderTest_actionOrInput_resultState

 * given, when, format (X, Y, Z)
 * arrange, act, assert (A, A, A)

 * */
class StatisticsUtilsTest {

    /*
    * If there is one completed task and no active tasks,
    * the activeTasks percentage should be 0f, the completed tasks percentage should be 100f.
    * */
    @Test
    fun getActiveAndCompletedStats_oneCompleted_returnsZeroHundred() {
        //arrange
        val tasks = listOf(Task("title", "description", isCompleted = true))
        //act
        val result = getActiveAndCompletedStats(tasks)
        //assert
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        //arrange
        val tasks = listOf(Task("title", "description", isCompleted = false))
        //act
        val result = getActiveAndCompletedStats(tasks)
        //assert
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    /*
    * If there are two completed tests and three active test, the completed
    * percentage should be 40f and the active percentage should be 60f.
    * */

    @Test
    fun getActiveAndCompletedStats_bunch_returnsFortySixty() {
        //arrange
        val tasks = listOf(
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false)
        )
        //act
        val result = getActiveAndCompletedStats(tasks)
        //assert
        assertThat(result.activeTasksPercent, `is`(60f))
        assertThat(result.completedTasksPercent, `is`(40f))
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFifty() {
        //arrange
        val tasks = listOf(
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = false)
        )
        //act
        val result = getActiveAndCompletedStats(tasks)
        //assert
        assertThat(result.completedTasksPercent, `is`(50f))
        assertThat(result.activeTasksPercent, `is`(50f))
    }

    //If there is an empty list (emptyList()), then both percentages should be 0f.

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        //arrange
        val tasks = emptyList<Task>()
        //act
        val result = getActiveAndCompletedStats(tasks)
        //assert
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    /*
    * If there was an error loading tasks, the list will be null,
    * then both percentages should be 0f.
    * */

    @Test
    fun getActiveAndCompletedStats_null_returnsZeros() {
        //arrange
        val tasks = null
        //act
        val result = getActiveAndCompletedStats(tasks)
        //assert
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }


}
