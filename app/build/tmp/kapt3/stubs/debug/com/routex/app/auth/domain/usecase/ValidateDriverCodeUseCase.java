package com.routex.app.auth.domain.usecase;

import com.routex.app.auth.domain.repository.AuthRepository;
import com.routex.app.core.utils.Resource;
import javax.inject.Inject;

/**
 * Validates that the given email exists in the drivers collection
 * AND that the driverCode matches. Returns the driverId on success.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086B\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/routex/app/auth/domain/usecase/ValidateDriverCodeUseCase;", "", "authRepository", "Lcom/routex/app/auth/domain/repository/AuthRepository;", "(Lcom/routex/app/auth/domain/repository/AuthRepository;)V", "invoke", "Lcom/routex/app/core/utils/Resource;", "", "email", "driverCode", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ValidateDriverCodeUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.repository.AuthRepository authRepository = null;
    
    @javax.inject.Inject()
    public ValidateDriverCodeUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<java.lang.String>> $completion) {
        return null;
    }
}