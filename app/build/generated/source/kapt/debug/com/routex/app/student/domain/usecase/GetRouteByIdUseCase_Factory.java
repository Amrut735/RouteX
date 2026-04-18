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
public final class GetRouteByIdUseCase_Factory implements Factory<GetRouteByIdUseCase> {
  private final Provider<StudentRepository> repositoryProvider;

  public GetRouteByIdUseCase_Factory(Provider<StudentRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetRouteByIdUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetRouteByIdUseCase_Factory create(Provider<StudentRepository> repositoryProvider) {
    return new GetRouteByIdUseCase_Factory(repositoryProvider);
  }

  public static GetRouteByIdUseCase newInstance(StudentRepository repository) {
    return new GetRouteByIdUseCase(repository);
  }
}
