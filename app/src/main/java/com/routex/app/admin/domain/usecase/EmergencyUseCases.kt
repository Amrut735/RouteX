package com.routex.app.admin.domain.usecase

import com.routex.app.admin.domain.model.EmergencyAlert
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendEmergencyAlertUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(alert: EmergencyAlert): Resource<Unit> =
        repo.sendEmergencyAlert(alert)
}

class ObserveActiveAlertsUseCase @Inject constructor(private val repo: AdminRepository) {
    operator fun invoke(): Flow<List<EmergencyAlert>> = repo.observeActiveAlerts()
}

class ResolveAlertUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(alertId: String): Resource<Unit> = repo.resolveAlert(alertId)
}
