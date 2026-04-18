package com.routex.app.auth.presentation.register

import androidx.lifecycle.viewModelScope
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.domain.usecase.SignUpDriverUseCase
import com.routex.app.auth.domain.usecase.SignUpWithEmailUseCase
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.core.utils.isValidEmail
import com.routex.app.core.utils.isValidName
import com.routex.app.core.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Which tab the register screen is on */
enum class RegisterTab { STUDENT, DRIVER }

data class RegisterFormState(
    val displayName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val driverCode: String = "",
    /** Always locked to STUDENT for email signup; DRIVER for driver code flow */
    val selectedRole: UserRole = UserRole.STUDENT,
    val activeTab: RegisterTab = RegisterTab.STUDENT,
    val isPasswordVisible: Boolean = false,
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val driverCodeError: String? = null,
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpWithEmail: SignUpWithEmailUseCase,
    private val signUpDriver: SignUpDriverUseCase,
) : BaseViewModel() {

    private val _formState = MutableStateFlow(RegisterFormState())
    val formState = _formState.asStateFlow()

    private val _registerState = MutableStateFlow<UiState<UserRole>>(UiState.Idle)
    val registerState = _registerState.asStateFlow()

    fun onTabChange(tab: RegisterTab) {
        _formState.value = _formState.value.copy(
            activeTab = tab,
            selectedRole = if (tab == RegisterTab.DRIVER) UserRole.DRIVER else UserRole.STUDENT,
        )
    }

    fun onNameChange(v: String) =
        _formState.value.let { _formState.value = it.copy(displayName = v, nameError = null) }

    fun onEmailChange(v: String) =
        _formState.value.let { _formState.value = it.copy(email = v, emailError = null) }

    fun onPasswordChange(v: String) =
        _formState.value.let { _formState.value = it.copy(password = v, passwordError = null) }

    fun onConfirmPasswordChange(v: String) =
        _formState.value.let {
            _formState.value = it.copy(confirmPassword = v, confirmPasswordError = null)
        }

    fun onDriverCodeChange(v: String) =
        _formState.value.let { _formState.value = it.copy(driverCode = v, driverCodeError = null) }

    // Only kept for legacy compat — tab drives the role now
    fun onRoleChange(role: UserRole) {}

    fun togglePasswordVisibility() =
        _formState.value.let {
            _formState.value = it.copy(isPasswordVisible = !it.isPasswordVisible)
        }

    fun register() {
        if (!validate()) return
        val form = _formState.value

        viewModelScope.launch {
            _registerState.value = UiState.Loading
            val result = if (form.activeTab == RegisterTab.DRIVER) {
                signUpDriver(
                    email = form.email,
                    password = form.password,
                    displayName = form.displayName,
                    driverCode = form.driverCode,
                )
            } else {
                signUpWithEmail(
                    email = form.email,
                    password = form.password,
                    displayName = form.displayName,
                    role = UserRole.STUDENT,          // always Student for normal signup
                )
            }
            when (result) {
                is Resource.Success ->
                    _registerState.value = UiState.Success(result.data.role)
                is Resource.Error   ->
                    _registerState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    private fun validate(): Boolean {
        val form = _formState.value
        var updated = form
        var valid = true

        if (!form.displayName.isValidName()) {
            updated = updated.copy(nameError = "Enter your full name")
            valid = false
        }
        if (!form.email.isValidEmail()) {
            updated = updated.copy(emailError = "Enter a valid email address")
            valid = false
        }
        if (!form.password.isValidPassword()) {
            updated = updated.copy(passwordError = "Password must be at least 6 characters")
            valid = false
        }
        if (form.password != form.confirmPassword) {
            updated = updated.copy(confirmPasswordError = "Passwords do not match")
            valid = false
        }
        if (form.activeTab == RegisterTab.DRIVER && form.driverCode.isBlank()) {
            updated = updated.copy(driverCodeError = "Driver code is required")
            valid = false
        }

        _formState.value = updated
        return valid
    }

    fun resetState() { _registerState.value = UiState.Idle }
}
