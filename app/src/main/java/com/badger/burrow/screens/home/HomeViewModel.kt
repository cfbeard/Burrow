package com.badger.burrow.screens.home

import androidx.lifecycle.SavedStateHandle
import com.badger.burrow.model.service.LogService
import com.badger.burrow.screens.BurrowViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,

) : BurrowViewModel(logService) {

}