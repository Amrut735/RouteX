package com.routex.app.core.rbac;

import com.routex.app.auth.domain.repository.AuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class SessionViewModel_Factory implements Factory<SessionViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public SessionViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SessionViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static SessionViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new SessionViewModel_Factory(authRepositoryProvider);
  }

  public static SessionViewModel newInstance(AuthRepository authRepository) {
    return new SessionViewModel(authRepository);
  }
}
