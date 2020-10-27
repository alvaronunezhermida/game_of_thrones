package com.alvaronunez.gameofthrones.presentation.ui.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    class Impl(override val uiDispatcher: CoroutineContext) : Scope {
        override lateinit var job: Job
    }

    var job: Job
    val uiDispatcher: CoroutineContext
    override val coroutineContext: CoroutineContext
        get() = uiDispatcher + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }
}