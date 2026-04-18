package com.routex.app.auth.presentation.register;

import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.usecase.SignUpDriverUseCase;
import com.routex.app.auth.domain.usecase.SignUpWithEmailUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

/**
 * Which tab the register screen is on
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/routex/app/auth/presentation/register/RegisterTab;", "", "(Ljava/lang/String;I)V", "STUDENT", "DRIVER", "app_debug"})
public enum RegisterTab {
    /*public static final*/ STUDENT /* = new STUDENT() */,
    /*public static final*/ DRIVER /* = new DRIVER() */;
    
    RegisterTab() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.routex.app.auth.presentation.register.RegisterTab> getEntries() {
        return null;
    }
}