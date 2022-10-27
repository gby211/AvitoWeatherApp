package com.pavel.avitoweatherapp.presentation.settings

import com.pavel.avitoweatherapp.core.BaseViewModel
import com.pavel.avitoweatherapp.domain.usecase.GetCoordinatesByCityNameUC
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getCoordinatesByCityNameUC: GetCoordinatesByCityNameUC,):BaseViewModel() {
}