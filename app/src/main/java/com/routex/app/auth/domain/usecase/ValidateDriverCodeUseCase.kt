package com.routex.app.auth.domain.usecase

import com.routex.app.auth.domain.repository.AuthRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

/**
 * Validates that the given email exists in the drivers collection
 * AND that the driverCode matches. Returns the driverId on success.
 */
class ValidateDriverCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, driverCode: String): Resource<String> =
        authRepository.validateDriverCode(email, driverCode)
}
