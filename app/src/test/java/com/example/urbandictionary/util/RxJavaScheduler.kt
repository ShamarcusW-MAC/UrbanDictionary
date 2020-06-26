package com.example.urbandictionary.util

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxJavaScheduler: TestRule {

    override fun apply(base: Statement, description: Description) = object: Statement() {
        override fun evaluate() {
            RxAndroidPlugins.reset()
            RxAndroidPlugins.setInitMainThreadSchedulerHandler{SCHEDULER}

            RxJavaPlugins.reset()
            RxJavaPlugins.setIoSchedulerHandler { SCHEDULER }
            RxJavaPlugins.setNewThreadSchedulerHandler { SCHEDULER }
            RxJavaPlugins.setComputationSchedulerHandler { SCHEDULER }
        }
    }

    companion object {
        private val SCHEDULER = Schedulers.trampoline()
    }
}