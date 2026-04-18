package com.routex.app.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.domain.usecase.SignInWithEmailUseCase
import com.routex.app.auth.domain.usecase.SignInWithGoogleUseCase
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.core.utils.isValidEmail
import com.routex.app.core.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmail: SignInWithEmailUseCase,
    private val signInWithGoogle: SignInWithGoogleUseCase,
) : BaseViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState = _formState.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<UserRole>>(UiState.Idle)
    val loginState = _loginState.asStateFlow()

    fun onEmailChange(value: String) {
        _formState.value = _formState.value.copy(email = value, emailError = null)
    }

    fun onPasswordChange(value: String) {
        _formState.value = _formState.value.copy(password = value, passwordError = null)
    }

    fun togglePasswordVisibility() {
        _formState.value = _formState.value.copy(
            isPasswordVisible = !_formState.value.isPasswordVisible,
        )
    }

    fun signIn() {
        if (!validate()) return
        val (email, password) = _formState.value

        viewModelScope.launch {
            _loginState.value = UiState.Loading
            when (val result = signInWithEmail(email, password)) {
                is Resource.Success ->
                    _loginState.value = UiState.Success(result.data.role)
                is Resource.Error   ->
                    _loginState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    /**
     * Called from the Compose Activity after the Google Sign-In intent completes
     * and an idToken has been extracted from the result.
     */
    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            when (val result = signInWithGoogle.invoke(idToken)) {
                is Resource.Success ->
                    _loginState.value = UiState.Success(result.data.role)
                is Resource.Error   ->
                    _loginState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    private fun validate(): Boolean {
        val form = _formState.value
        var valid = true
        var updated = form

        if (!form.email.isValidEmail()) {
            updated = updated.copy(emailError = "Enter a valid email address")
            valid = false
        }
        if (!form.password.isValidPassword()) {
            updated = updated.copy(passwordError = "Password must be at least 6 characters")
            valid = false
        }

        _formState.value = updated
        return valid
    }

    fun resetState() {
        _loginState.value = UiState.Idle
    }
}
