package com.routex.app.auth.presentation.register;

import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.usecase.SignUpDriverUseCase;
import com.routex.app.auth.domain.usecase.SignUpWithEmailUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u0014J\u0006\u0010!\u001a\u00020\u0014J\u0006\u0010\"\u001a\u00020\u0014J\b\u0010#\u001a\u00020$H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/routex/app/auth/presentation/register/RegisterViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "signUpWithEmail", "Lcom/routex/app/auth/domain/usecase/SignUpWithEmailUseCase;", "signUpDriver", "Lcom/routex/app/auth/domain/usecase/SignUpDriverUseCase;", "(Lcom/routex/app/auth/domain/usecase/SignUpWithEmailUseCase;Lcom/routex/app/auth/domain/usecase/SignUpDriverUseCase;)V", "_formState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/auth/presentation/register/RegisterFormState;", "_registerState", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/auth/domain/model/UserRole;", "formState", "Lkotlinx/coroutines/flow/StateFlow;", "getFormState", "()Lkotlinx/coroutines/flow/StateFlow;", "registerState", "getRegisterState", "onConfirmPasswordChange", "", "v", "", "onDriverCodeChange", "onEmailChange", "onNameChange", "onPasswordChange", "onRoleChange", "role", "onTabChange", "tab", "Lcom/routex/app/auth/presentation/register/RegisterTab;", "register", "resetState", "togglePasswordVisibility", "validate", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RegisterViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignUpWithEmailUseCase signUpWithEmail = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignUpDriverUseCase signUpDriver = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.auth.presentation.register.RegisterFormState> _formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.presentation.register.RegisterFormState> formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.auth.domain.model.UserRole>> _registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.auth.domain.model.UserRole>> registerState = null;
    
    @javax.inject.Inject()
    public RegisterViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignUpWithEmailUseCase signUpWithEmail, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignUpDriverUseCase signUpDriver) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.presentation.register.RegisterFormState> getFormState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.auth.domain.model.UserRole>> getRegisterState() {
        return null;
    }
    
    public final void onTabChange(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.presentation.register.RegisterTab tab) {
    }
    
    public final void onNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onConfirmPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onDriverCodeChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onRoleChange(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.model.UserRole role) {
    }
    
    public final void togglePasswordVisibility() {
    }
    
    public final void register() {
    }
    
    private final boolean validate() {
        return false;
    }
    
    public final void resetState() {
    }
}