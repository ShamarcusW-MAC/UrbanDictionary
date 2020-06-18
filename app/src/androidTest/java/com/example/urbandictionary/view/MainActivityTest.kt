package com.example.urbandictionary.view

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.urbandictionary.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    /**
     * Recycler view comes into view
     * */
    @Test
    fun test_RecyclerView() {
        SystemClock.sleep(3000)
        onView(withId(R.id.search_edittext)).perform(typeText("explanation"))
        onView(withId(R.id.search_button)).perform(click())
        SystemClock.sleep(5000)
        onView(withId(R.id.items_recyclerview)).perform(swipeDown())
        onView(withId(R.id.items_recyclerview)).perform(swipeDown())
    }


}