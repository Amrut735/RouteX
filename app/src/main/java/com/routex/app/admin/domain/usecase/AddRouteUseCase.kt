package com.routex.app.admin.domain.usecase

import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

class AddRouteUseCase @Inject constructor(
    private val repository: AdminRepository,
) {
    suspend operator fun invoke(route: BusRoute): Resource<BusRoute> =
        repository.addRoute(route)
}
