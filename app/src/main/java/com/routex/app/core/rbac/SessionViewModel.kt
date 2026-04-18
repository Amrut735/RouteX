package com.routex.app.core.rbac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Application-scoped ViewModel that observes the authenticated user's Firestore
 * document in real time.
 *
 * Placed at the NavGraph root so all composables share the same instance.
 * This means that if an admin changes a user's role while that user is active,
 * the session updates and [RoleGuard] redirects automatically.
 */
sealed class SessionState {
    data object Loading                            : SessionState()
    data object Unauthenticated                    : SessionState()
    data class  Authenticated(val user: User)      : SessionState()
}

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _session = MutableStateFlow<SessionState>(SessionState.Loading)
    val session: StateFlow<SessionState> = _session.asStateFlow()

    /** Convenience accessor — null while loading or unauthenticated. */
    val currentUser: User? get() = (_session.value as? SessionState.Authenticated)?.user

    init {
        observeSession()
    }

    private fun observeSession() {
        viewModelScope.launch {
            authRepository.observeCurrentUser()
                .catch { _session.value = SessionState.Unauthenticated }
                .collect { user ->
                    _session.value = if (user == null) SessionState.Unauthenticated
                                     else SessionState.Authenticated(user)
                }
        }
    }
}
