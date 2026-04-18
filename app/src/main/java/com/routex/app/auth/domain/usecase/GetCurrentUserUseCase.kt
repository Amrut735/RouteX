package com.routex.app.auth.domain.usecase

import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(): User? = repository.getCurrentUser()

    fun isLoggedIn(): Boolean = repository.isLoggedIn()
}
