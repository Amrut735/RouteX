package com.routex.app.student.domain.usecase

import com.routex.app.core.utils.Resource
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutesUseCase @Inject constructor(
    private val repository: StudentRepository,
) {
    operator fun invoke(): Flow<Resource<List<Route>>> = repository.getRoutes()
}
