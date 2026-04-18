package com.routex.app.student.domain.repository

import com.routex.app.core.utils.Resource
import com.routex.app.student.domain.model.Route
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getRoutes(): Flow<Resource<List<Route>>>
    suspend fun getRouteById(routeId: String): Resource<Route>
}
