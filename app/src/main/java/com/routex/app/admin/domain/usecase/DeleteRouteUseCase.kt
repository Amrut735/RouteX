package com.routex.app.admin.domain.usecase

import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

class DeleteRouteUseCase @Inject constructor(
    private val repository: AdminRepository,
) {
    suspend operator fun invoke(routeId: String): Resource<Unit> =
        repository.deleteRoute(routeId)
}
