package com.badger.burrow.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}