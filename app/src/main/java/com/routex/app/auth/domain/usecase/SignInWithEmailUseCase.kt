package com.routex.app.auth.domain.usecase

import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.repository.AuthRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): Resource<User> =
        repository.signInWithEmail(email.trim(), password)
}
