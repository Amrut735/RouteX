package com.routex.app.auth.domain.usecase

import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.repository.AuthRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

/**
 * Registers a driver account. Only succeeds if the email + driverCode
 * combination exists in the admin-managed `drivers` Firestore collection.
 */
class SignUpDriverUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        displayName: String,
        driverCode: String,
    ): Resource<User> = authRepository.signUpDriver(email, password, displayName, driverCode)
}
