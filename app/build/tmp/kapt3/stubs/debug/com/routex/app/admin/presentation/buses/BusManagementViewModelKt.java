package com.routex.app.admin.presentation.buses;

import com.routex.app.admin.domain.model.Bus;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.admin.domain.usecase.AddBusUseCase;
import com.routex.app.admin.domain.usecase.AddDriverUseCase;
import com.routex.app.admin.domain.usecase.AssignBusUseCase;
import com.routex.app.admin.domain.usecase.AssignDriverUseCase;
import com.routex.app.admin.domain.usecase.DeleteBusUseCase;
import com.routex.app.admin.domain.usecase.DeleteDriverUseCase;
import com.routex.app.admin.domain.usecase.GetAllBusesUseCase;
import com.routex.app.admin.domain.usecase.GetAllDriversUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.usecase.SeedBusesUseCase;
import com.routex.app.admin.domain.usecase.SeedDriversUseCase;
import com.routex.app.admin.domain.usecase.SeedRoutesUseCase;
import com.routex.app.admin.domain.usecase.UpdateBusUseCase;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u00a8\u0006\u0003"}, d2 = {"toBus", "Lcom/routex/app/admin/domain/model/Bus;", "Lcom/routex/app/admin/presentation/buses/BusForm;", "app_debug"})
public final class BusManagementViewModelKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.admin.domain.model.Bus toBus(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.buses.BusForm $this$toBus) {
        return null;
    }
}