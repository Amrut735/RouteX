package com.routex.app.student.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.core.utils.Resource;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.repository.StudentRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0002"}, d2 = {"ROUTES_COLLECTION", "", "app_debug"})
public final class StudentRepositoryImplKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ROUTES_COLLECTION = "routes";
}