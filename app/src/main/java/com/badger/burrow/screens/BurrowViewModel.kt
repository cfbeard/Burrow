package com.badger.burrow.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.badger.burrow.common.snackbar.SnackbarManager
import com.badger.burrow.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.badger.burrow.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BurrowViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}