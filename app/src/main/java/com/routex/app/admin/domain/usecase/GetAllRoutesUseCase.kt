package com.routex.app.admin.domain.usecase

import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRoutesUseCase @Inject constructor(
    private val repository: AdminRepository,
) {
    operator fun invoke(): Flow<Resource<List<BusRoute>>> = repository.getAllRoutes()
}
