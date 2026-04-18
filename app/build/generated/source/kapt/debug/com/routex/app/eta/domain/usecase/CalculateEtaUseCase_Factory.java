package com.routex.app.eta.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CalculateEtaUseCase_Factory implements Factory<CalculateEtaUseCase> {
  @Override
  public CalculateEtaUseCase get() {
    return newInstance();
  }

  public static CalculateEtaUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateEtaUseCase newInstance() {
    return new CalculateEtaUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateEtaUseCase_Factory INSTANCE = new CalculateEtaUseCase_Factory();
  }
}
