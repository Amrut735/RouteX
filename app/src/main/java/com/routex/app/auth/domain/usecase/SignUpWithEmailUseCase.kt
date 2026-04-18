package com.routex.app.auth.domain.usecase

import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.domain.repository.AuthRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        displayName: String,
        role: UserRole,
    ): Resource<User> =
        repository.signUpWithEmail(
            email = email.trim(),
            password = password,
            displayName = displayName.trim(),
            role = role,
        )
}
