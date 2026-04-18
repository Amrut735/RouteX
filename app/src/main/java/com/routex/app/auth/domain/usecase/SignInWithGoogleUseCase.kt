package com.routex.app.auth.domain.usecase

import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.repository.AuthRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(idToken: String): Resource<User> =
        authRepository.signInWithGoogle(idToken)
}
