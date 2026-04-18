package com.routex.app.auth.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.repository.AuthRepository;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0002"}, d2 = {"USERS_COLLECTION", "", "app_debug"})
public final class AuthRepositoryImplKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USERS_COLLECTION = "users";
}