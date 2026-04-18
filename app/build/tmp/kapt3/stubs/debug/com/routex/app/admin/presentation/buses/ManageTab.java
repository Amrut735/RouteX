package com.routex.app.admin.presentation.buses;

import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.navigation.NavController;
import com.routex.app.admin.domain.model.Bus;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.core.utils.UiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/routex/app/admin/presentation/buses/ManageTab;", "", "(Ljava/lang/String;I)V", "BUSES", "DRIVERS", "app_debug"})
enum ManageTab {
    /*public static final*/ BUSES /* = new BUSES() */,
    /*public static final*/ DRIVERS /* = new DRIVERS() */;
    
    ManageTab() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.routex.app.admin.presentation.buses.ManageTab> getEntries() {
        return null;
    }
}