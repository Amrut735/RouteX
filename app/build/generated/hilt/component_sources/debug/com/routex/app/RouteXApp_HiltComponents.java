package com.routex.app;

import com.routex.app.admin.data.di.AdminModule;
import com.routex.app.admin.presentation.analytics.AnalyticsViewModel_HiltModules;
import com.routex.app.admin.presentation.buses.BusManagementViewModel_HiltModules;
import com.routex.app.admin.presentation.dashboard.AdminDashboardViewModel_HiltModules;
import com.routex.app.admin.presentation.emergency.EmergencyViewModel_HiltModules;
import com.routex.app.admin.presentation.manage.ManageRoutesViewModel_HiltModules;
import com.routex.app.admin.presentation.routes.RouteEditorViewModel_HiltModules;
import com.routex.app.admin.presentation.trips.AdminTripsViewModel_HiltModules;
import com.routex.app.auth.data.di.AuthModule;
import com.routex.app.auth.presentation.login.LoginViewModel_HiltModules;
import com.routex.app.auth.presentation.onboarding.OnboardingViewModel_HiltModules;
import com.routex.app.auth.presentation.register.RegisterViewModel_HiltModules;
import com.routex.app.auth.presentation.splash.SplashViewModel_HiltModules;
import com.routex.app.core.di.AppModule;
import com.routex.app.core.notification.RouteXFcmService_GeneratedInjector;
import com.routex.app.core.rbac.SessionViewModel_HiltModules;
import com.routex.app.core.ui.ThemeViewModel_HiltModules;
import com.routex.app.driver.data.di.DriverModule;
import com.routex.app.driver.data.service.LocationTrackingService_GeneratedInjector;
import com.routex.app.driver.presentation.DriverMapViewModel_HiltModules;
import com.routex.app.driver.presentation.DriverViewModel_HiltModules;
import com.routex.app.eta.presentation.EtaViewModel_HiltModules;
import com.routex.app.maps.data.di.MapsModule;
import com.routex.app.maps.presentation.MapViewModel_HiltModules;
import com.routex.app.student.data.di.StudentModule;
import com.routex.app.student.presentation.boarding.AvailableBusesViewModel_HiltModules;
import com.routex.app.student.presentation.boarding.BoardingSelectionViewModel_HiltModules;
import com.routex.app.student.presentation.dashboard.StudentDashboardViewModel_HiltModules;
import com.routex.app.student.presentation.routes.RoutesViewModel_HiltModules;
import com.routex.app.trips.data.di.TripModule;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_SavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class RouteXApp_HiltComponents {
  private RouteXApp_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          AdminModule.class,
          AppModule.class,
          ApplicationContextModule.class,
          AuthModule.class,
          DriverModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          MapsModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          StudentModule.class,
          TripModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements RouteXApp_GeneratedInjector,
      FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements RouteXFcmService_GeneratedInjector,
      LocationTrackingService_GeneratedInjector,
      ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AdminDashboardViewModel_HiltModules.KeyModule.class,
          AdminTripsViewModel_HiltModules.KeyModule.class,
          AnalyticsViewModel_HiltModules.KeyModule.class,
          AvailableBusesViewModel_HiltModules.KeyModule.class,
          BoardingSelectionViewModel_HiltModules.KeyModule.class,
          BusManagementViewModel_HiltModules.KeyModule.class,
          DriverMapViewModel_HiltModules.KeyModule.class,
          DriverViewModel_HiltModules.KeyModule.class,
          EmergencyViewModel_HiltModules.KeyModule.class,
          EtaViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_SavedStateHandleModule.class,
          LoginViewModel_HiltModules.KeyModule.class,
          ManageRoutesViewModel_HiltModules.KeyModule.class,
          MapViewModel_HiltModules.KeyModule.class,
          OnboardingViewModel_HiltModules.KeyModule.class,
          RegisterViewModel_HiltModules.KeyModule.class,
          RouteEditorViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          RoutesViewModel_HiltModules.KeyModule.class,
          SessionViewModel_HiltModules.KeyModule.class,
          SplashViewModel_HiltModules.KeyModule.class,
          StudentDashboardViewModel_HiltModules.KeyModule.class,
          ThemeViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AdminDashboardViewModel_HiltModules.BindsModule.class,
          AdminTripsViewModel_HiltModules.BindsModule.class,
          AnalyticsViewModel_HiltModules.BindsModule.class,
          AvailableBusesViewModel_HiltModules.BindsModule.class,
          BoardingSelectionViewModel_HiltModules.BindsModule.class,
          BusManagementViewModel_HiltModules.BindsModule.class,
          DriverMapViewModel_HiltModules.BindsModule.class,
          DriverViewModel_HiltModules.BindsModule.class,
          EmergencyViewModel_HiltModules.BindsModule.class,
          EtaViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          LoginViewModel_HiltModules.BindsModule.class,
          ManageRoutesViewModel_HiltModules.BindsModule.class,
          MapViewModel_HiltModules.BindsModule.class,
          OnboardingViewModel_HiltModules.BindsModule.class,
          RegisterViewModel_HiltModules.BindsModule.class,
          RouteEditorViewModel_HiltModules.BindsModule.class,
          RoutesViewModel_HiltModules.BindsModule.class,
          SessionViewModel_HiltModules.BindsModule.class,
          SplashViewModel_HiltModules.BindsModule.class,
          StudentDashboardViewModel_HiltModules.BindsModule.class,
          ThemeViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
