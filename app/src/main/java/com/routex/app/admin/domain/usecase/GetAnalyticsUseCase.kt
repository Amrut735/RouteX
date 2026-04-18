package com.routex.app.admin.domain.usecase

import com.routex.app.admin.domain.model.AnalyticsSnapshot
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.core.utils.Resource
import javax.inject.Inject

class GetAnalyticsUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(): Resource<AnalyticsSnapshot> = repo.getAnalyticsSnapshot()
}
