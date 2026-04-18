package com.routex.app.auth.presentation.login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.core.navigation.Screen
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.utils.UiState

// Web Client ID = the "client_type: 3" client_id from google-services.json
private const val WEB_CLIENT_ID =
    "606653249058-tg4n1gvletdoeja5ng1r9dlg74far9lf.apps.googleusercontent.com"

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val form by viewModel.formState.collectAsState()
    val loginState by viewModel.loginState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // ── Google Sign-In launcher ───────────────────────────────────────────────
    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    .getResult(ApiException::class.java)
                account.idToken?.let { viewModel.signInWithGoogle(it) }
            } catch (e: ApiException) {
                /* handled via loginState error */
            }
        }
    }

    // ── State observer ────────────────────────────────────────────────────────
    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is UiState.Success -> {
                val dest = when (state.data) {
                    UserRole.ADMIN  -> Screen.AdminDashboard.route
                    UserRole.DRIVER -> Screen.DriverDashboard.route
                    else            -> Screen.StudentDashboard.route
                }
                navController.navigate(dest) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
                viewModel.resetState()
            }
            is UiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.resetState()
            }
            else -> Unit
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            // ── Logo ──────────────────────────────────────────────────────────
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DirectionsBus,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "RouteX",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "Sign in to your account",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.height(40.dp))

            // ── Email ─────────────────────────────────────────────────────────
            OutlinedTextField(
                value = form.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = null) },
                isError = form.emailError != null,
                supportingText = form.emailError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            )

            Spacer(Modifier.height(12.dp))

            // ── Password ──────────────────────────────────────────────────────
            OutlinedTextField(
                value = form.password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = viewModel::togglePasswordVisibility) {
                        Icon(
                            imageVector = if (form.isPasswordVisible)
                                Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                            contentDescription = "Toggle password visibility",
                        )
                    }
                },
                visualTransformation = if (form.isPasswordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                isError = form.passwordError != null,
                supportingText = form.passwordError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    viewModel.signIn()
                }),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            )

            Spacer(Modifier.height(24.dp))

            RouteXButton(
                text = "Sign In",
                onClick = viewModel::signIn,
                isLoading = loginState is UiState.Loading,
            )

            Spacer(Modifier.height(12.dp))

            // ── Divider ──────────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "  or  ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(12.dp))

            // ── Google Sign-In Button ─────────────────────────────────────────
            OutlinedButton(
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(WEB_CLIENT_ID)
                        .requestEmail()
                        .build()
                    val client = GoogleSignIn.getClient(context, gso)
                    client.signOut().addOnCompleteListener {
                        googleLauncher.launch(client.signInIntent)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = null,
                    tint = Color(0xFF4285F4),
                    modifier = Modifier.size(20.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text("Continue with Google")
            }

            Spacer(Modifier.height(16.dp))

            TextButton(
                onClick = { navController.navigate(Screen.Register.route) },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text("Don't have an account? Sign Up")
            }
        }
    }
}
