package com.routex.app.student.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.core.utils.Resource;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.repository.StudentRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\u00060\fH\u0016J\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/routex/app/student/data/repository/StudentRepositoryImpl;", "Lcom/routex/app/student/domain/repository/StudentRepository;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "getRouteById", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/student/domain/model/Route;", "routeId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoutes", "Lkotlinx/coroutines/flow/Flow;", "", "toRoute", "Lcom/google/firebase/firestore/DocumentSnapshot;", "app_debug"})
public final class StudentRepositoryImpl implements com.routex.app.student.domain.repository.StudentRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    
    @javax.inject.Inject()
    public StudentRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.student.domain.model.Route>>> getRoutes() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRouteById(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.student.domain.model.Route>> $completion) {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    private final com.routex.app.student.domain.model.Route toRoute(com.google.firebase.firestore.DocumentSnapshot $this$toRoute) {
        return null;
    }
}