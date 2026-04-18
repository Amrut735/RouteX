package com.routex.app.student.domain.usecase;

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
public final class MissedBusPredictionUseCase_Factory implements Factory<MissedBusPredictionUseCase> {
  @Override
  public MissedBusPredictionUseCase get() {
    return newInstance();
  }

  public static MissedBusPredictionUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MissedBusPredictionUseCase newInstance() {
    return new MissedBusPredictionUseCase();
  }

  private static final class InstanceHolder {
    private static final MissedBusPredictionUseCase_Factory INSTANCE = new MissedBusPredictionUseCase_Factory();
  }
}
