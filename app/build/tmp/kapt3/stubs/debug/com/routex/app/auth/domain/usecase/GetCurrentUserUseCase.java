package com.routex.app.auth.domain.usecase;

import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.repository.AuthRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0086B\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;", "", "repository", "Lcom/routex/app/auth/domain/repository/AuthRepository;", "(Lcom/routex/app/auth/domain/repository/AuthRepository;)V", "invoke", "Lcom/routex/app/auth/domain/model/User;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLoggedIn", "", "app_debug"})
public final class GetCurrentUserUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.repository.AuthRepository repository = null;
    
    @javax.inject.Inject()
    public GetCurrentUserUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.repository.AuthRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.auth.domain.model.User> $completion) {
        return null;
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
}