package com.routex.app.student.domain.usecase

import com.routex.app.core.utils.Resource
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.repository.StudentRepository
import javax.inject.Inject

class GetRouteByIdUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    suspend operator fun invoke(routeId: String): Resource<Route> =
        repository.getRouteById(routeId)
}
