package com.routex.app.auth.presentation.register;

import android.app.Activity;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.*;
import androidx.navigation.NavController;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.presentation.login.LoginViewModel;
import com.routex.app.core.navigation.Screen;
import com.routex.app.core.utils.UiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"WEB_CLIENT_ID", "", "RegisterScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/auth/presentation/register/RegisterViewModel;", "loginViewModel", "Lcom/routex/app/auth/presentation/login/LoginViewModel;", "app_debug"})
public final class RegisterScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String WEB_CLIENT_ID = "606653249058-tg4n1gvletdoeja5ng1r9dlg74far9lf.apps.googleusercontent.com";
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void RegisterScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.presentation.register.RegisterViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.presentation.login.LoginViewModel loginViewModel) {
    }
}