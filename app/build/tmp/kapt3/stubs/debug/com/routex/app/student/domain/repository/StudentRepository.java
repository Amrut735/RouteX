package com.routex.app.student.domain.repository;

import com.routex.app.core.utils.Resource;
import com.routex.app.student.domain.model.Route;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001a\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\n0\u00030\tH&\u00a8\u0006\u000b"}, d2 = {"Lcom/routex/app/student/domain/repository/StudentRepository;", "", "getRouteById", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/student/domain/model/Route;", "routeId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoutes", "Lkotlinx/coroutines/flow/Flow;", "", "app_debug"})
public abstract interface StudentRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.student.domain.model.Route>>> getRoutes();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRouteById(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.student.domain.model.Route>> $completion);
}