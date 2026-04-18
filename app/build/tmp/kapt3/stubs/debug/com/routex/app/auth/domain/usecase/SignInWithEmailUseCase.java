package com.routex.app.auth.domain.usecase;

import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.repository.AuthRepository;
import com.routex.app.core.utils.Resource;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0086B\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/routex/app/auth/domain/usecase/SignInWithEmailUseCase;", "", "repository", "Lcom/routex/app/auth/domain/repository/AuthRepository;", "(Lcom/routex/app/auth/domain/repository/AuthRepository;)V", "invoke", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/auth/domain/model/User;", "email", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SignInWithEmailUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.repository.AuthRepository repository = null;
    
    @javax.inject.Inject()
    public SignInWithEmailUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.repository.AuthRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.auth.domain.model.User>> $completion) {
        return null;
    }
}