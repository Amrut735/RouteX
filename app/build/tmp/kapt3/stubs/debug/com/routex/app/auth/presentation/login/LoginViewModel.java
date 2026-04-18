package com.routex.app.auth.presentation.login;

import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.usecase.SignInWithEmailUseCase;
import com.routex.app.auth.domain.usecase.SignInWithGoogleUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0014J\u0006\u0010\u0019\u001a\u00020\u0014J\u000e\u0010\u0004\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0016J\u0006\u0010\u001b\u001a\u00020\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/routex/app/auth/presentation/login/LoginViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "signInWithEmail", "Lcom/routex/app/auth/domain/usecase/SignInWithEmailUseCase;", "signInWithGoogle", "Lcom/routex/app/auth/domain/usecase/SignInWithGoogleUseCase;", "(Lcom/routex/app/auth/domain/usecase/SignInWithEmailUseCase;Lcom/routex/app/auth/domain/usecase/SignInWithGoogleUseCase;)V", "_formState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/auth/presentation/login/LoginFormState;", "_loginState", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/auth/domain/model/UserRole;", "formState", "Lkotlinx/coroutines/flow/StateFlow;", "getFormState", "()Lkotlinx/coroutines/flow/StateFlow;", "loginState", "getLoginState", "onEmailChange", "", "value", "", "onPasswordChange", "resetState", "signIn", "idToken", "togglePasswordVisibility", "validate", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LoginViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignInWithEmailUseCase signInWithEmail = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignInWithGoogleUseCase signInWithGoogle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.auth.presentation.login.LoginFormState> _formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.presentation.login.LoginFormState> formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.auth.domain.model.UserRole>> _loginState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.auth.domain.model.UserRole>> loginState = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignInWithEmailUseCase signInWithEmail, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignInWithGoogleUseCase signInWithGoogle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.presentation.login.LoginFormState> getFormState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.auth.domain.model.UserRole>> getLoginState() {
        return null;
    }
    
    public final void onEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void togglePasswordVisibility() {
    }
    
    public final void signIn() {
    }
    
    /**
     * Called from the Compose Activity after the Google Sign-In intent completes
     * and an idToken has been extracted from the result.
     */
    public final void signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken) {
    }
    
    private final boolean validate() {
        return false;
    }
    
    public final void resetState() {
    }
}