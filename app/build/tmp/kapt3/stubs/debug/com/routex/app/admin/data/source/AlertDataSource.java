package com.routex.app.admin.data.source;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.routex.app.admin.domain.model.EmergencyAlert;
import com.routex.app.admin.domain.model.EmergencyType;
import com.routex.app.core.notification.NotificationHelper;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019*\u00020\u000eH\u0002R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/routex/app/admin/data/source/AlertDataSource;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "notificationHelper", "Lcom/routex/app/core/notification/NotificationHelper;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/routex/app/core/notification/NotificationHelper;)V", "col", "Lcom/google/firebase/firestore/CollectionReference;", "getCol", "()Lcom/google/firebase/firestore/CollectionReference;", "observeActiveAlerts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/routex/app/admin/domain/model/EmergencyAlert;", "resolveAlert", "Lcom/routex/app/core/utils/Resource;", "", "alertId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEmergencyAlert", "alert", "(Lcom/routex/app/admin/domain/model/EmergencyAlert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMap", "", "app_debug"})
public final class AlertDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.notification.NotificationHelper notificationHelper = null;
    
    @javax.inject.Inject()
    public AlertDataSource(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.notification.NotificationHelper notificationHelper) {
        super();
    }
    
    private final com.google.firebase.firestore.CollectionReference getCol() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendEmergencyAlert(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.EmergencyAlert alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.admin.domain.model.EmergencyAlert>> observeActiveAlerts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resolveAlert(@org.jetbrains.annotations.NotNull()
    java.lang.String alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> toMap(com.routex.app.admin.domain.model.EmergencyAlert $this$toMap) {
        return null;
    }
}