package com.routex.app.student.domain.usecase;

import com.routex.app.student.domain.repository.StudentRepository;
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
public final class GetRoutesUseCase_Factory implements Factory<GetRoutesUseCase> {
  private final Provider<StudentRepository> repositoryProvider;

  public GetRoutesUseCase_Factory(Provider<StudentRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetRoutesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetRoutesUseCase_Factory create(Provider<StudentRepository> repositoryProvider) {
    return new GetRoutesUseCase_Factory(repositoryProvider);
  }

  public static GetRoutesUseCase newInstance(StudentRepository repository) {
    return new GetRoutesUseCase(repository);
  }
}
