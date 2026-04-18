package com.routex.app.auth.presentation.register

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.presentation.login.LoginViewModel
import com.routex.app.core.navigation.Screen
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.utils.UiState

private const val WEB_CLIENT_ID = "606653249058-tg4n1gvletdoeja5ng1r9dlg74far9lf.apps.googleusercontent.com"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val form by viewModel.formState.collectAsState()
    val registerState by viewModel.registerState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    // ── Google Sign-In (creates Student account via GoogleAuth) ───────────────
    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    .getResult(ApiException::class.java)
                account.idToken?.let { loginViewModel.signInWithGoogle(it) }
            } catch (_: ApiException) {}
        }
    }

    // Route on success
    LaunchedEffect(registerState) {
        when (val state = registerState) {
            is UiState.Success -> {
                val dest = when (state.data) {
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
                .padding(horizontal = 24.dp, vertical = 32.dp),
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Join RouteX today",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(Modifier.height(24.dp))

            // ── Tab switcher ─────────────────────────────────────────────────
            TabRow(selectedTabIndex = if (form.activeTab == RegisterTab.STUDENT) 0 else 1) {
                Tab(
                    selected = form.activeTab == RegisterTab.STUDENT,
                    onClick = { viewModel.onTabChange(RegisterTab.STUDENT) },
                    text = { Text("Student") },
                    icon = { Icon(Icons.Rounded.School, contentDescription = null, modifier = Modifier.size(18.dp)) },
                )
                Tab(
                    selected = form.activeTab == RegisterTab.DRIVER,
                    onClick = { viewModel.onTabChange(RegisterTab.DRIVER) },
                    text = { Text("Driver") },
                    icon = { Icon(Icons.Rounded.DirectionsBus, contentDescription = null, modifier = Modifier.size(18.dp)) },
                )
            }

            Spacer(Modifier.height(20.dp))

            // ── Driver code notice ────────────────────────────────────────────
            if (form.activeTab == RegisterTab.DRIVER) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Rounded.Info, null, tint = MaterialTheme.colorScheme.onSecondaryContainer, modifier = Modifier.size(18.dp))
                        Text(
                            text = "Driver accounts require a code issued by your admin.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            // ── Common fields ─────────────────────────────────────────────────
            OutlinedTextField(
                value = form.displayName,
                onValueChange = viewModel::onNameChange,
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = null) },
                isError = form.nameError != null,
                supportingText = form.nameError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = form.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = null) },
                isError = form.emailError != null,
                supportingText = form.emailError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = form.password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = viewModel::togglePasswordVisibility) {
                        Icon(
                            imageVector = if (form.isPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                            contentDescription = null,
                        )
                    }
                },
                visualTransformation = if (form.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = form.passwordError != null,
                supportingText = form.passwordError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = form.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                isError = form.confirmPasswordError != null,
                supportingText = form.confirmPasswordError?.let { { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            )

            // ── Driver code field (only in Driver tab) ────────────────────────
            if (form.activeTab == RegisterTab.DRIVER) {
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = form.driverCode,
                    onValueChange = viewModel::onDriverCodeChange,
                    label = { Text("Driver Code") },
                    leadingIcon = { Icon(Icons.Rounded.Key, contentDescription = null) },
                    isError = form.driverCodeError != null,
                    supportingText = form.driverCodeError?.let { { Text(it) } },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                )
            }

            Spacer(Modifier.height(28.dp))

            RouteXButton(
                text = if (form.activeTab == RegisterTab.DRIVER) "Register as Driver" else "Create Account",
                onClick = viewModel::register,
                isLoading = registerState is UiState.Loading,
            )

            // ── Google (Student only) ─────────────────────────────────────────
            if (form.activeTab == RegisterTab.STUDENT) {
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text("  or  ", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(12.dp))
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
                    Icon(Icons.Rounded.AccountCircle, null, tint = Color(0xFF4285F4), modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Continue with Google")
                }
            }

            Spacer(Modifier.height(12.dp))

            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text("Already have an account? Sign In")
            }
        }
    }
}
