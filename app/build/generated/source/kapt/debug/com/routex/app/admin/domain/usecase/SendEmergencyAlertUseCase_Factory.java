package com.routex.app.admin.domain.usecase;

import com.routex.app.admin.domain.repository.AdminRepository;
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
public final class SendEmergencyAlertUseCase_Factory implements Factory<SendEmergencyAlertUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public SendEmergencyAlertUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public SendEmergencyAlertUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static SendEmergencyAlertUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new SendEmergencyAlertUseCase_Factory(repoProvider);
  }

  public static SendEmergencyAlertUseCase newInstance(AdminRepository repo) {
    return new SendEmergencyAlertUseCase(repo);
  }
}
