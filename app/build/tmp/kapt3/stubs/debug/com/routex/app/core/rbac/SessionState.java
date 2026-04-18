package com.routex.app.core.rbac;

import androidx.lifecycle.ViewModel;
import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.repository.AuthRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

/**
 * Application-scoped ViewModel that observes the authenticated user's Firestore
 * document in real time.
 *
 * Placed at the NavGraph root so all composables share the same instance.
 * This means that if an admin changes a user's role while that user is active,
 * the session updates and [RoleGuard] redirects automatically.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/routex/app/core/rbac/SessionState;", "", "()V", "Authenticated", "Loading", "Unauthenticated", "Lcom/routex/app/core/rbac/SessionState$Authenticated;", "Lcom/routex/app/core/rbac/SessionState$Loading;", "Lcom/routex/app/core/rbac/SessionState$Unauthenticated;", "app_debug"})
public abstract class SessionState {
    
    private SessionState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/routex/app/core/rbac/SessionState$Authenticated;", "Lcom/routex/app/core/rbac/SessionState;", "user", "Lcom/routex/app/auth/domain/model/User;", "(Lcom/routex/app/auth/domain/model/User;)V", "getUser", "()Lcom/routex/app/auth/domain/model/User;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Authenticated extends com.routex.app.core.rbac.SessionState {
        @org.jetbrains.annotations.NotNull()
        private final com.routex.app.auth.domain.model.User user = null;
        
        public Authenticated(@org.jetbrains.annotations.NotNull()
        com.routex.app.auth.domain.model.User user) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.auth.domain.model.User getUser() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.auth.domain.model.User component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.core.rbac.SessionState.Authenticated copy(@org.jetbrains.annotations.NotNull()
        com.routex.app.auth.domain.model.User user) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/routex/app/core/rbac/SessionState$Loading;", "Lcom/routex/app/core/rbac/SessionState;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Loading extends com.routex.app.core.rbac.SessionState {
        @org.jetbrains.annotations.NotNull()
        public static final com.routex.app.core.rbac.SessionState.Loading INSTANCE = null;
        
        private Loading() {
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/routex/app/core/rbac/SessionState$Unauthenticated;", "Lcom/routex/app/core/rbac/SessionState;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Unauthenticated extends com.routex.app.core.rbac.SessionState {
        @org.jetbrains.annotations.NotNull()
        public static final com.routex.app.core.rbac.SessionState.Unauthenticated INSTANCE = null;
        
        private Unauthenticated() {
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}