package com.jantune.heartdisease.ui.view.auth.login

import androidx.lifecycle.ViewModel
import com.jantune.heartdisease.data.repository.JantuneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: JantuneRepository
): ViewModel()